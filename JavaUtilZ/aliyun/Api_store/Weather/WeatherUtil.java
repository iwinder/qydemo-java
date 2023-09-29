package cn.zhonya.FaceUtil.utils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wind on 2017/1/9.
 */
public class WeatherUtil {
    private static Logger logger = Logger.getLogger(WeatherUtil.class);

    public static void main(String[] args) {
        System.out.println("获取："+getIcoOfDay("多云","conditionDay"));
        String lat ="38.042307";
        String lon ="114.51486";
        getWeatherData(lat,lon,"days");
    }

    /**
     * 获取天气相关数据
     * @param lat 纬度  如：38.042307
     * @param lon 经度  如：114.51486
     * @param type 数据类型
     *             hours 天气预报24小时
     *             days 天气预报15天
     *             actual 天气实况
     *             live 生活指数
     * @return
     */
    public static JSONObject getWeatherData(String lat,String lon,String type){
        JSONObject r = new JSONObject();
        String path = null;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE "+ALiApiConfigUtil.APPCODE);
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("lat", lat);
        bodys.put("lon", lon);
        if(type.equals("hours")){
            path = ALiApiConfigUtil.URL_MOJI_WEATHER_HOURS;
            bodys.put("token", ALiApiConfigUtil.TOKEN_MOJI_WEATHER_HOURS);
        }else if(type.equals("days")){
            path = ALiApiConfigUtil.URL_MOJI_WEATHER_DAYS;
            bodys.put("token", ALiApiConfigUtil.TOKEN_MOJI_WEATHER_DAYS);
        }else if(type.equals("actual")){
            path = ALiApiConfigUtil.URL_MOJI_WEATHER_CONDITION;
            bodys.put("token", ALiApiConfigUtil.TOKEN_MOJI_WEATHER_CONDITION);
        }else if(type.equals("live")){
            path = ALiApiConfigUtil.URL_MOJI_WEATHER_LIVE;
            bodys.put("token", ALiApiConfigUtil.TOKEN_MOJI_WEATHER_LIVE);
        }else{
            r.put("code",0);
            r.put("msg","非法 type");
            return r;
        }
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
//            HttpResponse response = HttpUtils.doPost(HOST, path, method, headers, querys, bodys);
//            System.out.println(response.toString());
//            r= ALiErrorCoder.checkAliResError(response);
//            if(r.has("code")){
//                return r;
//            }
//            String ss = EntityUtils.toString(response.getEntity());
            String bodyObjStr="{\"code\":0,\"data\":{\"city\":{\"cityId\":284609,\"counname\":\"中国\",\"name\":\"东城区\",\"pname\":\"北京市\",\"timezone\":\"8\"},\"forecast\":[{\"conditionDay\":\"大部晴朗\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"阴\",\"moonphase\":\"WaxingGibbous\",\"moonrise\":\"2017-01-08 13:34:00\",\"moonset\":\"2017-01-09 03:41:00\",\"predictDate\":\"2017-01-08\",\"sunrise\":\"2017-01-08 07:36:00\",\"sunset\":\"2017-01-08 17:06:00\",\"tempDay\":\"7\",\"tempNight\":\"-5\",\"updatetime\":\"2017-01-08 23:10:05\",\"windDirDay\":\"西北风\",\"windDirNight\":\"北风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"5.0\",\"windSpeedNight\":\"5.0\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"大部晴朗\",\"moonphase\":\"WaxingGibbous\",\"moonrise\":\"2017-01-09 14:19:00\",\"moonset\":\"2017-01-10 04:49:00\",\"predictDate\":\"2017-01-09\",\"sunrise\":\"2017-01-09 07:36:00\",\"sunset\":\"2017-01-09 17:07:00\",\"tempDay\":\"5\",\"tempNight\":\"-5\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"西风\",\"windDirNight\":\"西北风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"5.0\",\"windSpeedNight\":\"5.0\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"moonphase\":\"WaxingGibbous\",\"moonrise\":\"2017-01-10 15:11:00\",\"moonset\":\"2017-01-11 05:55:00\",\"predictDate\":\"2017-01-10\",\"sunrise\":\"2017-01-10 07:36:00\",\"sunset\":\"2017-01-10 17:08:00\",\"tempDay\":\"3\",\"tempNight\":\"-10\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"西风\",\"windDirNight\":\"西风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"2\",\"windSpeedDay\":\"5.0\",\"windSpeedNight\":\"3.0\"},{\"conditionDay\":\"多云\",\"conditionIdDay\":\"1\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"moonphase\":\"WaxingGibbous\",\"moonrise\":\"2017-01-11 16:08:00\",\"moonset\":\"2017-01-12 06:55:00\",\"predictDate\":\"2017-01-11\",\"sunrise\":\"2017-01-11 07:36:00\",\"sunset\":\"2017-01-11 17:09:00\",\"tempDay\":\"5\",\"tempNight\":\"-7\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"西北风\",\"windDirNight\":\"北风\",\"windLevelDay\":\"2\",\"windLevelNight\":\"2\",\"windSpeedDay\":\"3.0\",\"windSpeedNight\":\"3.0\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"moonphase\":\"Full\",\"moonrise\":\"2017-01-12 17:11:00\",\"moonset\":\"2017-01-13 07:49:00\",\"predictDate\":\"2017-01-12\",\"sunrise\":\"2017-01-12 07:35:00\",\"sunset\":\"2017-01-12 17:10:00\",\"tempDay\":\"4\",\"tempNight\":\"-7\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"西风\",\"windDirNight\":\"西北风\",\"windLevelDay\":\"2\",\"windLevelNight\":\"2\",\"windSpeedDay\":\"3.0\",\"windSpeedNight\":\"3.0\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"moonphase\":\"WaningGibbous\",\"moonrise\":\"2017-01-13 18:16:00\",\"moonset\":\"2017-01-14 08:35:00\",\"predictDate\":\"2017-01-13\",\"sunrise\":\"2017-01-13 07:35:00\",\"sunset\":\"2017-01-13 17:11:00\",\"tempDay\":\"2\",\"tempNight\":\"-9\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"东风\",\"windDirNight\":\"东北风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"2\",\"windSpeedDay\":\"5.0\",\"windSpeedNight\":\"3.0\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"moonphase\":\"WaningGibbous\",\"moonrise\":\"2017-01-14 19:23:00\",\"moonset\":\"2017-01-15 09:16:00\",\"predictDate\":\"2017-01-14\",\"sunrise\":\"2017-01-14 07:35:00\",\"sunset\":\"2017-01-14 17:12:00\",\"tempDay\":\"1\",\"tempNight\":\"-9\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"东北风\",\"windDirNight\":\"东北风\",\"windLevelDay\":\"2\",\"windLevelNight\":\"2\",\"windSpeedDay\":\"3.0\",\"windSpeedNight\":\"3.0\"},{\"conditionDay\":\"多云\",\"conditionIdDay\":\"1\",\"conditionIdNight\":\"31\",\"conditionNight\":\"多云\",\"moonphase\":\"WaningGibbous\",\"moonrise\":\"2017-01-15 20:28:00\",\"moonset\":\"2017-01-16 09:52:00\",\"predictDate\":\"2017-01-15\",\"sunrise\":\"2017-01-15 07:35:00\",\"sunset\":\"2017-01-15 17:13:00\",\"tempDay\":\"1\",\"tempNight\":\"-11\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"东北风\",\"windDirNight\":\"东风\",\"windLevelDay\":\"2\",\"windLevelNight\":\"2\",\"windSpeedDay\":\"3.0\",\"windSpeedNight\":\"3.0\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"moonphase\":\"WaningGibbous\",\"moonrise\":\"2017-01-16 21:31:00\",\"moonset\":\"2017-01-17 10:25:00\",\"predictDate\":\"2017-01-16\",\"sunrise\":\"2017-01-16 07:34:00\",\"sunset\":\"2017-01-16 17:14:00\",\"tempDay\":\"1\",\"tempNight\":\"-9\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"东北风\",\"windDirNight\":\"北风\",\"windLevelDay\":\"1\",\"windLevelNight\":\"1\",\"windSpeedDay\":\"1.0\",\"windSpeedNight\":\"1.0\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"31\",\"conditionNight\":\"多云\",\"moonphase\":\"WaningGibbous\",\"moonrise\":\"2017-01-17 22:31:00\",\"moonset\":\"2017-01-18 10:55:00\",\"predictDate\":\"2017-01-17\",\"sunrise\":\"2017-01-17 07:34:00\",\"sunset\":\"2017-01-17 17:15:00\",\"tempDay\":\"2\",\"tempNight\":\"-8\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"西北风\",\"windDirNight\":\"东北风\",\"windLevelDay\":\"2\",\"windLevelNight\":\"1\",\"windSpeedDay\":\"3.0\",\"windSpeedNight\":\"1.0\"},{\"conditionDay\":\"多云\",\"conditionIdDay\":\"1\",\"conditionIdNight\":\"30\",\"conditionNight\":\"大部晴朗\",\"moonphase\":\"WaningGibbous\",\"moonrise\":\"2017-01-18 23:30:00\",\"moonset\":\"2017-01-19 11:26:00\",\"predictDate\":\"2017-01-18\",\"sunrise\":\"2017-01-18 07:33:00\",\"sunset\":\"2017-01-18 17:16:00\",\"tempDay\":\"2\",\"tempNight\":\"-8\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"东风\",\"windDirNight\":\"西南风\",\"windLevelDay\":\"1\",\"windLevelNight\":\"1\",\"windSpeedDay\":\"1.0\",\"windSpeedNight\":\"1.0\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"moonphase\":\"Last\",\"moonrise\":\"None\",\"moonset\":\"2017-01-19 11:26:00\",\"predictDate\":\"2017-01-19\",\"sunrise\":\"2017-01-19 07:33:00\",\"sunset\":\"2017-01-19 17:18:00\",\"tempDay\":\"2\",\"tempNight\":\"-10\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"西北风\",\"windDirNight\":\"西北风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"5.0\",\"windSpeedNight\":\"5.0\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"moonphase\":\"WaningCrescent\",\"moonrise\":\"2017-01-20 00:27:00\",\"moonset\":\"2017-01-20 11:56:00\",\"predictDate\":\"2017-01-20\",\"sunrise\":\"2017-01-20 07:32:00\",\"sunset\":\"2017-01-20 17:19:00\",\"tempDay\":\"0\",\"tempNight\":\"-10\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"西北风\",\"windDirNight\":\"西北风\",\"windLevelDay\":\"4\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"7.0\",\"windSpeedNight\":\"5.0\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"moonphase\":\"WaningCrescent\",\"moonrise\":\"2017-01-21 01:23:00\",\"moonset\":\"2017-01-21 12:27:00\",\"predictDate\":\"2017-01-21\",\"sunrise\":\"2017-01-21 07:32:00\",\"sunset\":\"2017-01-21 17:20:00\",\"tempDay\":\"1\",\"tempNight\":\"-8\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"西北风\",\"windDirNight\":\"西风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"1\",\"windSpeedDay\":\"5.0\",\"windSpeedNight\":\"1.0\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"moonphase\":\"WaningCrescent\",\"moonrise\":\"2017-01-22 02:18:00\",\"moonset\":\"2017-01-22 13:01:00\",\"predictDate\":\"2017-01-22\",\"sunrise\":\"2017-01-22 07:31:00\",\"sunset\":\"2017-01-22 17:21:00\",\"tempDay\":\"3\",\"tempNight\":\"-3\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"西南风\",\"windDirNight\":\"西风\",\"windLevelDay\":\"1\",\"windLevelNight\":\"1\",\"windSpeedDay\":\"1.0\",\"windSpeedNight\":\"1.0\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"moonphase\":\"WaningCrescent\",\"moonrise\":\"2017-01-23 03:14:00\",\"moonset\":\"2017-01-23 13:39:00\",\"predictDate\":\"2017-01-23\",\"sunrise\":\"2017-01-23 07:31:00\",\"sunset\":\"2017-01-23 17:22:00\",\"tempDay\":\"2\",\"tempNight\":\"-10\",\"updatetime\":\"2017-01-09 19:10:12\",\"windDirDay\":\"西北风\",\"windDirNight\":\"北风\",\"windLevelDay\":\"2\",\"windLevelNight\":\"2\",\"windSpeedDay\":\"3.0\",\"windSpeedNight\":\"3.0\"}]},\"msg\":\"success\",\"rc\":{\"c\":0,\"p\":\"success\"}}";
            JSONObject bodyObj =new JSONObject(bodyObjStr);
            JSONObject dataObj = new JSONObject();
            if(bodyObj.has("code")){
                dataObj.put("city",bodyObj.getJSONObject("data").getJSONObject("city").getString("pname"));
                if(type.equals("days")){
                    JSONArray forecast = bodyObj.getJSONObject("data").getJSONArray("forecast");
                    System.out.println("ss:"+forecast.toString());
                    int length = forecast.length();
                    JSONObject forecastObj = null;
                    Integer iconCode = -2;
                    JSONArray newForecast= new JSONArray();
                    for(int i=0;i<length;i++){
                        forecastObj = forecast.getJSONObject(i);
                        iconCode = getIcoOfDay(forecastObj.getString("conditionDay"),"conditionDay");
                        forecastObj.put("iconDay",iconCode);
                        iconCode = getIcoOfDay(forecastObj.getString("conditionNight"),"conditionNight");
                        forecastObj.put("iconNight",iconCode);
                        newForecast.put(forecastObj);
                    }
                    dataObj.put("forecast",newForecast);
                    r.put("code",1);
                    r.put("msg",dataObj.toString());
                    return r;
                }else if(type.equals("hours")){
                    JSONArray hourly = bodyObj.getJSONObject("data").getJSONArray("hourly");
                    dataObj.put("hourly",hourly);
                    r.put("code",1);
                    r.put("msg",dataObj.toString());
                    return r;
                }else if(type.equals("actual")){
                    JSONObject actual = bodyObj.getJSONObject("data").getJSONObject("actual");
                    dataObj.put("actual",actual);
                    r.put("code",1);
                    r.put("msg",dataObj.toString());
                    return r;
                }else if(type.equals("live")){
                    JSONArray liveIndex = bodyObj.getJSONObject("data").getJSONArray("liveIndex");
                    dataObj.put("liveIndex",liveIndex);
                    r.put("code",1);
                    r.put("msg",dataObj.toString());
                    return r;
                }
            }else{
                r.put("code",0);
                r.put("msg","error"+bodyObjStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;

    }
//    public static String getHours(String lat,String lon){
//
//    }

//    public static String getDays(String lat,String lon){
//
//    }

    private static Integer getIcoOfDay(String name,String type){
        if(name.equals("晴")||name.equals("大部晴朗")||
                name.equals("Sunny")||name.equals("Clear")||
                name.equals("Mostly Sunny")||name.equals("Mostly Clear")||
                name.equals("晴天")||name.equals("晴時多雲")||
                name.equals("天晴")||name.equals("陽光充沛")||name.equals("大部晴朗")){
            if(type.equals("conditionNight")){
                return 30;
            }
            return 0;
        }else if(name.equals("多云")||name.equals("少云")||
                name.equals("Cloudy")||name.equals("Partly Cloudy")||
                name.equals("多雲")||name.equals("少雲")){
            if(type.equals("conditionNight")){
                return 31;
            }
            return 1;
        }else if(name.equals("阴")||name.equals("Overcast")||
                name.equals("陰天")||name.equals("天陰")){
            return 2;
        }else if(name.equals("阵雨")||name.equals("局部阵雨")||
                name.equals("小阵雨")||name.equals("强阵雨")||
                name.equals("Showers")||name.equals("Scattered Showers")||
                name.equals("Light Showers")||name.equals("Heavy Showers")||
                name.equals("陣雨")||name.equals("局地陣雨")||name.equals("小陣雨")||name.equals("強陣雨")||
                name.equals("驟雨")||name.equals("局部地區性驟雨")||name.equals("零散驟雨")||name.equals("間中有驟雨")){
            if(type.equals("conditionNight")){
                return 33;
            }
            return 3;
        }else if(name.equals("阵雪")||name.equals("小阵雪")){
            if(type.equals("conditionNight")){
                return 34;
            }
            return 13;
        }else if(name.equals("雾")||name.equals("冻雾")){
            if(type.equals("conditionNight")){
                return 32;
            }
            return 18;
        }else if(name.equals("沙尘暴")||name.equals("强沙尘暴")){
            if(type.equals("conditionNight")){
                return 36;
            }
            return 20;
        }else if(name.equals("浮尘")||name.equals("尘卷风")||name.equals("扬沙")){
            if(type.equals("conditionNight")){
                return 35;
            }
            return 29;
        }else if(name.equals("霾")){
            if(type.equals("conditionNight")){
                return 46;
            }
            return 45;
        }else if(name.equals("雷阵雨")||name.equals("雷电")||name.equals("雷暴")){
            return 4;
        }else if(name.equals("雷阵雨伴有冰雹")||name.equals("冰雹")||
                name.equals("冰针")||name.equals("冰粒")){
            return 5;
        }else if(name.equals("雨夹雪")){
            return 6;
        }else if(name.equals("小雨")||name.equals("小到中雨")){
            return 7;
        }else if(name.equals("中雨")||name.equals("雨")){
            return 8;
        }else if(name.equals("大雨")||name.equals("中到大雨")){
            return 9;
        }else if(name.equals("暴雨")||name.equals("大暴雨")||name.equals("特大暴雨")||name.equals("大到暴雨")){
            return 10;
        }else if(name.equals("小雪")){
            return 14;
        } else if (name.equals("中雪")||name.equals("雪")||name.equals("小到中雪")) {
            return 15;
        } else if (name.equals("大雪")) {
            return 16;
        }else if(name.equals("暴雪")){
            return 17;
        }else if(name.equals("冻雨")){
            return 19;
        }else{
            return -1;
        }
//        switch(name){
//            case "晴":
//                return 0;
//            case "大部晴朗":
//                return 0;
//            case "多云":
//                return 1;
//            case "少云":
//                return 1;
//            case "阴":
//                return 2;
//            case "阵雨":
//                return 3;
//            case "局部阵雨":
//                return 3;
//            case "小阵雨":
//                return 3;
//            case "强阵雨":
//                return 3;
//            case "阵雪":
//                return 3;
//            case "阵雪":
//                return 3;
//            default:
//                return -1;
//        }
    }


}
