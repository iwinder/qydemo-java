package com.windcoder.thinking.in.spring.dependency.injection;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import java.util.Map;
import java.util.Optional;

/**
 * 注解驱动的依赖注入处理过程
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired          // 依赖查找（处理）
    @Lazy
    private User lazyUser;


    // DependencyDescriptor ->
    // 必须（required = true）
    // 实时注入(eager = true)
    // 通过类型（User.class）
    // 字段名称（"user"）
    // 是否是首要（primarty = true）
    @Autowired          // 依赖查找（处理）
    private User user;

    @Autowired   // 集合注入 依赖查找
    private Map<String, User> users;


    @Autowired
    private Optional<User> optionalUser; // super user




    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类,Java API方式中的配置类方式），该类代替了XML文件
        // Spring JavaBean
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        // XmlBeanDefinitionReader 的注册中心，并不一定只在XML场景中使用，注解场景也可以用到XML的一些特性
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 本身支持传递多个，会自动进行迭代的方式操作
        // 加载 Xml 资源，解析并生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);


        // 期待输出 superUser Bean
        PrintZUtill.println("demo.user = " + demo.user);
        PrintZUtill.println("demo.users = " + demo.users);
        PrintZUtill.println("demo.optionalUser = " + demo.optionalUser);
        PrintZUtill.println("demo.lazyUser = " + demo.lazyUser);


        // 显示关闭 Spring 应用上下文
        applicationContext.close();
    }
}
