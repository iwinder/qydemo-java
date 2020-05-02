package DesignPatterns.Creational.Factory.runoob.AbstractFactory;

import DesignPatterns.Creational.Factory.runoob.AbstractFactory.Color.Color;
import DesignPatterns.Creational.Factory.runoob.AbstractFactory.Shape.Circle;
import DesignPatterns.Creational.Factory.runoob.AbstractFactory.Shape.Rectangle;
import DesignPatterns.Creational.Factory.runoob.AbstractFactory.Shape.Shape;
import DesignPatterns.Creational.Factory.runoob.AbstractFactory.Shape.Square;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 16:13 下午
 */
public class ShapeFactory extends AbstractFactory{

    Color getColor(String color) {
        return null;
    }Shape getShape(String shapeType) {
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }

        return null;
    }
}
