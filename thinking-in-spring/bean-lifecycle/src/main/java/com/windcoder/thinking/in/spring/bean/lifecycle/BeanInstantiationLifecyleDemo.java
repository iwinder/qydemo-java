package com.windcoder.thinking.in.spring.bean.lifecycle;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.aop.aspectj.InstantiationModelAwarePointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 *  Bean 实例化生命周期
 */
public class BeanInstantiationLifecyleDemo {
    public static void main(String[] args) {

    }

    public static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法一 添加 BeanPostProcess 实例（显式）MyInstantiationAwareBeanPostProcessor
        // 方法二: 将 MyInstantiationAwareBeanPostProcessor 作为 Bean注册
//        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
//        String location = "META-INF/dependency-lookup-context.xml";
        String[] locations = {"META-INF/dependency-lookup-context.xml" , "META-INF/bean-constructor-dependency-injection.xml"};
        // 基于 XML 资源   BeanDefinition 实现
        int beanNumber = beanDefinitionReader.loadBeanDefinitions(locations);
        PrintZUtill.println("已加载的 BeanDefinition 数量：" + beanNumber);
        // 通过 Bean id 和类型查找

        User user = beanFactory.getBean("user", User.class);
        PrintZUtill.println(user );

        User superUser = beanFactory.getBean("superUser", User.class);
        PrintZUtill.println(superUser );

        // 构造器注入按类型注入， resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        PrintZUtill.println(userHolder );
    }
    public static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String[] locations = {"META-INF/dependency-lookup-context.xml" , "META-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);
        // 启动应用上下文
        applicationContext.refresh();

        // 通过 Bean id 和类型查找

        User user = applicationContext.getBean("user", User.class);
        PrintZUtill.println(user );

        User superUser = applicationContext.getBean("superUser", User.class);
        PrintZUtill.println(superUser );

        // 构造器注入按类型注入， resolveDependency
        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        PrintZUtill.println(userHolder );
        // 关闭 上下文
        applicationContext.close();

    }




    }

