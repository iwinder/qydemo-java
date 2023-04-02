package base.DateDemo;

import java.time.LocalDateTime;
import java.util.Date;

public class DateDemo2 {
    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time.minusDays(6));

        Date now = new Date(1556380800000L);
        System.out.println(now);
    }
}
