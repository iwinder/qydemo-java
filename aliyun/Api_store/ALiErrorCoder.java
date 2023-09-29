package cn.zhonya.FaceUtil.utils;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

/**
 * Created by wind on 2017/1/9.
 */
public class ALiErrorCoder {
    public static JSONObject checkAliResError(HttpResponse response){
        String shead = response.toString();
        JSONObject r = new JSONObject();
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
        }
        return  r;
    }
}
