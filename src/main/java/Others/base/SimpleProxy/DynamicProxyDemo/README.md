## 动态代理
在使用动态代理时，需要定义一个位于代理类与委托类之间的中介类，也叫动态代理类，该类被要求实现InvocationHandler接口。

当调用代理类对象的方法时，这个“调用”会转送到中介类的invoke方法中。参数proxy为调用method的代理实例(即动态生成的那个代理类)，method标识了我们具体调用的是代理类的哪个方法，args为这个方法的参数。

```java
public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
```

一个典型的动态代理可分为以下四个步骤：

- 创建抽象角色（如：Animal）
- 创建真实角色(如：Dog)
- 通过实现InvocationHandler接口创建中介类（如：DynamicProxyHandler）
- 通过场景类，动态生成代理类（如：SimpleDynamicProxy）


在场景类中通过如下代码动态产生了一个代理类，并返回了其实例：
```java
    Animal prox = (Animal) Proxy.newProxyInstance(
            Animal.class.getClassLoader(),
            new Class[]{Animal.class},
            new DynamicProxyHandler(dog)
    );
```

Proxy的newProxyInstance主要业务逻辑为：
```java
 Class<?> cl = getProxyClass0(loader, intfs);
 final Constructor<?> cons = cl.getConstructor(constructorParams);
 return cons.newInstance(new Object[]{h});
```
