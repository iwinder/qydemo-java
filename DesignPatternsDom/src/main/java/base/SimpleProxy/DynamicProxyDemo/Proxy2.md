上一篇我们了解了代理与动态代理的基础以及创建使用方式。这一篇继续关注动态代理。
## 1.JDK动态代理源码实现原理分析

上篇得知通过调用静态方法Proxy.newProxyInstance()可以创建动态代理，之后会调用InvocationHandler接口的一个实现DynamicProxyHandler对象的invoker方法。

故这里我们主要探寻这两个方法的源码实现。

本篇所有源码基于JDK1.8.
## proxy的class文件生成方式
### Proxy.newProxyInstance
上篇中也提到该方法存在三个参数

- ClassLoader loader：一个类加载器，用于定义代理类的类加载器，通常可以从已经被加载的对象获取其类加载器，然后传递给它。
- Class<?>[] interfaces：代理类要实现的接口列表
- InvocationHandler h： 关联的调用处理器引用，即InvocationHandler接口的一个实现，如DynamicProxyHandler，代表的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
```
 public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
        throws IllegalArgumentException
    {
        // 检测指定的对象引用不为空（即h不为空），若为空则抛出NullPointerException异常。
        Objects.requireNonNull(h);
        
        // 拷贝代理类要实现的接口列表,之后的操作均是使用该拷贝intfs，而不会涉及原列表interfaces。
        final Class<?>[] intfs = interfaces.clone();
        
        // 获取一个安全管理器对象security，这个对象所属的目录为java.lang.SecurityManager
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            // 检测创建代理类需要的权限
            checkProxyAccess(Reflection.getCallerClass(), loader, intfs);
        }

        // 关键代码1：获得与传入的指定类装载器（loader）和接口列表（intfs）相关的代理类类型对象
        /*
         * Look up or generate the designated proxy class.
         */
        Class<?> cl = getProxyClass0(loader, intfs);

        //  通过反射获取构造函数对象并生成代理类cl的实例
        /*
         * Invoke its constructor with the designated invocation handler.
         */
        try {
            // 检测权限
            if (sm != null) {
                checkNewProxyPermission(Reflection.getCallerClass(), cl);
            }
            // 关键代码2：通过反射获取该代理类的构造函数
            final Constructor<?> cons = cl.getConstructor(constructorParams);
            final InvocationHandler ih = h;
            if (!Modifier.isPublic(cl.getModifiers())) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        cons.setAccessible(true);
                        return null;
                    }
                });
            }
            // 关键代码3：返回这个新的代理类的一个实例
            return cons.newInstance(new Object[]{h});
        } catch (IllegalAccessException|InstantiationException e) {
            throw new InternalError(e.toString(), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new InternalError(t.toString(), t);
            }
        } catch (NoSuchMethodException e) {
            throw new InternalError(e.toString(), e);
        }
    }

```
### checkProxyAccess
检测创建代理类需要的权限,此处涉及到的SecurityManager安全管理器内容，后期单开篇细究。
```$xslt
    private static void checkProxyAccess(Class<?> caller,
                                         ClassLoader loader,
                                         Class<?>... interfaces)
    {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            ClassLoader ccl = caller.getClassLoader();
            if (VM.isSystemDomainLoader(loader) && !VM.isSystemDomainLoader(ccl)) {
                sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
            }
            ReflectUtil.checkProxyPackageAccess(ccl, interfaces);
        }
    }
```
### Class<?> cl = getProxyClass0(loader, intfs);
关键代码1，用于从缓存获取或重新生成所需要这个代理类。

通过下面具体实现可见，这方法会根据类加载器与接口类型到缓存中寻找一个代理类的Class对象，如果没有就通过ProxyClassFactory创建一个新的。

