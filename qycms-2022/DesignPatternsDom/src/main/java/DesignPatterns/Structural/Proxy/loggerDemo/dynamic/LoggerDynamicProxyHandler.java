package DesignPatterns.Structural.Proxy.loggerDemo.dynamic;

import Utills.PrintUtill;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;

public class LoggerDynamicProxyHandler implements InvocationHandler {
    private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Class<?> clazz = target.getClass();
        // newProxyInstance 初始化代理，基于被代理者的类加载器、实现的接口，以及当前代理类
        // 实例化被代理对象，并返回一个Proxy代理类对象
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(target,args);
        after();
        return obj;
    }

    private void before() {
        PrintUtill.println("日志动态代理开始>>>>>>>>>>>>>" + sdf.format(System.currentTimeMillis()) + ">>>>>>>>>>>");
    }

    private void after() {
        PrintUtill.println("日志动态代理完成>>>>>>>>>>>>>" + sdf.format(System.currentTimeMillis()) + ">>>>>>>>>>>");
    }
}
