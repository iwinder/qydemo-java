package Others.base.SimpleProxy.CGLibDemo;

import Utills.PrintUtill;
import net.sf.cglib.core.DebuggingClassWriter;

public class CGLIBDemo {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./tmp/");
        CGlibProxy proxy = new CGlibProxy();
        DogCGlib dog = (DogCGlib)proxy.getInstance(DogCGlib.class);
        dog.doBark();
        PrintUtill.printlnRule();
        dog.somethingElse("  你好");
    }
}
