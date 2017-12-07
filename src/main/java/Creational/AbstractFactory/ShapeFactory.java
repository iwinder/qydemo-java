package Creational.AbstractFactory;

import Creational.AbstractFactory.Color.Color;
import Creational.AbstractFactory.Shape.Circle;
import Creational.AbstractFactory.Shape.Rectangle;
import Creational.AbstractFactory.Shape.Shape;
import Creational.AbstractFactory.Shape.Square;

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
