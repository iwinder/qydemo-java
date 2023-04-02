package com.windcoder.thinking.in.spring.ioc.overview.dependency.injection;

import com.windcoder.thinking.in.spring.ioc.overview.annotation.Super;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import com.windcoder.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * 依赖注入示例
 *
 * 1. 通过名称的方式查找
 *
 * 2. 通过类型的方式查找
 *      包括单个类型和集合类型
 * 3. 通过注解的方式查找
 *
 * 依赖注入的来源和依赖查找的来源不是同一个。
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // 配置 xml配置文件
        // 启动  Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        System.out.println(beanFactory);
        // ApplicationContext可以替换BeanFactory
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        // 依赖来源一：自定义Bean
        UserRepository userRepsitory = beanFactory.getBean("userRepository",UserRepository.class);
        // user通过ObjectFactoryCreatingFactoryBean注入
        System.out.println(userRepsitory.getUsers());

        // 依赖来源二：内建依赖 BeanFactory
        // 依赖注入
        // userRepsitory的BeanFactory与beanFactory不等，同时不是no也不是一个bean对象，是一个内建对象
        // userRepsitory的BeanFactory基于xml中ObjectFactoryCreatingFactoryBean注入，得到对象DefaultListableBeanFactory
        System.out.println(userRepsitory.getBeanFactory());
        System.out.println(userRepsitory.getBeanFactory() == beanFactory);
        // 依赖查找
        // BeanFactory对象不属于内建Bean对象，若是，完全可以通过getBean()的通过类型的方式查出来。
        // userRepsitory的BeanFactory如果是bean,可以通过依赖查找BeanFactory获取到，但此时未找到，故不是
    //        System.out.println(beanFactory.getBean(BeanFactory.class));


       ObjectFactory userFactory = userRepsitory.getUserObjectFactory();
       // 因为SuperUser定义了primary属性，肯定获取的是SuperUser对象
       System.out.println(userFactory.getObject());
        System.out.println(userFactory.getObject() == beanFactory);

       ObjectFactory objectFactory  = userRepsitory.getObjectFactory();
       System.out.println(objectFactory.getObject());
       // 说明ObjectFactory autowire时注入了一个ApplicationContext，ApplicationContext也是一个BeanFactory
        System.out.println(objectFactory.getObject() == beanFactory);


        // 依赖来源三：容器内建Bean
        // Environment是一个外部配置和profile的综合体
        // 涉及Environment抽象
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean:" + environment);
    }


    private static void whoIsIoCContainer(UserRepository userRepository, BeanFactory beanFactory) {
        // 表达式为何不成立。（按感官印象注入的beanFactory应该和外面容器的beanFactory是一个）
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        // Application 是一个 BeanFactory,但是两个不同的对象。
        // BeanFactory接口提供了一些高级配置的一个机制能够来管理对象。
        // 按正常的继承关系 ConfigurableApplicationContext <- ApplicationContext <- BeanFactory
        // 但实际 ConfigurableApplicationContext#getBeanFacotry()获取。

        // ClassPathXmlApplicationContext <- AbstractXmlApplicationContext <- AbstractXmlApplicationContext <- AbstractRefreshableConfigApplicationContext <-
        //  AbstractRefreshableApplicationContext <-  AbstractApplicationContext <- ConfigurableApplicationContext <- ApplicationContext
        // ConfigurableApplicationContext#setParent()配置ApplicationContext，但没有setBaeanFactory的方法。
        //  ConfigurableApplicationContext#getBeanFacotry()实现在AbstractRefreshableApplicationContext中可看到通过组合方式DefaultListableBeanFactory beanFactory
        // getBean是AbstractRefreshableApplicationContext中
    }



}
