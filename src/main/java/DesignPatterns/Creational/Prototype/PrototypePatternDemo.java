package DesignPatterns.Creational.Prototype;

/**
 * Description:Java设计模式学习笔记—原型模式
 * User: wind
 * Date: 2017-06-20
 * Time: 0:14 上午
 */
public class PrototypePatternDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape cloneShape = (Shape)ShapeCache.getShape("1");
        System.out.println("Shape : "+cloneShape.getType());

        Shape cloneShape2 = (Shape)ShapeCache.getShape("2");
        System.out.println("Shape2 : "+cloneShape2.getType());
        Shape cloneShape3 = (Shape)ShapeCache.getShape("3");
        System.out.println("Shape3 : "+cloneShape3.getType());
    }
}
