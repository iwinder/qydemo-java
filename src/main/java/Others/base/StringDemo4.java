package Others.base;

public class StringDemo4 {
    public static void main(String[] args) {



        String s3 = new String("1") + new String("a");
//        String s3 = new String("11");


        s3.intern();
        String s4 = "1a";
        System.out.println(s3 == s4);
        System.out.println(s3.intern() == s4);
        String s1 = "1"+"a";
        System.out.println(s1 == s4);
        System.out.println(s3 == s4);
    }
}
