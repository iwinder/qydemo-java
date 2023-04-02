package DesignPatterns.Structural.Proxy.MetricsDemo.v1;

import DesignPatterns.DesignConcept.Aggregator.v1.UserVo;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestInfo;
import DesignPatterns.DesignConcept.Aggregator.v5.MetricsCollector;

/**
 * 直接使用性能计数器 MetricsCollector
 * 存在问题：
 * 1. 性能计数器框架代码入侵到业务代码，跟业务代码高度耦合
 * 2. 收集接口请求的代码与业务代码无关，本身不应该放在一个类中。业务类最好职责更加单一，只聚焦业务处理。
 */
public class UserController {

    private MetricsCollector metricsCollector; // 假设通过依赖注入注入

    public UserVo login(String telephone, String password) {
        long startTimeStamp = System.currentTimeMillis();
        UserVo user = new UserVo();
        // ...
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimeStamp;
        RequestInfo requestInfo = new RequestInfo("login", (double) responseTime, startTimeStamp);
        metricsCollector.recordRequest(requestInfo);
        // ... 返回 UserVo 数据
        return user;
    }

    public UserVo register(String telephone, String password) {
        long startTimeStamp = System.currentTimeMillis();
        UserVo user = new UserVo();
        // ...
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimeStamp;
        RequestInfo requestInfo = new RequestInfo("register", (double) responseTime, startTimeStamp);
        metricsCollector.recordRequest(requestInfo);
        // ... 返回 UserVo 数据
        return user;
    }
}
