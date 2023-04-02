package DesignPatterns.Creational.Builder.runoob;

/**
 * Description:素食汉堡（Veg Burger）
 * User: wind
 * Date: 2017-06-19
 * Time: 18:49 下午
 */
public class VegBurger extends Burger {
    //名称
    public String name() {
        return "Veg Burger";
    }
    //价格
    public float price() {
        return 25.0f;
    }
}
