package DesignPatterns.Creational.Factory.geekbang.factoryMethod.one;

import DesignPatterns.Creational.Factory.geekbang.base.IRuleConfigParser;
import DesignPatterns.Creational.Factory.geekbang.base.XmlRuleConfigParser;

public class XmlRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new XmlRuleConfigParser();
    }
}