由此可见，在传入loader参数的时候，需要跟传入的interface相关，所以比较常见的做法就是用接口或其实现类getClass().getClassLoader()方法（或如上篇的Animal.class.getClassLoader()）获得一个类加载器
```
    /**
     * Generate a proxy class.  Must call the checkProxyAccess method
     * to perform permission checks before calling this.
     */
    private static Class<?> getProxyClass0(ClassLoader loader,
                                           Class<?>... interfaces) {
        // 校验接口数量
        if (interfaces.length > 65535) {
            throw new IllegalArgumentException("interface limit exceeded");
        }
        // 从缓存中获取，如果没有就通过ProxyClassFactory创建
        // If the proxy class defined by the given loader implementing
        // the given interfaces exists, this will simply return the cached copy;
        // otherwise, it will create the proxy class via the ProxyClassFactory
        return proxyClassCache.get(loader, interfaces);
    }
```
### proxyClassCache.get(loader, interfaces);
继续跟入proxyClassCache.get(loader, interfaces);

该方法是java.lang.reflect.WeakCache的get方法。

此处不深究里面提到的缓存，后期单开篇细究。

方法中最终通过 V value = supplier.get();获取动态代理类，其中supplier是Factory,这个类定义在WeakCach的内部。
```
 public V get(K key, P parameter) {
        Objects.requireNonNull(parameter);

        expungeStaleEntries();
        // 通过类加载器classLoader生成以及一级缓存key
        Object cacheKey = CacheKey.valueOf(key, refQueue);

        // lazily install the 2nd level valuesMap for the particular cacheKey
        // 获取二级缓存，不存在则新建
        ConcurrentMap<Object, Supplier<V>> valuesMap = map.get(cacheKey);
        if (valuesMap == null) {
            ConcurrentMap<Object, Supplier<V>> oldValuesMap
                = map.putIfAbsent(cacheKey,
                                  valuesMap = new ConcurrentHashMap<>());
            if (oldValuesMap != null) {
                valuesMap = oldValuesMap;
            }
        }

        // create subKey and retrieve the possible Supplier<V> stored by that
        // subKey from valuesMap
        // 生成二级缓存key
        Object subKey = Objects.requireNonNull(subKeyFactory.apply(key, parameter));
        // 通过key获取二级缓存value,即缓存的代理类。不存在则新建代理类并加入缓存。
        Supplier<V> supplier = valuesMap.get(subKey);
        Factory factory = null;

        while (true) {
            if (supplier != null) {
                // supplier might be a Factory or a CacheValue<V> instance
                V value = supplier.get();
                if (value != null) {
                    return value;
                }
            }
            // else no supplier in cache
            // or a supplier that returned null (could be a cleared CacheValue
            // or a Factory that wasn't successful in installing the CacheValue)

            // lazily construct a Factory
            if (factory == null) {
                factory = new Factory(key, parameter, subKey, valuesMap);
            }

            if (supplier == null) {
                supplier = valuesMap.putIfAbsent(subKey, factory);
                if (supplier == null) {
                    // successfully installed Factory
                    supplier = factory;
                }
                // else retry with winning supplier
            } else {
                if (valuesMap.replace(subKey, supplier, factory)) {
                    // successfully replaced
                    // cleared CacheEntry / unsuccessful Factory
                    // with our Factory
                    supplier = factory;
                } else {
                    // retry with current supplier
                    supplier = valuesMap.get(subKey);
                }
            }
        }
    }
```
### supplier.get()
现在进入上面supplier.get()内部一探究竟。

发现内部关键语句 value = Objects.requireNonNull(valueFactory.apply(key, parameter));

