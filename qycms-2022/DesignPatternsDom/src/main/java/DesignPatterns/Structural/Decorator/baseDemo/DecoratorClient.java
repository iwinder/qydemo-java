package DesignPatterns.Structural.Decorator.baseDemo;

import Utills.PrintUtill;

/**
 * 客户端-用于调用测试
 */
public class DecoratorClient {
    public static void main(String[] args) {
        ConcreteComponentA concreteComponentA = new ConcreteComponentA(new ConcreteComponent());
        concreteComponentA.operation();
        PrintUtill.printlnRule();
        ConcreteComponentB concreteComponentB = new ConcreteComponentB(concreteComponentA);
        concreteComponentB.operation();
    }
}
