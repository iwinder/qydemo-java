package com.windcoder.thinking.in.spring.bean.lifecycle;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.Bean;

/**
 *  注解 BeanDefinition 解析示例
 *  AnnotatedBeanDefinitionReader 是发现 Configuration Class，而 @Bean 则是通过解析 Configuration Class 内部处理的
 *  所以 AnnotatedBeanDefinitionReader不对 @Bean 方式声明的 Bean 对象 做解析
 *
 *  “配置”是将元数据准备，比如 XML <bean> 等，“解析”是将元数据转化为运行时数据，如 <bean> 编程 BeanDefinition
 */
public class AnnotatedBeanDefinitionParsingDemo {

    @Bean
    public User user1() {
        return new User();
    }

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 Java  注解的 BeanDefinition 的实现
        // AnnotatedBeanDefinitionReader 并没有实现  BeanDefinitionReader
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        // 注册当前类（非 @Component class）,本身可以注册多个
        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionCountAfter - beanDefinitionCountBefore;
        PrintZUtill.println("已加载 BeanDefinition 数量：" + beanDefinitionCount);
        // 普通的 Class 作为 Component 注册到 Spring Ioc 容器后，通常 Bean 名 为 annotatedBeanDefinitionParsingDemo
        // Bean 名称的生成来自于 BeanNameGenerator ，注解实现 AnnotationBeanNameGenerator
        AnnotatedBeanDefinitionParsingDemo demo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo", AnnotatedBeanDefinitionParsingDemo.class);
        PrintZUtill.println(demo);
    }
}
