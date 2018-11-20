package Others.base;

import Utills.PrintUtill;

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
    }
}
