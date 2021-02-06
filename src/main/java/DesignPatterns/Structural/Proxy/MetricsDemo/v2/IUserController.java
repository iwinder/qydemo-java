package DesignPatterns.Structural.Proxy.MetricsDemo.v2;

import DesignPatterns.DesignConcept.Aggregator.v1.UserVo;

/**
 * 代理类 UserControllerProxy 和原始类 UserController 都实现相同的接口IUserController
 */
public interface IUserController {
    UserVo login(String telephone, String password);
    UserVo register(String telephone, String password);
}
