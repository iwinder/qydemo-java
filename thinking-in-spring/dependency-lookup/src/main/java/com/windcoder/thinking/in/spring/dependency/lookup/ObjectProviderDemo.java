package com.windcoder.thinking.in.spring.dependency.lookup;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

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
        // 关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public String helloWorld() { // 方法名就是 Bean 名称 = “helloWorld”
        return "Hello World";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        // 通常 String 类型的 Bean,Spring 上下文是不会定义的，所以这里可以放心直接用
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        // getObject方法来自 ObjectFactory，ObjectProvider 继承  ObjectFactory 实现的
        PrintZUtill.println(objectProvider.getObject());
    }
}
