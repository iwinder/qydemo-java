package DesignPatterns.Creational.Factory.geekbang.abstractFactory;

import DesignPatterns.Creational.Factory.geekbang.abstractFactory.base.ISystemConfigParser;
import DesignPatterns.Creational.Factory.geekbang.base.IRuleConfigParser;

/**
 *  抽象接口定义的一个接口，所有的具体实现工厂都必须实现此接口
 */
public interface IConfigParserFactory {
    IRuleConfigParser createRuleParser();
    ISystemConfigParser createSystemParser();
    // 此处可以扩展新的parser类型，比如IBizConfigParser
}
