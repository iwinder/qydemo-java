package com.windcoder.thinking.in.spring.ioc.overview.container;

import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 注解能力 {@link org.springframework.context.ApplicationContext} 作为 IoC 容器示例
 */
public class AnnotationApplicationContextAsIoCContainerDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();
        // 将当前类 AnnotationApplicationContextAsIoCContainerDemo 作为配置类（Configuration Class）
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 依赖查找集合对象
        lookupCollectionByType(applicationContext);
        // 关闭应用上下文
        applicationContext.close();
    }

    /**
     * 通过 Java 注解的方式，定义一个Bean
     * @return
     */
    @Bean
    public User user(){
        User user = new User();
        user.setId(1L);
        user.setName("windCoder");
        return user;
    }


    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 对象 集合对象：" + users);
        }
    }
}
