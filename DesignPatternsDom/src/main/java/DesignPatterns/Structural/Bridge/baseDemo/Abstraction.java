package DesignPatterns.Structural.Bridge.baseDemo;

/**
 * 抽象化角色
 */
public abstract class Abstraction {
    Implementor implementor;
    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }
    public abstract void opterator();
}
