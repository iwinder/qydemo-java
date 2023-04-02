package DesignPatterns.Structural.Proxy.MetricsDemo.v3;

import DesignPatterns.DesignConcept.Aggregator.v1.UserVo;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestInfo;
import DesignPatterns.DesignConcept.Aggregator.v5.MetricsCollector;

/**
 * 直接创建业务代码的代理类，存在一些问题：
 * 1. 需要在代理类中，将原始类中的所有方法都重新实现一遍，并且为每个方法都附加相似的代码逻辑。
 * 2. 若需要添加的附加功能的类不止一个，需要针对每个类都创建一个代理类。
 * 这将导致项目中类的个数成倍增加，增加了代码维护成倍。并且每个代理类中的代码都有些像模板式的“重复”代码，也增加了不必要的开发成本。
 */
public class UserControllerProxy extends UserController{
    private MetricsCollector metricsCollector;

    public UserControllerProxy() {
            this.metricsCollector = new MetricsCollector();
    }

    public UserVo login(String telephone, String password) {

        // 委托
        UserVo userVo = super.login(telephone,password);
        // 附加逻辑
        long startTimeStamp = System.currentTimeMillis();
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimeStamp;
        RequestInfo requestInfo = new RequestInfo("login", (double) responseTime, startTimeStamp);
        metricsCollector.recordRequest(requestInfo);
        return userVo;
    }

    public UserVo register(String telephone, String password) {
        // 委托
        UserVo userVo = super.register(telephone,password);
        // 附加逻辑
        long startTimeStamp = System.currentTimeMillis();
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimeStamp;
        RequestInfo requestInfo = new RequestInfo("register", (double) responseTime, startTimeStamp);
        metricsCollector.recordRequest(requestInfo);
        return userVo;
    }
}
