package Others.base.DateDemo;

import Utills.PrintUtill;

public class DateDemoTow {

    private static final int DAY_MILLIS = 24 * 60 * 60 * 1000;
    private static final int DAY_BEIJING = 8 *  60 * 60 * 1000;
    public static void main(String[] args) {
        // 从1970开始到现在格林威治时间的时间戳
        long day = (System.currentTimeMillis()+DAY_BEIJING) / DAY_MILLIS;
        PrintUtill.println("day:"+day);

        // 从1970开始到现在北京时区的时间戳，北京时间比格林威治整整快了8个小时，所以需要加上8个小时的时间差
        day = (System.currentTimeMillis()+DAY_BEIJING) / DAY_MILLIS;
        PrintUtill.println("day:"+day);
    }
}
