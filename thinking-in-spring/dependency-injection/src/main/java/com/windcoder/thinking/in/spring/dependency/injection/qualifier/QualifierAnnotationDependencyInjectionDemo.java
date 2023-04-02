package com.windcoder.thinking.in.spring.dependency.injection.qualifier;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.dependency.injection.qualifier.annotation.UserGroup;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link Qualifier} 注解依赖注入
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // SuperUser -> primary = true

    @Autowired
    @Qualifier("user") // 指定 Bean 名称或 ID
    private User namedUser;

    // 整体应用上下文存在 4 个 User 类型的Bean
    // SuperUser
    // user
    // user1
    // user2


    /**
     * 但由于xml 优先加载，@Bean 要先被识别，此时尚未实例化到容器中，
     * 所以实例中仅出现2个xml中的，当将@Bean移出将其声明放到其他配置类即可生效。
     */
    @Autowired
    private Collection<User> allUsers; //4 个 Bean，
    @Autowired
    @Qualifier
    private Collection<User> qualifterUsers; // 2 个 Bean = user1 + user2 -> 4 个 Bean, user1 + user2 + user3 + user4

    @Autowired
    @UserGroup
    private Collection<User> userGroupUsers; // 2 个 Bean = user3 + user4

    @Bean
    @Qualifier // 进行逻辑分组
    public User user1() {
        return createUser(7L);
    }

    @Bean
    @Qualifier // 进行逻辑分组
    public User user2() {
        return createUser(8L);
    }

    @Bean
    @UserGroup // 进行逻辑分组
    public User user3() {
        return createUser(9L);
    }


    @Bean
    @UserGroup // 进行逻辑分组
    public User user4() {
        return createUser(10L);
    }



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
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        // XmlBeanDefinitionReader 的注册中心，并不一定只在XML场景中使用，注解场景也可以用到XML的一些特性
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 本身支持传递多个，会自动进行迭代的方式操作
        // 加载 Xml 资源，解析并生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);


        PrintZUtill.println("demo.user = " + demo.user);
        PrintZUtill.println("demo.namedUser = " + demo.namedUser);
        PrintZUtill.println("demo.allUsers = " + demo.allUsers);
        PrintZUtill.println("demo.qualifterUsers = " + demo.qualifterUsers);
        PrintZUtill.println("demo.userGroupUsers = " + demo.userGroupUsers);

        // 显示关闭 Spring 应用上下文
        applicationContext.close();
    }
}
