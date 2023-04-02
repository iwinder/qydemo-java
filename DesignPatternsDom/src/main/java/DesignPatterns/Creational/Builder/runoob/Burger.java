package DesignPatterns.Creational.Builder.runoob;

/**
 * Description:创建实现 Item 接口的抽象类，该类提供了默认的功能。
 * User: wind
 * Date: 2017-06-19
 * Time: 18:46 下午
 */
public abstract class Burger implements Item{
    //包装-返回Wrapper，即纸盒包装
    public Packing packing(){
        return new Wrapper();
    }

    //价格
    public abstract float price();
}
