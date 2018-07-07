package Others.base.SimpleProxy.SimpleProxyDemo;

import Others.base.SimpleProxy.Animal;
import Others.base.SimpleProxy.Dog;

public class SimpleProxyDemo {
    public static void  consumer(Animal iface){
        iface.doBark();
        iface.somethingElse("WindCoder.com");
    }

    public static void main(String[] args) {
        consumer(new Dog());
        consumer(new SimpleProxy(new Dog()));
    }
}

