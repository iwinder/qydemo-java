package Utills;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 根据开始与结束日期获取相减得到的天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate;
        java.util.Date endDate;
        try{
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
        } catch (ParseException e){
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return day;
    }
    /**
     * 日期 Date 转 String
     * @param date
     * @param type
     * @return
     */
    public static String DateToString(Date date, String type){
        SimpleDateFormat dateFormat = new SimpleDateFormat(type);
        return dateFormat.format(date);
    }
    /**
     * 时间字符串转为新的type样式的时间字符串
     * @param dateString
     * @param type
     * @return
     */
    public static String StringToNewStringOfDate(String dateString,String type){
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }
    /**
     * 时间 String 转 Date
     * @param dateString
     * @param type
     * @return
     */
    public static Date StringToDate(String dateString,String type){
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 时间 String 转 Calendar
     * @param dateString
     * @param type
     * @return
     */
    public static Calendar StringtoCalendar(String dateString,String type){
        SimpleDateFormat sdf= new SimpleDateFormat(type);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    /**
     * 在指定日期上加一天
     * @param date
     * @return
     */
    public static Date addDateOneDay(Date date) {
        if (null == date) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.DATE, 1); //日期加1天
//     c.add(Calendar.DATE, -1); //日期减1天
        date = c.getTime();
        return date;
    }
    /**
     * // 获取当日期的前后日期 i为正数 向后推迟i天，负数时向前提前i天
     * @param i
     * @return
     */
    public static Date getNumdate(int i) {
        Calendar cd = Calendar.getInstance();
        cd.add(Calendar.DATE, i);
        return cd.getTime();
    }
    /**
     * 获取某月的第一天
     *  0当月
     *  -1 前一个月
     *  1 后一个月
     * @param i
     * @return
     */
    public static Date getNumMonth(int i){
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, i);
        cal_1.set(Calendar.DAY_OF_MONTH,1);
        return cal_1.getTime();
    }
    /**
     * 获取本周日的日期
     * @return
     */
    public static Date getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return c.getTime();
    }
    /**
     * 获取本周一的日期
     * @return
     */
    public static Date getMondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return c.getTime();
    }
}
