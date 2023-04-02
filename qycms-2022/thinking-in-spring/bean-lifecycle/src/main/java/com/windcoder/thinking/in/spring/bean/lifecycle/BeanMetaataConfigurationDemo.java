package com.windcoder.thinking.in.spring.bean.lifecycle;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 *  Bean 元信息配置示例
 */
public class BeanMetaataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 实例化基于 Properties 资源 BeanDefinitionReader
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
//        String location = "classpath://META-INF/user.properties";
        String location = "META-INF/user.properties";
        // 加载 properties 资源
        // 默认读取通过 ASCII 码读取，而文件实际是 UTF-8 从而出现乱码。
        // 基于 ClassPath 加载 Properties 资源
        Resource resource = new ClassPathResource(location);
        // 指定字符编码 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNumber = reader.loadBeanDefinitions(encodedResource);
        PrintZUtill.println("已加载的 BeanDefinition 数量：" + beanNumber);
        // 通过 Bean id 和类型查找

        User user = beanFactory.getBean("user", User.class);
        PrintZUtill.println(user );
    }
}
