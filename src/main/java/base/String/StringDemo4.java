package base.String;

public class StringDemo4 {
    public static void main(String[] args) {
        String s1 = new String("1") + new String("a");
        s1.intern();
        String s2 = "1a";
        System.out.println(s1 == s2);
        System.out.println(s1.intern() == s2);
    }
}
