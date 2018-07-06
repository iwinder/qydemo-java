package Others.base.SimpleProxy.SimpleProxyDemo;

import Others.base.SimpleProxy.Interface;
import Others.base.SimpleProxy.RealObject;

public class SimpleProxyDemo {
    public static void  consumer(Interface iface){
        iface.doSomething();
        iface.somethingElse("WindCoder.com");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}

