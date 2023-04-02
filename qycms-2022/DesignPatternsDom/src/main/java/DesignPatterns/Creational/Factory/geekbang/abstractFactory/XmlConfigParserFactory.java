package DesignPatterns.Creational.Factory.geekbang.abstractFactory;

import DesignPatterns.Creational.Factory.geekbang.abstractFactory.base.ISystemConfigParser;
import DesignPatterns.Creational.Factory.geekbang.abstractFactory.base.XmlSystemConfigParser;
import DesignPatterns.Creational.Factory.geekbang.base.IRuleConfigParser;
import DesignPatterns.Creational.Factory.geekbang.base.XmlRuleConfigParser;

public class XmlConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new XmlRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new XmlSystemConfigParser();
    }
}
