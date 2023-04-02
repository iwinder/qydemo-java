package DesignPatterns.Structural.Proxy.MetricsDemo.v2;

import DesignPatterns.DesignConcept.Aggregator.v1.UserVo;

/**
 * UserController 只负责业务逻辑
 */
public class UserController implements IUserController{
    @Override
    public UserVo login(String telephone, String password) {
        UserVo user = new UserVo();
        // ... 省略相关逻辑
        return user;
    }

    @Override
    public UserVo register(String telephone, String password) {
        UserVo user = new UserVo();
        // ... 省略相关逻辑
        return user;
    }
}
