package com.windcoder.thinking.in.spring.ioc.overview.dependency.lookup;

import com.windcoder.thinking.in.spring.ioc.overview.annotation.Super;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 *
 * 1. 通过名称的方式查找
 *
 * 2. 通过类型的方式查找
 *      包括单个类型和集合类型
 * 3. 通过注解的方式查找
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        // 配置 xml配置文件
        // 启动  Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");


        // 按名称延迟查找
        lookupInLazy(beanFactory);
        lookupInRealTime(beanFactory);

        // 按类型查找
        lookupByType(beanFactory);
        lookupCollectionByType(beanFactory);

        // 通过注解查找
        lookupByAnnotationType(beanFactory);

    }
    /**
     * 通过注解查找
     *
     * Spring底层做了过滤，从而可以只查到superUser
     * @param beanFactory
     */
    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
                System.out.println("查找标注 @Super  所有的 User 对象 集合对象：" + users);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 对象 集合对象：" + users);
        }
    }

    private static void  lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找" + user);
    }
    private static void lookupInLazy(BeanFactory beanFactory) {
        // ObjectFactory不会生成新的bean,factoryBean会生成新的bean
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
