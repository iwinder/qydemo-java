package DesignPatterns.Structural.Decorator.baseDemo;

import Utills.PrintUtill;

/**
 * 具体组件：实现/继承类，被装饰对象
 */
public class ConcreteComponent extends Component{
    @Override
    public void operation() {
        PrintUtill.println("真正的对象做一些事情");
    }
}
