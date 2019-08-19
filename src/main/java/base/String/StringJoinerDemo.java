package base.String;

import Utills.PrintUtill;

import java.util.StringJoiner;

public class StringJoinerDemo {
    static String s = "a";
    public static void main(String[] args) {
        StringJoiner sj = new StringJoiner(":", "[", "]");
        sj.add("www").add("windcoder").add("com");
        String desiredString = sj.toString();
        PrintUtill.println(desiredString);
        sj = new StringJoiner(",");
        String.join("-", "apple","banana","orange");

        PrintUtill.println("Runtime.getRuntime().availableProcessors(): " + Runtime.getRuntime().availableProcessors());
    }
}
