package Others.base.SimpleProxy.DynamicProxyDemo;

import Utills.PrintUtill;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("*** proxy: " + proxy.getClass() + " , method: " + method + " , args: " + args);
        if (args != null){
            for (Object arg: args){
                PrintUtill.println(" " + arg);
            }
        }
        return method.invoke(proxied, args);
    }
}
