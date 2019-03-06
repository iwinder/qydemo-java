package Others.base.DateDemo;

import Utills.DateTimeUtils;
import Utills.PrintUtill;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 参考资料
 * [Joda-Time 操作常用时间函数](https://blog.csdn.net/zhousenshan/article/details/74788044)
 */
public class DateDemo {
    public static void main(String[] args) {
        // 日期-按月增加
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date now = new Date();
        try {
            now = df.parse("2019-01-30  17:10:15");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(df.format(now));
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(now);
        gc.add(Calendar.MONTH,1);
        System.out.println(df.format(gc.getTime()));
        PrintUtill.printlnRule();
        // 按日增减  int days = Math.abs(
        Date start  = DateTimeUtils.formatDateString("2018-8-2 17:05:31",DateTimeUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
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
        int LimitCycle = 7;
        PrintUtill.printlnRule();
        gc.setTime(start);
        gc.add(Calendar.DATE,0);
        System.out.println(df.format(gc.getTime()));
        gc.setTime(start);
        gc.add(Calendar.DATE,1);
        System.out.println(df.format(gc.getTime()));
        long l1 = day / LimitCycle;
        PrintUtill.printlnRule();

        DateTime startDate = new DateTime(start).withTimeAtStartOfDay();
        DateTime nowDate = new DateTime().withTimeAtStartOfDay();
        int days=Days.daysBetween(startDate,nowDate ).getDays();
        int cyclesTimes = days / LimitCycle;
        int startAdd = cyclesTimes * LimitCycle;
        int endAdd = startAdd + LimitCycle;
        PrintUtill.println(nowDate.millisOfDay().withMaximumValue() + "与 " + startDate.withTimeAtStartOfDay() + "相差天数：" );
        PrintUtill.println( cyclesTimes + " " + days);
        PrintUtill.printlnRule();
        PrintUtill.println( "周期是： ");
        PrintUtill.println(startDate.withTimeAtStartOfDay().plusDays(startAdd) + "到 " + startDate.withTimeAtStartOfDay().plusDays(endAdd) + "相差天数：" );
        int[] si = {1,2,3};

    }
}
