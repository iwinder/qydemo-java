package com.windcoder.thinking.in.spring.bean.definition;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationDemo {

    public static void main(String[] args) {
        // 配置XMl 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META_INF/bean-instantiation-context.xml");
        User user = beanFactory.getBean("user-by-static-method", User.class);
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        User userByFactoryMethod = beanFactory.getBean("user-by-factory-bean", User.class);
        // 因为两者是不同的方面进行创建的，所以不相等
        PrintZUtill.println(user);
        PrintZUtill.println(userByInstanceMethod);
        PrintZUtill.println(userByFactoryMethod);
        PrintZUtill.println(user==userByInstanceMethod);
        PrintZUtill.println(user==userByFactoryMethod);

    }
}
