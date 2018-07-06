package Others.base.SimpleProxy;

import Utills.PrintUtill;

public class RealObject implements Interface {
    public void doSomething() {
        PrintUtill.println("doSomething");
    }

    public void somethingElse(String arg) {
        PrintUtill.println("somethingElse " + arg);
    }
}
