package base.SimpleProxy.SimpleProxyDemo;

import base.SimpleProxy.Animal;
import Utills.PrintUtill;

public class SimpleProxy implements Animal {

    private Animal proxied;

    public SimpleProxy(Animal proxied) {
        this.proxied = proxied;
    }

    public void doBark() {
        PrintUtill.println("SimpleProxy doSomething");
        proxied.doBark();
    }

    public void somethingElse(String arg) {
        PrintUtill.println("SimpleProxy somethingElse " + arg);
        proxied.somethingElse(arg);
    }
}
