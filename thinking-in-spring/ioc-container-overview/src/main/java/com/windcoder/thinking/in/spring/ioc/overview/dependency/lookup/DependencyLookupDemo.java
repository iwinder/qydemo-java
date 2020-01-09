package com.windcoder.thinking.in.spring.ioc.overview.dependency.lookup;

import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖查找示例
 *
 * 1. 通过名称的方式查找
 *
 * 2. 通过类型的方式查找
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        // 配置 xml配置文件
        // 启动  Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        lookupByType(beanFactory);

        lookupInLazy(beanFactory);
        lookupInRealTime(beanFactory);

    }

    private static void  lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找" + user);
    }
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" +  user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        // 老版本，3.0之前不支持泛型，需要强转
        User user = (User)beanFactory.getBean("user");
        System.out.println("实时查找" + user);
    }
}
