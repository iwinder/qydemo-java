package DesignPatterns.Creational.Factory.geekbang.factoryMethod.two;


import DesignPatterns.Creational.Factory.geekbang.factoryMethod.one.*;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现方法二
 */
public class RuleConfigParserFactoryMap {
    private static final Map<String, IRuleConfigParserFactory> cacheFactorues = new HashMap<>();

    static {
        cacheFactorues.put("json", new JsonRuleConfigParserFactory());
        cacheFactorues.put("xml", new XmlRuleConfigParserFactory());
        cacheFactorues.put("yaml", new YamlRuleConfigParserFactory());
        cacheFactorues.put("properties", new PropertiesRuleConfigParserFactory());
    }

    public static IRuleConfigParserFactory getParserFactory (String type){
        if (StringUtils.isBlank(type)) {
            return null;
        }
        IRuleConfigParserFactory parserFactory = cacheFactorues.get(type);
        return parserFactory;
    }

}
