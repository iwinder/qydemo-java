package Structural.Facade;

/**
 * 外观模式,隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。
 */
public class FacadePatternDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();
        /**
         * 外观模式主要是需要一个接待员，来执行一系列创建活动，所以感觉将创建多个shape放在一个方法中创建更合适
         */
        shapeMaker.drawShapes();
        //废除
//        shapeMaker.drawCircle();
//        shapeMaker.drawRectangle();
//        shapeMaker.drawSquare();
    }
}
