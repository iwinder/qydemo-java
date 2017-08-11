package Decorator;

/**
 * Description:
 * User: wind
 * Date: 2017-06-30
 * Time: 14:58 下午
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw() {
        decoratedShape.draw();
    }
}
