package Decorator;

/**
 * Description: 创建扩展了 ShapeDecorator 类的实体装饰类。
 * User: wind
 * Date: 2017-06-30
 * Time: 15:00 下午
 */
public class RedShapeDecorator extends ShapeDecorator{
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    public void draw(){
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color：Red");
    }
}
