package Others.base.SimpleProxy.SimpleProxyDemo;

import Others.base.SimpleProxy.Interface;
import Utills.PrintUtill;

public class SimpleProxy implements Interface {

    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    public void doSomething() {
        PrintUtill.println("SimpleProxy doSomething");
        proxied.doSomething();
    }

    public void somethingElse(String arg) {
        PrintUtill.println("SimpleProxy somethingElse " + arg);
        proxied.somethingElse(arg);
    }
}
