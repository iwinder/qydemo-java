package com.windcoder.thinking.in.spring.dependency.lookup;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Iterator;

/**
 *  BeanProvider 延迟依赖查找 示例
 *
 *  {@link com.windcoder.thinking.in.spring.ioc.overview.dependency.lookup.DependencyLookupDemo} 的扩充
 *
 */
public class ObjectProviderDemo { // @Configuration  是非必须的
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();
        // 将当前类 AnnotationApplicationContextAsIoCContainerDemo 作为配置类（Configuration Class）
        applicationContext.register(ObjectProviderDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 依赖查找集合对象
        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);
        // 关闭应用上下文
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        // ObjectProvider 实现Iterable，并覆盖了Iterable遍历的方法。
        // 输出当前上下文中的所有String类型的集合
        // 与集合查找的不同之处在于：目前的实现方案是一个延迟加载的
        // 目前事实，但可以实现延迟
//        Iterable<String> stringIterable = objectProvider;
//        for (String str: stringIterable) {
//            PrintZUtill.println(str);
//        }
        // Stream -> Method reference
        objectProvider.stream().forEach(System.out::println);
    }

    /**
     *  当 Bean 不存在时
     * @param applicationContext
     */
    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        // 当不存在时调用createUser() 方法来进行操作
        User user = objectProvider.getIfAvailable(  User:: createUser);
        PrintZUtill.println("当前 User 对象: " + user);
    }

    @Bean
    @Primary
    public String helloWorld() { // 方法名就是 Bean 名称 = “helloWorld”
        return "Hello World";
    }

    @Bean
    public String message() {
        return "message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        // 通常 String 类型的 Bean,Spring 上下文是不会定义的，所以这里可以放心直接用
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        // getObject方法来自 ObjectFactory，ObjectProvider 继承  ObjectFactory 实现的
        PrintZUtill.println(objectProvider.getObject());
    }
}
