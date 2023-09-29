package cn.wind.WebScoketDom.controller;

import cn.wind.WebScoketDom.entity.User;
import cn.wind.WebScoketDom.utills.ReturnResult;
import cn.wind.WebScoketDom.utills.UUIDUtil;
import cn.wind.WebScoketDom.utills.UsrMapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wind on 2016/11/21.
 */
@Controller
@RequestMapping("/*")
public class HomeController {
    @RequestMapping("home.do")
    public String toHome(HttpServletRequest request){
        return "login";
    }

    //用户登陆
    @RequestMapping("login.do")
    public String doLogin(User user, HttpServletRequest request){
        ReturnResult r = new ReturnResult();
        if(user.getName()==null|| StringUtils.isEmpty(user.getName())){
            request.setAttribute("error", "行走江湖前，先给自己起个响亮的名字吧");
            return "login";
        }else if(UsrMapUtil.findUserYON(user.getName())){
            request.setAttribute("error", "您的名字太热了，换个其他的试试");
            return "login";
        }
        user.setId(UUIDUtil.newUUID());
        UsrMapUtil.addUser(user);
        request.getSession().setAttribute("uid",user.getId());
        request.getSession().setAttribute("name",user.getName());
        request.setAttribute("uid", user.getId());
        request.setAttribute("uname", user.getName());
        return "talk";
    }
}
