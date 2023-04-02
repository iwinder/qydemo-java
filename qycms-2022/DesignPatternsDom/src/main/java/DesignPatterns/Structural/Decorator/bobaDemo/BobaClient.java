package DesignPatterns.Structural.Decorator.bobaDemo;

import Utills.PrintUtill;

/**
 * 客户端
 */
public class BobaClient {
    public static void main(String[] args) {
        // 用户A定了一杯椰果珍珠奶茶
        CoconutJellyBobaDecorator coconutJellyBoba = new CoconutJellyBobaDecorator(new TapiocaBoba());
        // 制作椰果珍珠奶茶
        coconutJellyBoba.make();
        PrintUtill.printlnRule();
        // 用户B想要一杯椰果红豆珍珠奶茶,
        // 此处为简单演示嵌套装饰，省去了重新创建椰果珍珠奶茶的过程，重用上一个对象。
        RedBeansBobaDecorator redBeansBoba = new RedBeansBobaDecorator(coconutJellyBoba);
        redBeansBoba.make();

    }
}
