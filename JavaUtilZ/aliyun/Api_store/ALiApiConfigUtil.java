package cn.zhonya.FaceUtil.utils;

/**
 * Created by wind on 2017/1/10.
 */
public class ALiApiConfigUtil {

    public static final String METHOD = "POST";
    //阿里api统一APPCODE
    public static String APPCODE = "个人 APPCODE";



    //性别api地址
    public static String HOST_FACE_GENDER = "https://dm-22.data.aliyun.com";
    public static String URL_FACE_GENDER = "/rest/160601/face/gender_detection.json";
    //年龄api地址
    public static String  HOST_FACE_AGE = "https://dm-23.data.aliyun.com";
    public static String  URL_FACE_AGE = "/rest/160601/face/age_detection.json";

    /**
     * 阿里墨迹天气api
     */
    //墨迹天气api Host地址
    public static final String HOST_MOJI_WEATHER = "http://aliv8.data.moji.com";
    //天气实况-墨迹
    public static final String URL_MOJI_WEATHER_CONDITION ="/whapi/json/aliweather/condition";
    public static final String TOKEN_MOJI_WEATHER_CONDITION = "ff826c205f8f4a59701e64e9e64e01c4";
    //天气预报24小时-墨迹
    public static final String URL_MOJI_WEATHER_HOURS= "/whapi/json/aliweather/forecast24hours";
    public static final String TOKEN_MOJI_WEATHER_HOURS= "1b89050d9f64191d494c806f78e8ea36";
    //天气预报15天
    public static final String URL_MOJI_WEATHER_DAYS = "/whapi/json/aliweather/forecast15days";
    public static final String TOKEN_MOJI_WEATHER_DAYS= "7538f7246218bdbf795b329ab09cc524";
    //天气预报-生活指数
    public static final String URL_MOJI_WEATHER_LIVE="/whapi/json/aliweather/index";
    public static final String TOKEN_MOJI_WEATHER_LIVE = "42b0c7e2e8d00d6e80d92797fe5360fd";

}
