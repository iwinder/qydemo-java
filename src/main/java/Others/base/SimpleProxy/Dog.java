package Others.base.SimpleProxy;

import Utills.PrintUtill;

public class Dog implements Animal {
    public void doBark() {
        PrintUtill.println("doBark wow");
    }

    public void somethingElse(String arg) {
        PrintUtill.println("somethingElse " + arg);
    }
}
