package base.String;

import Utills.PrintUtill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  区别在于它不会使用b{1,3}与c匹配，在匹配完成abb之后，会使用regex中的c匹配text中的c。
 */
public class StringDemo9 {
    public static void main(String[] args) {
        String regex = "ab{1,3}?c";
        String str = "abbc";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()){
            System.out.println(m.group(0));
            System.out.println(m.start()+ ""+m.end());
//            System.out.println(m.group(1));
//            System.out.println(m.group(2));
//            System.out.println(m.group(3));
        }
        PrintUtill.printlnRule();
        String text="abbcc";
        String regex2="ab{1,3}";
        String regex3="ab{1,3}?";
        String[] test = text.split(regex);
        for(String a: test){
            PrintUtill.println("懒惰test: "+text+",a:"+a+" ");
        }
//        String text2="abbcc";
        PrintUtill.printlnRule();
        String[] test2 = text.split(regex2);
        for(String a: test2){
            PrintUtill.println("贪婪test2: "+text+",a:"+a+" ");
        }
        PrintUtill.printlnRule();
        String[] test3 = text.split(regex3);
        for(String a: test){
            PrintUtill.println("懒惰test: "+text+",a:"+a+" ");
        }
//        System.out.println(test[1]);
//        System.out.println(test[1]);
//        String ss = "\\?(([A-Za-z0-9-~_=%]++\\\\&{0,1})+)";
//        String ht ="http://www.fapiao.com/dddp-web/pdf/download?request=6e7JGxxxxx4ILd-kExxxxxxxqJ4-CHLmqVnenXC692m74H38sdfdsazxcUmfcOH2fAfY1Vw__%5EDadIfJgiEf";
//        String[] s =  ht.split(ss);
//        for (String s1: s){
//            PrintUtill.println(s1 + " ");
//
//        }
    }
}