其中，valueFactory是ProxyClassFactory类型
```
   @Override
        public synchronized V get() { // serialize access
        
            // re-check
            Supplier<V> supplier = valuesMap.get(subKey);
            if (supplier != this) {
                // something changed while we were waiting:
                // might be that we were replaced by a CacheValue
                // or were removed because of failure ->
                // return null to signal WeakCache.get() to retry
                // the loop
                return null;
            }
            // else still us (supplier == this)

            // create new value
            V value = null;
            try {
                // 这里又通过valueFactory.apply(key, parameter)得到value进行返回
                value = Objects.requireNonNull(valueFactory.apply(key, parameter));
            } finally {
                if (value == null) { // remove us on failure
                    valuesMap.remove(subKey, this);
                }
            }
            // the only path to reach here is with non-null value
            assert value != null;

            // wrap value with CacheValue (WeakReference)
            CacheValue<V> cacheValue = new CacheValue<>(value);

            // put into reverseMap
            reverseMap.put(cacheValue, Boolean.TRUE);

            // try replacing us with CacheValue (this should always succeed)
            if (!valuesMap.replace(subKey, this, cacheValue)) {
                throw new AssertionError("Should not reach here");
            }

            // successfully replaced us with new CacheValue -> return the value
            // wrapped by it
            return value;
        }
```
### valueFactory.apply(key, parameter)
进入java.lang.reflect.Proxy.ProxyClassFactory#apply
在这里面可以找到生成字节码（即代理类）的语句。
```
  public Class<?> apply(ClassLoader loader, Class<?>[] interfaces) {

            Map<Class<?>, Boolean> interfaceSet = new IdentityHashMap<>(interfaces.length);
            for (Class<?> intf : interfaces) {
                // 确保该loader加载的此类（intf）
                /*
                 * Verify that the class loader resolves the name of this
                 * interface to the same Class object.
                 */
                Class<?> interfaceClass = null;
                try {
                    interfaceClass = Class.forName(intf.getName(), false, loader);
                } catch (ClassNotFoundException e) {
                }
                
                if (interfaceClass != intf) {
                    throw new IllegalArgumentException(
                        intf + " is not visible from class loader");
                }
                // 确保是一个接口
                /*
                 * Verify that the Class object actually represents an
                 * interface.
                 */
                if (!interfaceClass.isInterface()) {
                    throw new IllegalArgumentException(
                        interfaceClass.getName() + " is not an interface");
                }
                // 确保接口没重复  
                /*
                 * Verify that this interface is not a duplicate.
                 */
                if (interfaceSet.put(interfaceClass, Boolean.TRUE) != null) {
                    throw new IllegalArgumentException(
                        "repeated interface: " + interfaceClass.getName());
                }
            }

            String proxyPkg = null;     // package to define proxy class in
            int accessFlags = Modifier.PUBLIC | Modifier.FINAL;

            //  验证所有非公共的接口在同一个包内；公共的就无需处理.
            /*
             * Record the package of a non-public proxy interface so that the
             * proxy class will be defined in the same package.  Verify that
             * all non-public proxy interfaces are in the same package.
             */
            for (Class<?> intf : interfaces) {
                int flags = intf.getModifiers();
                if (!Modifier.isPublic(flags)) {
                    accessFlags = Modifier.FINAL;
                    String name = intf.getName();
                    int n = name.lastIndexOf('.');
                    String pkg = ((n == -1) ? "" : name.substring(0, n + 1));
                    if (proxyPkg == null) {
                        proxyPkg = pkg;
                    } else if (!pkg.equals(proxyPkg)) {
                        throw new IllegalArgumentException(
                            "non-public interfaces from different packages");
                    }
                }
            }

            if (proxyPkg == null) {
                // if no non-public proxy interfaces, use com.sun.proxy package
                proxyPkg = ReflectUtil.PROXY_PACKAGE + ".";
            }

            // 为代理类生成一个名字，防止重复
            /*
             * Choose a name for the proxy class to generate.
             */
            long num = nextUniqueNumber.getAndIncrement();
            String proxyName = proxyPkg + proxyClassNamePrefix + num;

            // 生成指定代理类
            /*
             * Generate the specified proxy class.
             */
            byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces, accessFlags);
            try {
                return defineClass0(loader, proxyName,
                                    proxyClassFile, 0, proxyClassFile.length);
            } catch (ClassFormatError e) {
                /*
                 * A ClassFormatError here means that (barring bugs in the
                 * proxy class generation code) there was some other
                 * invalid aspect of the arguments supplied to the proxy
                 * class creation (such as virtual machine limitations
                 * exceeded).
                 */
                throw new IllegalArgumentException(e.toString());
            }
        }
```
关键语句有：
```
 byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces, accessFlags);
```
###  ProxyGenerator.generateProxyClass( proxyName, interfaces, accessFlags)
进入sun.misc.ProxyGenerator#generateProxyClass(java.lang.String, java.lang.Class<?>[])。

