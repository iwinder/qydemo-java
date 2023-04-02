package DesignPatterns.Creational.Factory.runoob.AbstractFactory.Shape;

/**
 * Description: 2、创建实现形状接口的实体类。Circle
 * User: wind
 * Date: 2017-06-19
 * Time: 15:43 下午
 */
public class Circle implements Shape {
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
