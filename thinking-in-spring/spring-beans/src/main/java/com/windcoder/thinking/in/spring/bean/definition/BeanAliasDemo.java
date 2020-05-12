package com.windcoder.thinking.in.spring.bean.definition;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名示例
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        // 配置XMl 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META_INF/bean-definitions-context.xml");

        // 通过别名 获取曾用名user的Bean
        User user = beanFactory.getBean("user", User.class);
        User windUser = beanFactory.getBean("wind-user", User.class);
        PrintZUtill.println("wind-user 是否与 user Bean 相同：" + (user == windUser));
    }
}