这里面便是得到class文件byte内容的逻辑。
```
  public static byte[] generateProxyClass(final String var0, Class<?>[] var1, int var2) {
        ProxyGenerator var3 = new ProxyGenerator(var0, var1, var2);
        //动态生成代理类的字节码,非常复杂
        final byte[] var4 = var3.generateClassFile();
         //根据配置，判断class文件是否存储在本地
        if (saveGeneratedFiles) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    try {
                        int var1 = var0.lastIndexOf(46);
                        Path var2;
                        if (var1 > 0) {
                            Path var3 = Paths.get(var0.substring(0, var1).replace('.', File.separatorChar));
                            Files.createDirectories(var3);
                            var2 = var3.resolve(var0.substring(var1 + 1, var0.length()) + ".class");
                        } else {
                            var2 = Paths.get(var0 + ".class");
                        }

                        Files.write(var2, var4, new OpenOption[0]);
                        return null;
                    } catch (IOException var4x) {
                        throw new InternalError("I/O exception saving generated file: " + var4x);
                    }
                }
            });
        }
        //返回代理类的字节码 
        return var4;
    }
```
## proxy的class文件内容
可以自定义存储硬盘位置
调用sun.misc.ProxyGenerator#generateProxyClass(java.lang.String, java.lang.Class<?>[])函数即可
```
public static void showProxyClass() {
        String path = "./$Proxy0.class";
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0",
                Dog.class.getInterfaces());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
```
在mian函数中调用，即可获取到生成的$Proxy0.class类
```
public final class $Proxy0 extends Proxy implements Animal {
    private static Method m1;
    private static Method m3;
    private static Method m2;
    private static Method m4;
    private static Method m0;

    public $Proxy0(InvocationHandler var1) throws  {
        super(var1);
    }

    public final boolean equals(Object var1) throws  {
        try {
            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void doBark() throws  {
        try {
            super.h.invoke(this, m3, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final void somethingElse(String var1) throws  {
        try {
            super.h.invoke(this, m4, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final int hashCode() throws  {
        try {
            return (Integer)super.h.invoke(this, m0, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m3 = Class.forName("Others.base.SimpleProxy.Animal").getMethod("doBark");
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m4 = Class.forName("Others.base.SimpleProxy.Animal").getMethod("somethingElse", Class.forName("java.lang.String"));
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}

```
## proxy类对象生成方式
可以看最上面java.lang.reflect.Proxy#newProxyInstance中的详情，里面便是生成方式。

