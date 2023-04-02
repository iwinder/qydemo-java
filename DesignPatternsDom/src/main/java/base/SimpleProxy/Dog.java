package base.SimpleProxy;

import Utills.PrintUtill;

public class Dog implements Animal {
    public void doBark() {
        PrintUtill.println("Dog doBark wow");
    }

    public void somethingElse(String arg) {
        PrintUtill.println("Dog somethingElse " + arg);
    }
}
