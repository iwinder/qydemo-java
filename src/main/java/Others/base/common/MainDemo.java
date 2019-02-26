package Others.base.common;

import Utills.PrintUtill;

/**
 * [C++ 值传递、指针传递、引用传递详解](https://www.cnblogs.com/yanlingyin/archive/2011/12/07/2278961.html)
 */
public class MainDemo {
    public static void main(String[] args) {
        User a = new User("a",10,10);
        User b = new User("b",11, 11);
        PrintUtill.println("交换前：");
        PrintUtill.println(a);
        PrintUtill.println(b);
        PrintUtill.printlnRule();
        swap(a,b);
        PrintUtill.println("交换最后：");
        PrintUtill.println(a);
        PrintUtill.println(b);
        PrintUtill.printlnRule();
    }

    public static void swap(Object sa, Object sb){
        Object sc = sa;
        sa = sb;
        sb = sc;
        PrintUtill.println("交换中：");
        PrintUtill.println(sa);
        PrintUtill.println(sb);
        PrintUtill.printlnRule();
    }
}
