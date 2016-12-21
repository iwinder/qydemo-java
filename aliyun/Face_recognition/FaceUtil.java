package cn.zhonya.FaceUtil.utils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云人脸识别Util
 * Created by wind on 2016/12/20.
 */
public class FaceUtil {
    private static Logger logger = Logger.getLogger(FaceUtil.class);
    //性别api地址
    private static String host = "https://dm-22.data.aliyun.com";
    private static String path = "/rest/160601/face/gender_detection.json";
    //年龄api地址
    private static String  host2 = "https://dm-23.data.aliyun.com";
    private static String  path2 = "/rest/160601/face/age_detection.json";

    private static String method = "POST";
    private static String APPCODE = "自己的APPCODE";

    /**
     * 获取性别或年龄
     * @param data 图片Base64编码的字符串
     * @param type 1为性别 2为年龄
     * @return JSONObject类型结果 其中参数有 code和msg
     *          code 0（请求或编码错误） -1（获取年龄错误） -2（获取性别错误） 1（成功）
     *          msg  code>0    JSONObject
     *                   num-识别出来的数量
     *                   gender 性别数组（根据type决定是否存在）
     *                   age 年龄数组  （根据type决定是否存在）
     *               code<=0  String 错误信息
     */
    public static JSONObject getSexOrAgeOfFace(String data,int type){
        JSONObject r = new JSONObject();
        if(data!=null){
            Map<String, String> headers = new HashMap<String, String>();
            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
            headers.put("Authorization", "APPCODE "+APPCODE);
            Map<String, String> querys = new HashMap<String, String>();
            try {
                String bodys = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\""+data+"\"}}]}";
                /**
                 * 重要提示如下:
                 * HttpUtils请从
                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
                 * 下载
                 *
                 * 相应的依赖请参照
                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
                 */
                HttpResponse response = null;
                if(type==1){
                    response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
                }else{
                    response = HttpUtils.doPost(host2, path2, method, headers, querys, bodys);
                }
				
                //获取response的head
                String shead = response.toString();
                int sstar = shead.indexOf("X-Ca-Error-Message");
                if(sstar>=0){
                    //响应参数有错误
                    int send = shead.indexOf("]");
                    String tmpShead = shead.substring(sstar,send);
                    String [] tmpSA = tmpShead.split(", ");
                    String [] tmpSt = null;
                    JSONObject sts = new JSONObject();
                    for(String st : tmpSA){
                        tmpSt = st.split(":");
                        sts.put(tmpSt[0],tmpSt[1]);
                    }
                    r.put("code",0);
                    r.put("msg",sts.getString("X-Ca-Error-Message"));
                    return  r;
                }

                String ss = EntityUtils.toString(response.getEntity());
                JSONObject ts1 =new JSONObject(ss);
                logger.info("response sby:"+ts1.toString());
                if(ts1.has("kInvalidArgument")){
                    //一般为图片编码错误
                    r.put("code",0);
                    r.put("msg",ts1.getString("kInvalidArgument"));
                    return r;
                }
                //获取response的body
                JSONObject ts =ts1.getJSONArray("outputs").getJSONObject(0).getJSONObject("outputValue");
                String std = (String) ts.get("dataValue");
                JSONObject tha = new JSONObject(std);

                if(tha.getInt("errno")>=0){
                    r.put("code",1);
                    JSONObject tmpN = new JSONObject();
                    tmpN.put("num",tha.getInt("number"));
                    if(type==1){
                        tmpN.put("gender",tha.getJSONArray("gender"));
                        r.put("msg",tmpN);
                    }else{
                        tmpN.put("age",tha.getJSONArray("age"));
                        r.put("msg",tmpN);
                    }
                }else{
                    if(type==1){
                        r.put("code",-1);
                        r.put("msg","天太黑了，没认出男女QAQ");
                    }else{
                        r.put("code",-2);
                        r.put("msg","天太黑了，没认出年龄QAQ");
                    }
                }
                return  r;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        r.put("code",0);
        r.put("msg","请先上传图片啊QAQ");
        return r;
    }

    /**
     * 将图片byte[]类型数据转为Base64编码的字符串
     * @param dataByte 图片byte[]类型数据
     * @return
     */
    public static String byteToBASE64String(byte[] dataByte){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(dataByte);
    }




    /**
     * 根据图片网络地址获取图片的byte[]类型数据
     * @param urlPath 图片网络地址
     * @return 图片数据
     */
    public static byte[] getImageFromURL(String urlPath) {
        byte[] data = null;
        InputStream is = null;
        URLConnection conn = null;
        HttpURLConnection httpUrlConnection  = null;
        try {
            URL url = new URL(urlPath);
            conn =  url.openConnection();
            conn.setDoInput(true);
            conn.setConnectTimeout(6000);
            is = conn.getInputStream();
            httpUrlConnection  =  (HttpURLConnection)conn;
            if (httpUrlConnection.getResponseCode() == 200) {
                data = readInputStream(is);
            } else{
                data=null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (httpUrlConnection != null) httpUrlConnection.disconnect();
        }
        return data;
    }

    /**
     * 读取InputStream数据，转为byte[]数据类型
     * @param is InputStream数据
     * @return 返回byte[]数据
     */
    public static byte[] readInputStream(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = -1;
        try {
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = baos.toByteArray();
        try {
            is.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }



    /**
     * 同时识别性别和年龄
     * @param data  图片Base64编码的字符串
     * @return  JSONObject类型的结果 如
     *          成功：data:{code:1，msg:{age:{num:1,age:[25]},gender:{ num:1,gender:[1]}}}
     *          失败：data:{code:-1，msg:"天太黑了，没认出男女QAQ"}
     */
    public static JSONObject getSexAndAgeOfResult(String data){
        JSONObject r = null;
        JSONObject rs = new JSONObject();
        r = FaceUtil.getSexOrAgeOfFace(data,1);
        JSONObject rs2 = new JSONObject();
        if(r.getInt("code")>0&&r.getJSONObject("msg").getInt("num")>0){
            rs2.put("gender",r.getJSONObject("msg"));
            r = FaceUtil.getSexOrAgeOfFace(data,2);
            if(r.getInt("code")>0&&r.getJSONObject("msg").getInt("num")>0){
                rs.put("code",1);
                rs2.put("age",r.getJSONObject("msg"));
                rs.put("msg",rs2);
            }else{
                rs.put("code",-2);
                rs.put("msg",r.getString("msg"));
            }
        }else{
            rs.put("code",-1);
            rs.put("msg",r.getString("msg"));
        }
        return rs;
    }
}
