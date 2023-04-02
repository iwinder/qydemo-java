package base.SimpleProxy.CGLibDemo;

import Utills.PrintUtill;
import net.sf.cglib.core.DebuggingClassWriter;

public class CGLIBDemo {
    public static void main(String[] args) {
        // 设置输出目录，方便之后查看CGLIB生成的class---非必选项
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./tmp/");
        // 正式执行
        CGlibProxy proxy = new CGlibProxy();
        DogCGlib dog = (DogCGlib)proxy.getInstance(DogCGlib.class);
        dog.doBark();
        PrintUtill.printlnRule();
        dog.somethingElse("  你好");
    }
}
