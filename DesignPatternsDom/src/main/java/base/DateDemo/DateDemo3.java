package base.DateDemo;

import Utills.DateUtil;
import Utills.PrintUtill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateDemo3 {

    public static void main(String[] args) {
        DateValue d = new DateValue();
        d.setTime("2019-04-23");
        d.setCount(1);

        DateValue d2 = new DateValue();
        d2.setTime("2019-04-29");
        d2.setCount(1);

        DateValue d3 = new DateValue();
        d3.setTime("2019-04-25");
        d3.setCount(1);

        List<DateValue> list = new ArrayList<DateValue>();
        list.add(d);
        list.add(d2);
        list.add(d3);

        String start = "2019-04-03";
        String end = "2019-04-30";
        List<DateValue> nlist = addWeeksForNull(list, start, end);
        nlist.forEach( a ->{
            PrintUtill.println(a.getTime());
            PrintUtill.println(a.getCount());
        });
    }

    public static   List<DateValue> addWeeksForNull(List<DateValue> oldList,String start,String end){
        Calendar c_begin = DateUtil.StringtoCalendar(start,"yyyy-MM-dd");
        Calendar    c_end = DateUtil.StringtoCalendar(end,"yyyy-MM-dd");
        ArrayList <DateValue> newList = new ArrayList<DateValue>();
        if(c_begin==null||c_end==null){
            return newList;
        }
        Date c_tmp_begin = c_begin.getTime();
        Date c_tmp_end = c_begin.getTime();
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy/MM/dd");
        int tmpLeng = oldList.size();
        int count = 0;
        DateValue statisticResult = null;
        StringBuffer tmpSTime = new StringBuffer();
        while(c_begin.compareTo(c_end)<=0){
//            System.out.println("第"+count+"周  日期："+ sdf.format((c_begin.getTime().getTime())+","+weeks[c_begin.get(Calendar.DAY_OF_WEEK)]));
            if(c_begin.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
                //周日，标准的一周结束日期，进行一次数据判断添加
                c_tmp_end = c_begin.getTime();
                System.out.println("第"+count+"周 日期:"+sdf.format(c_tmp_begin.getTime())+"-"+sdf.format(c_tmp_end.getTime()));//用于查看一周开始与结束，可删
                if(tmpSTime.length()<=0){
                    tmpSTime.append(sdf.format(c_tmp_begin.getTime())+"-"+sdf.format(c_tmp_end.getTime()));
                }else{
                    tmpSTime.replace(0,tmpSTime.length(),sdf.format(c_tmp_begin.getTime())+"-"+sdf.format(c_tmp_end.getTime()));
                }
                count = addWeeksForNullOfNewList( count, tmpLeng,tmpSTime,oldList, newList, c_tmp_begin, c_tmp_end, statisticResult);
            }
            if(c_begin.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY){
                //周一，标准的一周开始时间，使用c_tmp_begin临时记录，用于之后与结束日期组成“开始-结束”的时间字符串。
                c_tmp_begin = c_begin.getTime();
            }
            if(c_begin.compareTo(c_end)==0&&(c_begin.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY)){
                //最后一天，但结束日期不为周日时进行对应的判断与数据填充
                c_tmp_end = c_begin.getTime();
                if(tmpSTime.length()<=0){
                    tmpSTime.append(sdf.format(c_tmp_begin.getTime())+"-"+sdf.format(c_tmp_end.getTime()));
                }else{
                    tmpSTime.replace(0,tmpSTime.length(),sdf.format(c_tmp_begin.getTime())+"-"+sdf.format(c_tmp_end.getTime()));
                }
                count = addWeeksForNullOfNewList( count, tmpLeng,tmpSTime,oldList, newList, c_tmp_begin, c_tmp_end, statisticResult);
            }
            c_begin.add(Calendar.DAY_OF_YEAR, 1);
        }
        return  newList;
    }

    private static int addWeeksForNullOfNewList(int count, int tmpLeng, StringBuffer tmpSTime, List<DateValue> oldList, ArrayList <DateValue> newList, Date c_tmp_begin, Date c_tmp_end, DateValue statisticResult){
        if(count<tmpLeng){
            if(c_tmp_begin.compareTo(DateUtil.StringToDate(oldList.get(count).getTime(),"yyyy-MM-dd"))<=0&&
                    c_tmp_end.compareTo(DateUtil.StringToDate(oldList.get(count).getTime(),"yyyy-MM-dd"))>=0){
                statisticResult = oldList.get(count);
                statisticResult.setTime(tmpSTime.toString());
                newList.add(statisticResult);
                count++;
            }else{
                statisticResult  =getNewDateValue(tmpSTime.toString());
                newList.add(statisticResult);
            }
        }else{
            statisticResult  =getNewDateValue(tmpSTime.toString());
            newList.add(statisticResult);
        }
        return count;
    }


    private static DateValue getNewDateValue(String start) {
        DateValue s = new DateValue();
        s.setTime(start);
        s.setCount(0);
        return s;
    }
}

class DateValue {
    String time;
    Integer count;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}