package com.windcoder.thinking.in.spring.dependency.injection.setter;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.dependency.injection.UserHolder;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 *  基于 Java  注解的依赖 Setter 方法注入示例
 */
public class AnnotationDependencySetterInjectionDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类,Java API方式中的配置类方式），该类代替了XML文件
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        // XmlBeanDefinitionReader 的注册中心，并不一定只在XML场景中使用，注解场景也可以用到XML的一些特性
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 本身支持传递多个，会自动进行迭代的方式操作
        // 加载 Xml 资源，解析并生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找并创建 Bean
        UserHolder userHolder =  applicationContext.getBean(UserHolder.class);

        PrintZUtill.println(userHolder);
        // 显示关闭 Spring 应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
