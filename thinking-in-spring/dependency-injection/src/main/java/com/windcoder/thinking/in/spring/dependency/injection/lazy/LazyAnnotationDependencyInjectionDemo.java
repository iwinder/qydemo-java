package com.windcoder.thinking.in.spring.dependency.injection.lazy;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.dependency.injection.qualifier.annotation.UserGroup;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link org.springframework.beans.factory.ObjectProvider} 实现延迟依赖注入
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // 实时注入

    @Autowired
    private ObjectProvider<User> userObjectProvider; // 延迟依赖注入




    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类,Java API方式中的配置类方式），该类代替了XML文件
        // Spring JavaBean
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        // XmlBeanDefinitionReader 的注册中心，并不一定只在XML场景中使用，注解场景也可以用到XML的一些特性
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 本身支持传递多个，会自动进行迭代的方式操作
        // 加载 Xml 资源，解析并生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);


        // 期待输出 superUser Bean
        PrintZUtill.println("demo.user = " + demo.user);
        // 期待输出 user Bean -> superUser
        PrintZUtill.println("demo.userObjectProvider = " + demo.userObjectProvider.getObject()); // 继承 ObjectFactory
        PrintZUtill.printlnRule();
        demo.userObjectProvider.forEach(System.out::println);

        // 显示关闭 Spring 应用上下文
        applicationContext.close();
    }
}
