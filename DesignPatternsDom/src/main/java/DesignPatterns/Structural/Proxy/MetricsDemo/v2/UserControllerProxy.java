package DesignPatterns.Structural.Proxy.MetricsDemo.v2;

import DesignPatterns.DesignConcept.Aggregator.v1.UserVo;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestInfo;
import DesignPatterns.DesignConcept.Aggregator.v5.MetricsCollector;

/**
 *  代理类UserControllerProxy 负责在业务代码执行前后附加其他逻辑代码，并通过委托的方式调用原始类来执行业务代码。
 *  参照基于接口而非实现编程的设计思想，将原始类对象替换为代理类对象的时候，为了让代码改动尽量少，故代理类和原始类需要实现相同的接口。
 */
public class UserControllerProxy implements IUserController{

    private MetricsCollector metricsCollector;
    private UserController userController;

    public UserControllerProxy(UserController userController) {
        this.userController = userController;
        this.metricsCollector = new MetricsCollector();
    }

    @Override
    public UserVo login(String telephone, String password) {

        // 委托
        UserVo userVo = userController.login(telephone,password);
        // 附加逻辑
        long startTimeStamp = System.currentTimeMillis();
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimeStamp;
        RequestInfo requestInfo = new RequestInfo("login", (double) responseTime, startTimeStamp);
        metricsCollector.recordRequest(requestInfo);
        return userVo;
    }

    @Override
    public UserVo register(String telephone, String password) {
        // 委托
        UserVo userVo = userController.register(telephone,password);
        // 附加逻辑
        long startTimeStamp = System.currentTimeMillis();
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimeStamp;
        RequestInfo requestInfo = new RequestInfo("register", (double) responseTime, startTimeStamp);
        metricsCollector.recordRequest(requestInfo);
        return userVo;
    }
}
