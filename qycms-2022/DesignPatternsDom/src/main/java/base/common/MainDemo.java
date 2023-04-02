package base.common;

import Utills.PrintUtill;

/**
 * [C++ 值传递、指针传递、引用传递详解](https://www.cnblogs.com/yanlingyin/archive/2011/12/07/2278961.html)
 */
public class MainDemo {
    public static void main(String[] args) {
        User a = new User("a",10,10);
        User b = new User("b",11, 11);
        PrintUtill.println("交换前：");
        PrintUtill.println("a: "+a);
        PrintUtill.println("b: "+b);
        PrintUtill.printlnRule();
        swap(a,b);
        PrintUtill.println("交换最后：");
        PrintUtill.println("a: "+a);
        PrintUtill.println("b: "+b);
        PrintUtill.printlnRule();
        change(a);
        PrintUtill.println("修改最后：");
        PrintUtill.println("a: "+a);
    }

    public static void swap(Object sa, Object sb){
        Object sc = sa;
        sa = sb;
        sb = sc;
        PrintUtill.println("交换中：");
        PrintUtill.println("sa: " + sa);
        PrintUtill.println("sb: " + sb);
        PrintUtill.printlnRule();
    }

    public static void change(User sa){
        sa.setName("a2");
        sa.setAge(11);
        sa.setHeight(12);
        PrintUtill.println("修改中：");
        PrintUtill.println("sa: " + sa);
    }
}
