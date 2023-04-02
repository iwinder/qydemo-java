package DesignPatterns.Creational.Factory.geekbang.factoryMethod.one;

import DesignPatterns.Creational.Factory.geekbang.base.IRuleConfigParser;
import DesignPatterns.Creational.Factory.geekbang.base.PropertiesRuleConfigParser;

public class PropertiesRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new PropertiesRuleConfigParser();
    }
}
