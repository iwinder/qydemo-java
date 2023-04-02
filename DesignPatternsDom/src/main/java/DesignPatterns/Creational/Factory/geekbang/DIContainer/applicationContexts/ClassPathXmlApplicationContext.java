package DesignPatterns.Creational.Factory.geekbang.DIContainer.applicationContexts;

import DesignPatterns.Creational.Factory.geekbang.DIContainer.BeansFactory;
import DesignPatterns.Creational.Factory.geekbang.DIContainer.entity.BeanDefinition;
import DesignPatterns.Creational.Factory.geekbang.DIContainer.exceptions.BeanCreationFailureException;
import DesignPatterns.Creational.Factory.geekbang.DIContainer.exceptions.NoSuchBeanDefinitionException;
import DesignPatterns.Creational.Factory.geekbang.DIContainer.parses.BeanConfigParser;
import DesignPatterns.Creational.Factory.geekbang.DIContainer.parses.XmlBeanConfigParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * ClassPathXmlApplicationContext 负责组装 BeansFactory 和 BeanConfigParser 两个类。
 *
 *
 * 串联执行流程：
 * 从 classpath 中加载 XML 格式的配置文件，
 * 通过 BeanConfigParser 解析为统一的 BeanDefinition 格式，
 * 然后，BeansFactory 根据 BeanDefinition 来创建对象。
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private BeansFactory beansFactory;
    private BeanConfigParser beanConfigParser;

    public ClassPathXmlApplicationContext(String configLocation) {
        this.beansFactory = new BeansFactory();
        this.beanConfigParser = new XmlBeanConfigParser();
        loadBeanDefinitions(configLocation);
    }

    private void loadBeanDefinitions(String configLocation) {
        InputStream in = null;
        try {
            in = this.getClass().getResourceAsStream("/" + configLocation);
            if (in == null) {
                throw new RuntimeException("Can not find config file: " + configLocation);
            }
            List<BeanDefinition> beanDefinitions = beanConfigParser.parse(in);
            beansFactory.addBeanDefinitions(beanDefinitions);
        } catch (BeanCreationFailureException e) {
            e.printStackTrace();
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO log error
                }
            }
        }

    }

    @Override
    public Object getBean(String beanId) {
        try {
            return beansFactory.getBean(beanId);
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        } catch (BeanCreationFailureException e) {
            e.printStackTrace();
        }
        return null;
    }
}
