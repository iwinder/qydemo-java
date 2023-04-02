package DesignPatterns.Creational.Prototype.runoob;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 22:17 下午
 */
public class Circle extends Shape {
    public Circle(){
        type = "Circle";
    }
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
