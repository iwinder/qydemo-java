package com.windcoder.thinking.in.spring.dependency.lookup;

import com.windcoder.thinking.in.spring.common.utils.PrintZUtill;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  层次性依赖查找 示例
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();
        // 将当前类 HierarchicalDependencyLookupDemo 作为配置类（Configuration Class）
        applicationContext.register(HierarchicalDependencyLookupDemo.class);

        // 1. 获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        PrintZUtill.println("当前 BeanFactory 的Parent BeanFactory:  " + beanFactory.getParentBeanFactory());

        // 2. 设置 ParentBeanFactory
        ConfigurableListableBeanFactory parentBeanFactory = crateParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        PrintZUtill.println("当前 BeanFactory 的Parent BeanFactory:  " + beanFactory.getParentBeanFactory());

        PrintZUtill.printlnRule();
        displayLocalBean(beanFactory, "user");
        PrintZUtill.printlnRule();
        displayLocalBean(parentBeanFactory, "user");
        PrintZUtill.printlnRule();
        displayContainsBean(beanFactory, "user");
        PrintZUtill.printlnRule();
        displayContainsBean(parentBeanFactory, "user");

        // 启动应用上下文
        applicationContext.refresh();
        // 关闭应用上下文
        applicationContext.close();
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Bean[name: %s] : %s\n", beanFactory, beanName,  containsBean(beanFactory, beanName));

    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof  HierarchicalBeanFactory) {
            // 既判断了类型，又判断了是否为空
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsBean(beanName);
    }

    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Local Bean[name: %s] : %s\n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }




    private static ConfigurableListableBeanFactory crateParentBeanFactory() {
        // 创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建xml方式的Bean定义的读取器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //  XML 配置文件 ClassPath 路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载配置
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }
}
