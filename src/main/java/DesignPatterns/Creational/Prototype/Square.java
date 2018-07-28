package DesignPatterns.Creational.Prototype;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 22:15 下午
 */
public class Square extends Shape {
    public Square(){
        type = "Square";
    }
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
