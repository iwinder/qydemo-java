package base.String;

public class StringDemo7 {
    public static void main(String[] args) {
        String str1 = new StringBuilder("测试").append("01").toString();
        String str2 = new StringBuilder("ja").append("va").toString();

        System.out.println(str1.intern()==str1);
        System.out.println(str2.intern()==str2);
    }
}
