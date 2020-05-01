package DesignPatterns.Creational.Factory.geekbang.simple;

import DesignPatterns.Creational.Factory.geekbang.base.*;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 第二种简单工厂模式实现方法
 */
public class RuleConfigParserFactory2 {
    private static Map<String, IRuleConfigParser> cacheParsers = new HashMap<>();

    static {
        cacheParsers.put("json", new JsonRuleConfigParser());
        cacheParsers.put("xml", new XmlRuleConfigParser());
        cacheParsers.put("yaml", new YamlRuleConfigParser());
        cacheParsers.put("properties", new PropertiesRuleConfigParser());
    }

    public static IRuleConfigParser createParser(String ruleConfigFileExt) {
        if (StringUtils.isBlank(ruleConfigFileExt)) {
            return null;
        }
        IRuleConfigParser parser = cacheParsers.get(ruleConfigFileExt);
        return parser;
    }

}
