package DesignPatterns.Structural.Decorator.bobaDemo;

import Utills.PrintUtill;

/**
 * 奶茶装饰器-添加红豆
 */
public class RedBeansBobaDecorator extends BobaDecorator{
    public RedBeansBobaDecorator(Boba boba) {
        super(boba);
    }

    private void operationOne() {
        PrintUtill.println("添加配料：红豆>>>>>>>>>>>>");
    }
    @Override
    public void make() {
        super.make();
        operationOne();
    }
}
