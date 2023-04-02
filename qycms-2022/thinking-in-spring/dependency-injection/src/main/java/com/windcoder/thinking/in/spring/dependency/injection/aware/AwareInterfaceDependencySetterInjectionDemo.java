package com.windcoder.thinking.in.spring.dependency.injection.aware;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.dependency.injection.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  基于 {@link org.springframework.beans.factory.Aware}  接口回调的注入示例
 *
 *  怎样判断对象和依赖的关系？
 */
public class AwareInterfaceDependencySetterInjectionDemo   implements BeanFactoryAware, ApplicationContextAware {

    /**
     *  static 仅是为了简化开发
     */
    private static BeanFactory beanFactory;
    /**
     * 用于对照，比如 ApplicationContext 获取一些 Bean,可以用 ApplicationContextAware
     */
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类,Java API方式中的配置类方式）--> Spring Bean
        context.register(AwareInterfaceDependencySetterInjectionDemo.class);


        // 启动应用上下文
        context.refresh();

        PrintZUtill.println(beanFactory == context.getBeanFactory());
        PrintZUtill.println(applicationContext == context );

        // 显示关闭 Spring 应用上下文
        context.close();
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencySetterInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencySetterInjectionDemo.applicationContext = applicationContext;
    }
}
