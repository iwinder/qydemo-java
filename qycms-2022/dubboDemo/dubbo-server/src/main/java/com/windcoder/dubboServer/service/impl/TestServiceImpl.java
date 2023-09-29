package com.windcoder.dubboServer.service.impl;


import com.windcoder.dubbo.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: wind
 * Date: 2017-08-30
 * Time: 11:59 上午
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    public String sayHello(String name) {
        return name + " service say hello Test service By windCoder.com!";
    }
}
