package Others.base;

import Utills.PrintUtill;

/***
 * [Java调用函数传递参数到底是值传递还是引用传递](https://www.cnblogs.com/wutianqi/p/8723582.html)
 * [java之语法之方法调用之地址传值之空对象（null）](https://lixh1986.iteye.com/blog/2354853)
 * [Java参数引用传递之例外：null](https://blog.csdn.net/stevesun13/article/details/51922169)
 *
 * https://blog.csdn.net/weixin_34268843/article/details/86864970
 */

public class StringDemo3 {
    public static void main(String[] args) {
        String s = "aaa";
        add(s);
        PrintUtill.println(s);

        String s1 = new String("123");
        String s2 = "123";
        String s3 = new String("123");
        String s4 = "123";
        PrintUtill.println("s1: "+ (s1==s2)+", s1.hashCode:"+s1.hashCode()+", addr:"+System.identityHashCode(s1));
        PrintUtill.println("s2: "+ (s2==s3)+", s2.hashCode:"+s2.hashCode()+", addr:"+System.identityHashCode(s2));
        PrintUtill.println("s3: "+ (s1==s3)+", s3.hashCode:"+s3.hashCode()+", addr:"+System.identityHashCode(s3));
        PrintUtill.println("s4: "+ (s2==s4)+", s4.hashCode:"+s4.hashCode()+", addr:"+System.identityHashCode(s4));
    }

    public static void add(String s){
        PrintUtill.printlnRule();
        s = "bbb";
        PrintUtill.println(s);
        PrintUtill.printlnRule();
    }
}
