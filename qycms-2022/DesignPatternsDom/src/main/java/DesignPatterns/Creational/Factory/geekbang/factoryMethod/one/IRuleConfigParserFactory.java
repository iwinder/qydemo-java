package DesignPatterns.Creational.Factory.geekbang.factoryMethod.one;

import DesignPatterns.Creational.Factory.geekbang.base.IRuleConfigParser;

/**
 * 抽象创造者类
 */
public interface IRuleConfigParserFactory {
    IRuleConfigParser createParser();
}
