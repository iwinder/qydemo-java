package DesignPatterns.Structural.Decorator.bobaDemo;

import Utills.PrintUtill;

/**
 * 珍珠奶茶
 */
public class TapiocaBoba extends Boba{
    @Override
    public void make() {
        PrintUtill.println("制作珍珠奶茶");
    }
}
