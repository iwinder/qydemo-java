package DesignPatterns.Creational.Singleton.runoob.test;

import Utills.PrintUtill;

public class A {
    private static A a = new A();
    public static int c1;
    public static int c2=0;

    private A(){
        c1++;
        c2++;
    }

    public static  A getA(){
        return a;
    }

    public static void main(String[] args) {
        A s = A.getA();
        PrintUtill.println(s.c1);
        PrintUtill.printlnRule();
        PrintUtill.println(s.c2);
    }
}
