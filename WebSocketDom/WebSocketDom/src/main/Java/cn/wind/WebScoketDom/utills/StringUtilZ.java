package cn.wind.WebScoketDom.utills;

import java.text.SimpleDateFormat;

/**
 * Created by wind on 2016/11/16.
 */
public class StringUtilZ {
    /***
     * 获得当前时间的时间戳
     * @param type 时间戳的样式
     * @return
     */
    public static String getTimestampOfNow(String type){
        SimpleDateFormat dateFormat = new SimpleDateFormat(type);
        return dateFormat.format(System.currentTimeMillis());
    }
}
