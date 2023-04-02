package DesignPatterns.Creational.Factory.runoob.AbstractFactory;


import DesignPatterns.Creational.Factory.runoob.AbstractFactory.Color.Blue;
import DesignPatterns.Creational.Factory.runoob.AbstractFactory.Color.Color;
import DesignPatterns.Creational.Factory.runoob.AbstractFactory.Color.Green;
import DesignPatterns.Creational.Factory.runoob.AbstractFactory.Color.Red;
import DesignPatterns.Creational.Factory.runoob.AbstractFactory.Shape.Shape;

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
