package DesignPatterns.Creational.Factory.geekbang.simple;

import DesignPatterns.Creational.Factory.geekbang.base.*;

/**
 * 简单工厂模式类
 *
 * 将代码中涉及解析器 parser 创建的部分逻辑剥离出来，抽象成 createParser() 函数
 * 将 createParser() 函数剥离到一个独立的类中，让这个类只负责对象的创建。
 * 这个类就是简单工厂模式类
 */
public class RuleConfigParserFactory1 {
    public static IRuleConfigParser createParser(String ruleConfigFileExt) {
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(ruleConfigFileExt)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(ruleConfigFileExt)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(ruleConfigFileExt)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(ruleConfigFileExt)) {
            parser = new PropertiesRuleConfigParser();
        }
        return parser;
    }
}
