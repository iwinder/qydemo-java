package com.windcoder.thinking.in.spring.bean.definition;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import jdk.nashorn.internal.ir.IfNode;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;

import java.util.Map;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 注册 BeanDefinition 示例
 */
// 3. 通过 @Import 来进行导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置类,Java API方式中的配置类方式），该类代替了XML文件
        applicationContext.register(Config.class);

        // 通过 BeanDefinition 注册API实现
        // 1. 命名 Bean 的注册方式
        registerBeanDefinition(applicationContext, "mercybitz-user", User.class);
        // 2. 非命名 Bean 的注册方式
        registerBeanDefinition(applicationContext);
        // 启动应用上下文
        applicationContext.refresh();

        // 按照类型依赖查找
//         Map<String, Config> beansOfType = applicationContext.getBeansOfType(Config.class);
        PrintZUtill.println("Config 类型的所有 Beans: " +  applicationContext.getBeansOfType(Config.class));
        PrintZUtill.println("user 类型的所有 Beans: " +  applicationContext.getBeansOfType(User.class));

        // 显示关闭 Spring 应用上下文
        applicationContext.close();
    }

    /**
     * 命名 Bean 的注册方式
     * @param registry
     * @param beanName
     * @param beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder =  genericBeanDefinition(beanClass);
        beanDefinitionBuilder
                .addPropertyValue("id", 3L)
                .addPropertyValue("name", "毁灭者2");
        // 判断如果 beanName 参数存在时
        if (StringUtils.hasText(beanName)) {
            // 注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 非命名 Bean 注册方法
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }


    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry) {
        registerBeanDefinition(registry,null,User.class);
    }

    // 2. 通过@Component 方式
    @Configuration // 定义当前类作为 Spring Bean组件
    public static class Config{

        // 1. 通过@Bean 方式定义
        /**
         *  通过 Java 注解的方式，定义了一个Bean
         * @return
         */
        @Bean(name = {"user", "windcoder-user"})
        public User user() {
            User user = new User();
            user.setId(2L);
            user.setName("windcoder");
            return user;
        }
    }



}
