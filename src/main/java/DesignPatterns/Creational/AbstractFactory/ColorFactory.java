package DesignPatterns.Creational.AbstractFactory;


import DesignPatterns.Creational.AbstractFactory.Color.Blue;
import DesignPatterns.Creational.AbstractFactory.Color.Color;
import DesignPatterns.Creational.AbstractFactory.Color.Green;
import DesignPatterns.Creational.AbstractFactory.Color.Red;
import DesignPatterns.Creational.AbstractFactory.Shape.Shape;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 16:18 下午
 */
public class ColorFactory extends AbstractFactory {
    Color getColor(String color) {
        if(color == null)return null;

        if(color.equalsIgnoreCase("RED")){
            return new Red();
        }else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        }else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
    Shape getShape(String shape) {
        return null;
    }
}
