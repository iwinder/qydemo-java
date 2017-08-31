package com.windcoder.dubboServer.service.impl;

import com.windcoder.dubbo.service.HeHeService;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: wind
 * Date: 2017-08-30
 * Time: 17:14 下午
 */
@Service("heHeService")
public class HeheServiceImpl implements HeHeService {
    @Override
    public String okSay(String name) {
        return name + "  傻傻分不清楚";
    }
}
