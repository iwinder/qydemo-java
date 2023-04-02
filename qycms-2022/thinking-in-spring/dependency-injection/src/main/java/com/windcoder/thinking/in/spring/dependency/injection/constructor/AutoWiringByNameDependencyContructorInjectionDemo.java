package com.windcoder.thinking.in.spring.dependency.injection.constructor;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.dependency.injection.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *  基于 “byName” Autowiring 依赖 构造器 方法注入示例
 *
 *
 *  基于 type 时有两个User类型，SuperUser 设置成了 primary,所以会注入 SuperUser
 */
public class AutoWiringByNameDependencyContructorInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourcePath = "classpath:/META_INF/autowiring-dependency-contructor-injection.xml";
        // 本身支持传递多个，会自动进行迭代的方式操作
        // 加载 Xml 资源，解析并生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        PrintZUtill.println(userHolder);
    }
}
