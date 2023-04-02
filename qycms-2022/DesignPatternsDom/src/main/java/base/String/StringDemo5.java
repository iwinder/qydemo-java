package base.String;

import Utills.PrintUtill;

public class StringDemo5 {
    public static void main(String[] args) {
        String s1 = new String("123");
        s1.intern();
        String s2 = "123";
        String s3 = new String("123");
        String s4 = "123";
        String s5 = s3.intern();

        PrintUtill.println("s1: "+ (s1==s2)+", s1.hashCode:"+s1.hashCode()+", addr:"+System.identityHashCode(s1));
        PrintUtill.println("s2: "+ (s2==s3)+", s2.hashCode:"+s2.hashCode()+", addr:"+System.identityHashCode(s2));
        PrintUtill.println("s3: "+ (s1==s3)+", s3.hashCode:"+s3.hashCode()+", addr:"+System.identityHashCode(s3));
        PrintUtill.println("s4: "+ (s2==s4)+", s4.hashCode:"+s4.hashCode()+", addr:"+System.identityHashCode(s4));
        PrintUtill.println("s5: "+ (s2==s5)+", s4.hashCode:"+s5.hashCode()+", addr:"+System.identityHashCode(s5));
        PrintUtill.println("s5: "+ (s4==s5)+", s4.hashCode:"+s5.hashCode()+", addr:"+System.identityHashCode(s5));
        PrintUtill.println("s3 -2: "+ (s2==s3)+", s3.hashCode:"+s3.hashCode()+", addr:"+System.identityHashCode(s3));
    }
}
