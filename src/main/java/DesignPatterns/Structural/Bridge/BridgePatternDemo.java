package DesignPatterns.Structural.Bridge;

/**
 * Description:Java设计模式学习笔记—桥接模式
 * User: wind
 * Date: 2017-06-23
 * Time: 13:55 下午
 */
public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100,10,new RedCirle());
        Shape greenCircle = new Circle(100,100,10,new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
