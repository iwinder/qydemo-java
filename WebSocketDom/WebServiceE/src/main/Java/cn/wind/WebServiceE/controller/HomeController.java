package cn.wind.WebServiceE.controller;

import cn.wind.WebServiceE.entity.User;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wind on 2016/11/16.
 */
@Controller("/")
public class HomeController {


    @RequestMapping("login.do")
    public String login(User user, HttpServletRequest request){
        request.getSession().setAttribute("uid",user.getId());
        request.getSession().setAttribute("username",user.getUsername());
        return "take";
    }

    @RequestMapping("take.do")
    public String take(HttpServletRequest request){
        return "index";
    }
    @ResponseBody
    @RequestMapping("content_load")
    public String  content_load(HttpServletResponse response){
        JSONObject jsonObject = new JSONObject() ;
        return "";
    }
}
