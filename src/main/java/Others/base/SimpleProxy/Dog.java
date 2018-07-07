package Others.base.SimpleProxy;

import Utills.PrintUtill;

public class Dog implements Animal {
    public void doSomething() {
        PrintUtill.println("doSomething");
    }

    public void somethingElse(String arg) {
        PrintUtill.println("somethingElse " + arg);
    }
}
