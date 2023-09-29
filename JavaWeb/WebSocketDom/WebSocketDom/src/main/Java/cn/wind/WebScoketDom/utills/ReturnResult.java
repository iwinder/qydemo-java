package cn.wind.WebScoketDom.utills;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by wind on 2016/11/20.
 */
public class ReturnResult implements Serializable {
    private int code = 0;
    private String msg = null;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toJsonString(){

        JSONObject json = new JSONObject(this);
        return json.toString();
    }
}
