package DesignPatterns.Structural.Decorator.baseDemo;

import Utills.PrintUtill;

/**
 * 具体装饰器B
 */
public class ConcreteComponentB extends Component{
    private Component component;
    public ConcreteComponentB(Component component) {
        this.component = component;
    }
    private void operationOne() {
        PrintUtill.println("装饰器转发请求前的附加功能>>>>>>>>>>>>B");
    }
    private void operationTwo() {
        PrintUtill.println("装饰器转发请求后的附加功能>>>>>>>>>>>>B");
    }
    @Override
    public void operation() {
        operationOne();
        component.operation();
        operationTwo();
    }
}
