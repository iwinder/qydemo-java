package Others.base.DateDemo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateDemo {
    public static void main(String[] args) {
        // 日期-按月增加
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd EE hh:mm:ss");
        Date now = new Date();
        System.out.println(df.format(now));
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(now);
        gc.add(Calendar.MONTH,24);
        System.out.println(df.format(gc.getTime()));
    }
}
