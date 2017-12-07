package Creational.Prototype;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 22:14 下午
 */
public class Rectangle extends Shape {
    public Rectangle(){
        type = "Rectangle";
    }
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
