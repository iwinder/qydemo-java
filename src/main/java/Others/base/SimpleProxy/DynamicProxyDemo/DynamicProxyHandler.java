package Others.base.SimpleProxy.DynamicProxyDemo;

import Utills.PrintUtill;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {
    // 被代理的实现类，如Dog等
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("*** proxy: " + proxy.getClass() + " , method: " + method + " , args: " + args);

        if (args != null){
            PrintUtill.println(" ");
            for (Object arg: args){
                PrintUtill.println(" " + arg);
            }
            PrintUtill.println(" ");
        }
        return method.invoke(proxied, args);
    }
}
