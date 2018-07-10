上一篇我们了解了代理与动态代理的基础以及创建使用方式。这一篇继续关注动态代理。
## 1.JDK动态代理源码实现原理分析

上篇得知通过调用静态方法Proxy.newProxyInstance()可以创建动态代理，之后会调用InvocationHandler接口的一个实现DynamicProxyHandler对象的invoker方法。

故这里我们主要探寻这两个方法的源码实现。

本篇所有源码基于JDK1.8.
## Proxy.newProxyInstance
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

        // 获得与传入的指定类装载器（loader）和接口列表（intfs）相关的代理类类型对象
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
            // 通过反射获取构造函数
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
检测创建代理类需要的权限
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
### 

## 参考资料

### 动态代理
[java动态代理原理及解析](https://blog.csdn.net/scplove/article/details/52451899)

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