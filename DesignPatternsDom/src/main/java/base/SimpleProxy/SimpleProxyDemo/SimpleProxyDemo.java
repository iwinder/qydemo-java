package base.SimpleProxy.SimpleProxyDemo;

import base.SimpleProxy.Animal;
import base.SimpleProxy.Dog;
import Utills.PrintUtill;

/**
 * consumer 接收的Animal，所以它无法知道正则获得的到底是Dog还是SimpleProxy，因为这两者都实现了Animal。
 *
 * 但是SimpleProxy已经被插入到consumer与Dog之间，因此它会执行操作，然后调用Dog上相同的方法。
 */
public class SimpleProxyDemo {
    public static void  consumer(Animal iface){
        iface.doBark();
        iface.somethingElse("WindCoder.com");
    }

    public static void main(String[] args) {
        consumer(new Dog());
        PrintUtill.printlnRule();
        consumer(new SimpleProxy(new Dog()));
    }
}

