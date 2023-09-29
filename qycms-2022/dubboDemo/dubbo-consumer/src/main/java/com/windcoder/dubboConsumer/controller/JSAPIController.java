package com.windcoder.dubboConsumer.controller;

import com.windcoder.dubbo.service.HelloService;
import com.windcoder.dubbo.service.TestService;
import com.windcoder.dubbo.service.WindCoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 * User: wind
 * Date: 2017-08-30
 * Time: 14:57 下午
 */
@Controller("/*")
public class JSAPIController {
    @Autowired
    private TestService testService;
    @Autowired
    private HelloService helloService;
    @Autowired
    private WindCoderService windCoderService;

    @RequestMapping(value = "/test.do")
    @ResponseBody
    public String testSay(@RequestParam(value = "name",defaultValue = "") String name){
        StringBuffer sb = new StringBuffer();
        sb.append("Dubbo: ").append(testService.sayHello(name));
        return sb.toString();
    }
    @RequestMapping(value = "/hello.do")
    @ResponseBody
    public String helloSay(@RequestParam(value = "name",defaultValue = "") String name){
        StringBuffer sb = new StringBuffer();
        sb.append("Dubbo: ").append(helloService.sayHello(name));
        return sb.toString();
    }

    @RequestMapping(value = "/wind.do")
    @ResponseBody
    public String windCoderService(@RequestParam(value = "name",defaultValue = "") String name){
        StringBuffer sb = new StringBuffer();
        sb.append("Dubbo: ").append(windCoderService.sayHello(name));
        return sb.toString();
    }


}
