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



}
