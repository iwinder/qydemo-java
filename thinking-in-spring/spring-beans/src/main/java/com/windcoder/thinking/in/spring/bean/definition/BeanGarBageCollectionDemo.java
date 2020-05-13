package com.windcoder.thinking.in.spring.bean.definition;

import com.windcoder.thinking.in.spring.bean.definition.factory.abstractFactory.IUserFactory;
import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  Bean 垃圾回收（GC）示例
 */
public class BeanGarBageCollectionDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class(配置类)
        //  当 Class 被 register 之后，标注 @Configuration 与否都不在重要
        applicationContext.register(BeanInitializationDemo.class);

        // 启动Spring 应用上下文
        applicationContext.refresh();

        // 非延迟初始化在 String 应用上下文启动完成后，被初始化
//        PrintZUtill.println("Spring 应用上下文已经启动...");
//
//        PrintZUtill.println("Spring 应用上下文准备关闭...");
        // 关闭 Spring 应用上下文
        applicationContext.close();
        PrintZUtill.println("Spring 应用上下文已经关闭...");
        Thread.sleep(5000L);
        // 强制触发 GC
        System.gc();
        Thread.sleep(5000L);
    }
}
