package Others.base;

import Utills.PrintUtill;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class StringDemo {
    public static void main(String[] args) {
        String s = "/data/content/upload/2018/11/19/1cb4285f-24b7-42bc-9ea9-1dc811973b8d.zip";
        PrintUtill.println("s.sub："+s.substring(0, s.lastIndexOf("/")));
        AtomicLong  one = new AtomicLong(20);
        Long tow = Long.valueOf(22);
        PrintUtill.println("One add Two:"+ one.addAndGet(tow));
        Long three  = Long.valueOf(-21);
        PrintUtill.println("One add Two:"+ one.addAndGet(three));
        LongAdder ss = new LongAdder();
        ss.add(22);
        PrintUtill.println("sss add :"+ ss);
        ss.add(-2);
        PrintUtill.println("sss add :"+ ss);
        Long ssa =  ss.longValue();
        PrintUtill.println("ssa add :"+ ssa);
        String s1 = "1a743402-0049-450f-bfcb-7b530c243af9";
        String s2 = "1a743402-0049-450f-bfcb-7b530c243af9";
        PrintUtill.println(s1.equals(s2));
        PrintUtill.printlnRule();
        PrintUtill.printlnRule();
        byte[] array = {97,98,99,-122};
        String a = new String(array);
        PrintUtill.println(a);
        byte sa = (byte)97;
        PrintUtill.println(sa);
        array[2] = sa;
        String a1 = new String(array);
        PrintUtill.println(a1);
        fa();
    }
    static synchronized public void  fa(){
        PrintUtill.println("ddd");
    }

    public static synchronized  void  fa2(){
        PrintUtill.println("ddds");
    }
}
