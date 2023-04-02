package base.Array;

import Utills.PrintUtill;

/**
 *
 */
public class ArrayDemo {
    public static void main(String[] args) {
        int[] a1={1,2,3,4};
        PrintUtill.println("a1: " + a1.getClass().getName());
        // a1: [I

        String[] s = new String[2];
        PrintUtill.println("s: " + s.getClass().getName());
        // s: [Ljava.lang.String;

        String[][] ss = new String[2][3];
        PrintUtill.println("ss: " + ss.getClass().getName());
        // ss: [[Ljava.lang.String;

        // 由上可得到结论：数组也是对象，数组的顶层父类是Object。
        PrintUtill.printlnRule();
        // 数组的继承
        Object obj = a1; // 数组的父类也是Object,可以将a向上转型为Object

        int[]b = (int[])obj; // 可以进行向下转型

        if (obj instanceof int[]){   // 可以使用instanceof关键字进行类型判定
            PrintUtill.println("obj的真实类型是int[] :" + obj.getClass().getName());
        }

        Object[] obja = s;
        PrintUtill.println("obja: " + obja.getClass().getName());
        // obja: [Ljava.lang.String;  可见obj的真实类型是String[]

        PrintUtill.println("s: " + s.getClass().getSuperclass().getName());
        // s: java.lang.Object

        PrintUtill.println("obja getSuperclass: " + obja.getClass().getSuperclass().getName());

        StringBuffer sbf = new StringBuffer();
        StringBuilder sbd = new StringBuilder();
    }
}
