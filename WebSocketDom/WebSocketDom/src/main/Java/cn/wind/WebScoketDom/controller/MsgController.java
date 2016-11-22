package cn.wind.WebScoketDom.controller;




import cn.wind.WebScoketDom.entity.Message;
import cn.wind.WebScoketDom.entity.User;

import cn.wind.WebScoketDom.utills.ReturnResult;
import cn.wind.WebScoketDom.utills.StringUtilZ;
import cn.wind.WebScoketDom.utills.UUIDUtil;
import cn.wind.WebScoketDom.utills.UsrMapUtil;
import cn.wind.WebScoketDom.websocket.MyWebSocketHandler;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * Created by wind on 2016/11/20.
 */

@Controller
@RequestMapping("/msg/*")
public class MsgController {
    @Resource
    MyWebSocketHandler handler;
//    Map<String, User> users = new HashMap<String, User>();
    //模拟一些数据
//    @ModelAttribute
//    public  void setReqAndRes(){
//        User u1 = new User();
//        u1.setId(1L);
//        u1.setName("张三");
//        users.put(u1.getId(),u1);
//        User u2 = new User();
//        u2.setId(2L);
//        u2.setName("李四");
//        users.put(u2.getId(), u2);
//        User u3 = new User();
//        u3.setId(3L);
//        u3.setName("王五");
//        users.put(u3.getId(), u2);
//    }

    // 跳转到发布广播页面
    @RequestMapping(value = "broadcast.do", method = RequestMethod.GET)
    public String broadcast() {
        return "broadcast";
    }
    @ResponseBody
    @RequestMapping("getUserList.do")
    public String getUserList(HttpServletRequest request, HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "*");
        List<User> userList = UsrMapUtil.getUserList();
        int userListLen = userList.size();
        JSONArray js = new JSONArray(userList);
        JSONObject jb = new JSONObject();
        jb.put("num",userListLen);
        jb.put("objs",js);
        ReturnResult r = new ReturnResult();
        r.setCode(1);
        r.setMsg(jb.toString());
        return r.toJsonString();
    }


    // 发布系统广播（群发）
    @ResponseBody
    @RequestMapping("toBroadcast.do")
    public String tobroadcast(String text) throws IOException {
        Message msg = new Message();
        msg.setDate(StringUtilZ.getTimestampOfNow("yyyy-MM-dd HH:mm:ss"));
        msg.setFrom("admin");
        msg.setFromName("系统广播");
        msg.setTo("all");
        msg.setText(text);
        handler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
        return "sucess";
    }


}
