package DesignPatterns.Structural.Bridge.baseDemo;

import Utills.PrintUtill;

/**
 *修正抽象化角色
 */
public class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    public void opterator() {
        this.implementor.operationImpl();
        PrintUtill.println("做的另一些事情");
    }
}
