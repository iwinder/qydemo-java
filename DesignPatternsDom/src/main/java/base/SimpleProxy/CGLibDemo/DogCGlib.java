package base.SimpleProxy.CGLibDemo;

import Utills.PrintUtill;

public class DogCGlib {
    public void doBark() {
        PrintUtill.println("doBark wow CGlib");
    }

    public void somethingElse(String arg) {
        PrintUtill.println("somethingElse CGlib" + arg);
    }
}
