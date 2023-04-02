package com.windcoder.thinking.in.spring.bean.definition;

import com.windcoder.thinking.in.spring.bean.definition.factory.UserFacotry;
import com.windcoder.thinking.in.spring.bean.definition.factory.abstractFactory.DefaultUserFactory;
import com.windcoder.thinking.in.spring.bean.definition.factory.abstractFactory.IUserFactory;
import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  单体 Bean 注册示例
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();
        // 创建一个外部 UserFactory 对象
        IUserFactory userFactory = new DefaultUserFactory();
//        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        SingletonBeanRegistry beanFactory = applicationContext.getBeanFactory();
        // 注册外部单例对象  Spring 生命周期处理，对 registrySingleton 注册对象没有任何作用
        beanFactory.registerSingleton("userFactory", userFactory);

        // 启动Spring 应用上下文
        applicationContext.refresh();

        // 非延迟初始化在 String 应用上下文启动完成后，被初始化

        PrintZUtill.println("Spring 应用上下文已经关闭...");
        // 通过依赖查找的方式获取 UserFactory
//        IUserFactory userFactoryByLookup = beanFactory.getBean("userFactory", IUserFactory.class);
        IUserFactory userFactoryByLookup = applicationContext.getBean("userFactory", IUserFactory.class);
        PrintZUtill.println("userFactory == userFactoryByLookup:" + (userFactory == userFactoryByLookup));
        // 关闭 Spring 应用上下文,一旦关闭，bean就会销毁
        applicationContext.close();

    }
}
