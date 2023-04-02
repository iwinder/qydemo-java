package com.windcoder.thinking.in.spring.bean.definition;

import com.windcoder.thinking.in.spring.bean.definition.factory.UserFacotry;
import com.windcoder.thinking.in.spring.bean.definition.factory.abstractFactory.DefaultUserFactory;
import com.windcoder.thinking.in.spring.bean.definition.factory.abstractFactory.IUserFactory;
import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊的 Bean 实例化示例
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
//        serviceLoaderBeanFactoryDemo();
        autowireCapableDemo();

    }
    public static void autowireCapableDemo() {
        // 配置XMl 配置文件
        // 启动 Spring 应用上下文
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        // 通过 ApplicationContext 获取 AutowireCapableBeanFactory
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        // 创建 UserFactor 对象，通过 AutowireCapableBeanFactory,创建Bean时，一定要用实例(即具体实现类)，不要用接口（或者抽象类），
        IUserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        PrintZUtill.println(userFactory.createUser());

    }

    /**
     *
     */
    public static void serviceLoaderBeanFactoryDemo() {
        // 配置XMl 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        // 创建 ServiceLoader
        ServiceLoader<IUserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);
        PrintZUtill.println("------ServiceLoader------");
        demoServiceLoader();
    }


    /**
     *   ServiceLoader 使用示例
     */
    public static void demoServiceLoader() {
        // 返回一个同类型的一个接口，文件中有多少个实现类就会实例化多少个,重复的会去重
        ServiceLoader<IUserFactory> serviceLoader = ServiceLoader.load(IUserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);

    }

    /**
     * 展示ServiceLoader中的 IUserFactory
     * @param serviceLoader
     */
    public static void displayServiceLoader(ServiceLoader<IUserFactory> serviceLoader) {
        Iterator<IUserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            IUserFactory userFacotry = iterator.next();
            PrintZUtill.println(userFacotry.createUser());
        }
    }
}