简单说是生成了代理对象的字节码文件后,根据构造函数生成对象
```
//生成字节码，获取类文件
Class<?> cl = getProxyClass0(loader, intfs);
//省略
//反射，根据构造函数，生成对象，构造参数为InvocationHandler h
return cons.newInstance(new Object[]{h});
```
对应的$Proxy0构造函数为：
```
public $Proxy0(InvocationHandler var1) throws  {
    super(var1);
}
```
其会调用父级的，即：
```
protected Proxy(InvocationHandler h) {
    Objects.requireNonNull(h);
    this.h = h;
}
```
此处的h即为之前传入的InvocationHandler实现类DynamicProxyHandler
## invoke方法触发
由方法doBark()分析可见，代理类实现了委托类的方法，也就是说 代理对象触发doBark方法时，调用InvocationHandler的m3方法
```
    public final void doBark() throws  {
        try {
            super.h.invoke(this, m3, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }
```
其m3的定义在下面
```
static {
    try {
        m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
        m3 = Class.forName("Others.base.SimpleProxy.Animal").getMethod("doBark");
        m2 = Class.forName("java.lang.Object").getMethod("toString");
        m4 = Class.forName("Others.base.SimpleProxy.Animal").getMethod("somethingElse", Class.forName("java.lang.String"));
        m0 = Class.forName("java.lang.Object").getMethod("hashCode");
    } catch (NoSuchMethodException var2) {
        throw new NoSuchMethodError(var2.getMessage());
    } catch (ClassNotFoundException var3) {
        throw new NoClassDefFoundError(var3.getMessage());
    }
}
```
可见m3是通过反射获取到的Animal的doBark方法。
```
 m3 = Class.forName("Others.base.SimpleProxy.Animal").getMethod("doBark");
```
然后看我们自定义的实现Others.base.SimpleProxy.DynamicProxyDemo.DynamicProxyHandler#invoke

三个参数分别对应：
- Object proxy 代理类
- Method method 方法
- Object[] args 方法method的参数 
```
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("*** proxy: " + proxy.getClass() + " , method: " + method + " , args: " + args);
        if (args != null){
            for (Object arg: args){
                PrintUtill.println(" " + arg);
            }
        }
         //通过反射，把proxy的代理类 转交给 实现类上
        return method.invoke(proxied, args);
    }
```
代理类调用doBark方法，实际是利用父类InvocationHandler属性h,传入doBark这个method到h实现类DynamicProxyHandler的invoke中，
在自定义的InvocationHandler即DynamicProxyHandler中，再利用反射，利用实现类Dog完成doBark操作，最终完成了代理类 到 实现类的调用。
## 参考资料

### 动态代理

* [java动态代理作用及源码分析](https://www.jianshu.com/p/9d5ef621f2d1)

* [Java JDK 动态代理使用及实现原理分析](https://blog.csdn.net/bluetjs/article/details/52263410)

* [代理 3 动态代理](https://www.jianshu.com/p/0e06cf43a3c2)

[深入剖析JDK动态代理源码实现](https://blog.csdn.net/lsgqjh/article/details/68486433)

[java动态代理原理及解析](https://blog.csdn.net/scplove/article/details/52451899)

[Java JDK 动态代理（AOP）使用及实现原理分析](https://blog.csdn.net/jiankunking/article/details/52143504)

[Spring温故而知新系列教程之AOP代理](https://www.jb51.net/article/140444.htm)

[java静态代理与动态代理简单分析](https://www.cnblogs.com/V1haoge/p/5860749.html)

[java的动态代理机制详解](https://www.cnblogs.com/xiaoluo501395377/p/3383130.html)

### System.getSecurityManager()
[java之jvm学习笔记四(安全管理器)](https://blog.csdn.net/yfqnihao/article/details/8262858)

[打开Java中的SecurityManager](http://www.importnew.com/9751.html)

### Reflection.getCallerClass()
[JDK8的@CallerSensitive](https://blog.csdn.net/aguda_king/article/details/72355807)

### AccessController.doPrivileged
[关于AccessController.doPrivileged](http://simpleframework.net/news/view?newsId=4eaf169107bf45d3832c9c03f85905e4)

[对AccessController.doPrivileged一点了解](https://www.xuebuyuan.com/659682.html)

#### 缓存
[JDK动态代理代理类的生成与缓存](https://www.cnblogs.com/James-Gong/p/8126129.html)

[代理4 动态代理的缓存机制](https://www.jianshu.com/p/9f5566b5e7fb)

[WeakCache详解](http://xiaoxiaoher.iteye.com/blog/2372315)