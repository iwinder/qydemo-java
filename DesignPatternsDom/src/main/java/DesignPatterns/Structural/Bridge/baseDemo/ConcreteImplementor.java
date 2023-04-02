package DesignPatterns.Structural.Bridge.baseDemo;

import Utills.PrintUtill;

/**
 *具体实现
 */
public class ConcreteImplementor implements Implementor{
    @Override
    public void operationImpl() {
        PrintUtill.println("实际做一些事情");
    }
}
