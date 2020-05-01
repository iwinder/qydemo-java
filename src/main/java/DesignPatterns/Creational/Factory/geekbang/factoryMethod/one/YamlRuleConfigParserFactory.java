package DesignPatterns.Creational.Factory.geekbang.factoryMethod.one;

import DesignPatterns.Creational.Factory.geekbang.base.IRuleConfigParser;
import DesignPatterns.Creational.Factory.geekbang.base.YamlRuleConfigParser;

public class YamlRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new YamlRuleConfigParser();
    }
}
