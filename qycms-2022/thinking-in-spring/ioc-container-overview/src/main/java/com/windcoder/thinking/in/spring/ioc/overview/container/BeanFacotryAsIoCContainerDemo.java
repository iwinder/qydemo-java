package com.windcoder.thinking.in.spring.ioc.overview.container;

import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * {@link BeanFacotry}作为 IoC 容器示例
 */
public class BeanFacotryAsIoCContainerDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建xml方式的Bean定义的读取器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //  XML 配置文件 ClassPath 路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载配置
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        PrintZUtill.println("Bean定义加载的数量：",beanDefinitionsCount);
        // 依赖查找集合对象
        // 说明即使不在ApplicationContext下也可以加载Bean文件，只是如此便无法有之前的各种复杂特性。
        lookupCollectionByType(beanFactory);
    }


    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 对象 集合对象：" + users);
        }
    }
}
