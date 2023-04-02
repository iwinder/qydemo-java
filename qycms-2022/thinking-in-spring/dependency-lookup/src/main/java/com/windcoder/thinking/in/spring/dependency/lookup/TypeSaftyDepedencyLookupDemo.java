package com.windcoder.thinking.in.spring.dependency.lookup;



import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import com.windcoder.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  类型安全 依赖查找示例
 *
 *  异常安全
 */
public class TypeSaftyDepedencyLookupDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();
        // 将当前类 TypeSaftyDepedencyLookupDemo 作为配置类（Configuration Class）
        applicationContext.register(ObjectProviderDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        // 演示 BeanFactory#getBean 方法的安全性
        displayBeanFactoryGetBean(applicationContext);
        // 演示 ObjectFactory#getObject 方法的安全性
        displayObjectFactoryGetObject(applicationContext);
        // 演示 ObjectProvider#getIfAvailable 方法的安全性
        displayObjectProviderIfAvailable(applicationContext);
        // 演示 ListableBeanFactory#getBeansOfType 方法的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        // 演示 ObjectProvider Stream 操作的安全性
        displayObjectProviderStreamOps(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", () ->  objectProvider.forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType", () ->  beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", () ->  objectProvider.getIfAvailable());
    }


    public static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", () ->  objectProvider.getObject(User.class));
    }

    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {

        printBeansException(" displayBeanFactoryGetBean", () -> {
            // User类不存在时，生产环境不要这么做，会发生阻塞。
            // 因为该方法是线程安全的，所以会发生死锁
            beanFactory.getBean(User.class);
        });
    }


    private static void printBeansException(String source,Runnable runnable) {
        PrintZUtill.printlnRule();
        PrintZUtill.println("Source from " + source);
        try {
           runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
