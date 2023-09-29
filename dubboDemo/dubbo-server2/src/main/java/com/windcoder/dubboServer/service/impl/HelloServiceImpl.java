package com.windcoder.dubboServer.service.impl;

import com.windcoder.dubbo.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: wind
 * Date: 2017-08-30
 * Time: 16:47 下午
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return name + "  say hello  Dubbo By windCoder.com!~";
    }
}
