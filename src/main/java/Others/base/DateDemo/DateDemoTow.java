package Others.base.DateDemo;

import Utills.PrintUtill;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemoTow {

    private static final int DAY_MILLIS = 24 * 60 * 60 * 1000;
    private static final int DAY_BEIJING = 8 *  60 * 60 * 1000;
    public static void main(String[] args) {
        // 从1970开始到现在格林威治时间的时间戳
        long day = (System.currentTimeMillis()) / DAY_MILLIS;
        PrintUtill.println("day:"+day);

        // 从1970开始到现在北京时区的时间戳，北京时间比格林威治整整快了8个小时，所以需要加上8个小时的时间差
        day = (System.currentTimeMillis()+DAY_BEIJING) / DAY_MILLIS;
        PrintUtill.println("day:"+day);
        Date old = new Date();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        try {
            old = df.parse("2019-07-15  07:10:15");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long days = (System.currentTimeMillis() + DAY_BEIJING - old.getTime())/3600000 + 2;
        PrintUtill.println(days);
        Long s = (long) Math.ceil( Math.pow(days, 1.2));
        PrintUtill.println(s);

        Long d = (1*1+1*2)*1000/s;
        PrintUtill.println(d);

        Date tem = new Date(1563246175958L);
        PrintUtill.println(tem);
    }
}
