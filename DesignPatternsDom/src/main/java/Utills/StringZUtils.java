package Utills;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringZUtils {
    public static String getMatcher(String regex, String source) {
           String result = "";
           Pattern pattern = Pattern.compile(regex);
           Matcher matcher = pattern.matcher(source);
            if (matcher.find()) {
                System.out.println("Found value: " + matcher.group(0) );
                System.out.println("Found value: " + matcher.group(1) );
                System.out.println("Found value: " + matcher.group(2) );
                System.out.println("Found value: " + matcher.group(3) );

                result = matcher.group(1);
            }else {
                System.out.println("Not Found value" );
            }
            return result;
    }

    public static String getReplaceAll(String regex, String source,String  replace ){
        String result = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        return matcher.replaceAll(replace);
    }

}
