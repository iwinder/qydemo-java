package DesignPatterns.Structural.Decorator.baseDemo;

import Utills.PrintUtill;

/**
 * 具体装饰器A
 */
public class ConcreteComponentA extends Decorator{
    /**
     * 构造方法：传入组件对象
     *
     * @param component
     */
    public ConcreteComponentA(Component component) {
        super(component);
    }
    private void operationOne() {
        PrintUtill.println("装饰器转发请求前的附加功能>>>>>>>>>>>>A");
    }
    private void operationTwo() {
        PrintUtill.println("装饰器转发请求后的附加功能>>>>>>>>>>>>A");
    }

    @Override
    public void operation() {
        operationOne(); // 添加的新功能
        // 可选择性调用父级方法，若不调用，则相当于完全改写了方法，实现新功能
        super.operation();
        operationTwo(); // 添加的新功能
    }
}
