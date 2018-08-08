package Others.base.DateDemo;

import Utills.DateTimeUtils;
import Utills.PrintUtill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateDemo {
    public static void main(String[] args) {
        // 日期-按月增加
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date now = new Date();
        System.out.println(df.format(now));
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(now);
        gc.add(Calendar.MONTH,24);
        System.out.println(df.format(gc.getTime()));
        PrintUtill.printlnRule();
        // 按日增减  int days = Math.abs(
        Date start  = DateTimeUtils.formatDateString("2018-8-7 17:05:31",DateTimeUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
        int  i = Math.abs(DateTimeUtils.compareDaysWithDay(start, now));
        PrintUtill.println(now + "与 " + start + "相差天数：" );
        PrintUtill.println(i);

        int a = (int) (now.getTime() - start.getTime());
        String s = DateTimeUtils.getDayHourMinuteSecond(a);
        PrintUtill.printlnRule();
        PrintUtill.println(now + "与 " + start + "相差天数：" );
        PrintUtill.println(s);
        PrintUtill.printlnRule();

        long l = now.getTime() - start.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s1 = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        System.out.println(day + "天" + hour + "小时" + min +"分" + s1 + "秒");
        PrintUtill.printlnRule();
        gc.setTime(start);
        gc.add(Calendar.DATE,0);
        System.out.println(df.format(gc.getTime()));
        gc.setTime(start);
        gc.add(Calendar.DATE,1);
        System.out.println(df.format(gc.getTime()));
         long l1 = day / 7;


    }
}
