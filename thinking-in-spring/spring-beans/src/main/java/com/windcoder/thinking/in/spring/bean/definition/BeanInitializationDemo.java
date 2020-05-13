package com.windcoder.thinking.in.spring.bean.definition;

import com.windcoder.thinking.in.spring.bean.definition.factory.abstractFactory.DefaultUserFactory;
import com.windcoder.thinking.in.spring.bean.definition.factory.abstractFactory.IUserFactory;
import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 初始化Demo
 */
public class BeanInitializationDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class(配置类)
        //  当 Class 被 register 之后，标注 @Configuration 与否都不在重要
        applicationContext.register(BeanInitializationDemo.class);

        // 启动Spring 应用上下文
        applicationContext.refresh();

        // 非延迟初始化在 String 应用上下文启动完成后，被初始化
        PrintZUtill.println("Spring 应用上下文已经启动...");
        // 依赖查找
        IUserFactory userFactory = applicationContext.getBean(IUserFactory.class);
        PrintZUtill.println(userFactory.createUser());
        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory")
    @Lazy
    public IUserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
