package DesignPatterns.Creational.Factory.geekbang.DIContainer;

import DesignPatterns.Creational.Factory.geekbang.DIContainer.applicationContexts.ApplicationContext;
import DesignPatterns.Creational.Factory.geekbang.DIContainer.applicationContexts.ClassPathXmlApplicationContext;
import DesignPatterns.Creational.Factory.geekbang.DIContainer.entity.RateLimiter;

/**
 * 最小原型使用示例
 */
public class DIDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("di-beans.xml");
        RateLimiter rateLimiter = (RateLimiter)applicationContext.getBean("rateLimiter");
        rateLimiter.test();
        // ...
    }
}
