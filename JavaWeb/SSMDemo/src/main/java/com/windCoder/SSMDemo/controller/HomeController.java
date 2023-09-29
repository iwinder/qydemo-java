package com.windCoder.SSMDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * User: windCoder
 * Date: 2017-08-11
 * Time: 16:53 下午
 */
@Controller
@RequestMapping("/*")
public class HomeController {

    @RequestMapping("home.do")
    public String home(HttpServletRequest request) {

        return "home";
    }

    @RequestMapping("admin/login.do")
    public String login(HttpServletRequest request) {

        return "admin/login";
    }

    @RequestMapping("admin/home.do")
    public String adminHome(HttpServletRequest request) {

        return "admin/home";
    }

    @RequestMapping("admin/user/home.do")
    public String adminUserHome(HttpServletRequest request) {

        return "admin/user/home";
    }
    @RequestMapping("admin/user/add.do")
    public String adminUserAdd(HttpServletRequest request) {

        return "admin/user/add";
    }
    @RequestMapping("admin/post/home.do")
    public String adminPostHome(HttpServletRequest request) {

        return "admin/post/home";
    }
    @RequestMapping("admin/post/add.do")
    public String adminPostAdd(HttpServletRequest request) {

        return "admin/post/add";
    }



}
