package Creational.AbstractFactory.Shape;

/**
 * Description:2、创建实现形状接口的实体类。Square
 * User: wind
 * Date: 2017-06-19
 * Time: 15:42 下午
 */
public class Square implements Shape {
    public void draw() {
        System.out.println("Inside Square::Draw() method.");
    }
}
