package DesignPatterns.Structural.Bridge.baseDemo;

/**
 * 客户端
 */
public class BridgeClient {
    public static void main(String[] args) {
        RefinedAbstraction obj = new RefinedAbstraction(new ConcreteImplementor());
        obj.opterator();
    }
}
