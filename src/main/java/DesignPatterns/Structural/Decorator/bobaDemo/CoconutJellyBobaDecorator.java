package DesignPatterns.Structural.Decorator.bobaDemo;

import Utills.PrintUtill;

/**
 * 奶茶具体装饰器-奶茶加料-椰果
 */
public class CoconutJellyBobaDecorator extends BobaDecorator{

    public CoconutJellyBobaDecorator(Boba boba) {
        super(boba);
    }

    private void operationOne() {
        PrintUtill.println("添加配料：椰果>>>>>>>>>>>>");
    }

    @Override
    public void make() {
        super.make();
        operationOne();
    }
}
