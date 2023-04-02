package com.windcoder.thinking.in.spring.bean.lifecycle;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 *  BeanDefinition 合并示例
 */
public class MergedBeanDefinitionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        // 基于 XML 资源   BeanDefinition 实现
        int beanNumber = beanDefinitionReader.loadBeanDefinitions(location);
        PrintZUtill.println("已加载的 BeanDefinition 数量：" + beanNumber);
        // 通过 Bean id 和类型查找

        User user = beanFactory.getBean("user", User.class);
        PrintZUtill.println(user );

        User superUser = beanFactory.getBean("superUser", User.class);
        PrintZUtill.println(superUser );
    }
}
