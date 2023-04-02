package DesignPatterns.Creational.Factory.geekbang.DIContainer.parses;

import DesignPatterns.Creational.Factory.geekbang.DIContainer.entity.BeanDefinition;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * xml配置文件解析类
 */
public class XmlBeanConfigParser implements BeanConfigParser {
    @Override
    public List<BeanDefinition> parse(InputStream in) {
        String content = null;
        // TODO:...

       return parse(content);
    }

    @Override
    public List<BeanDefinition> parse(String configContent) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        // TODO:...

        return beanDefinitions;
    }
}
