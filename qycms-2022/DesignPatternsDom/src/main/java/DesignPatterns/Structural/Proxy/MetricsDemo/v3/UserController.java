package DesignPatterns.Structural.Proxy.MetricsDemo.v3;

import DesignPatterns.DesignConcept.Aggregator.v1.UserVo;

public class UserController {
    public UserVo login(String telephone, String password) {
        UserVo user = new UserVo();
        // ... 省略相关逻辑
        return user;
    }

    public UserVo register(String telephone, String password) {
        UserVo user = new UserVo();
        // ... 省略相关逻辑
        return user;
    }
}
