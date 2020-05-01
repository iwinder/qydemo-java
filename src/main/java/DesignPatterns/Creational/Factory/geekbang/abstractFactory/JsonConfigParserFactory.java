package DesignPatterns.Creational.Factory.geekbang.abstractFactory;

import DesignPatterns.Creational.Factory.geekbang.abstractFactory.base.ISystemConfigParser;
import DesignPatterns.Creational.Factory.geekbang.abstractFactory.base.JsonSystemConfigParser;
import DesignPatterns.Creational.Factory.geekbang.base.IRuleConfigParser;
import DesignPatterns.Creational.Factory.geekbang.base.JsonRuleConfigParser;

public class JsonConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new JsonRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new JsonSystemConfigParser();
    }
}
