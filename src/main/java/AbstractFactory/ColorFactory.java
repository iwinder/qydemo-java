package AbstractFactory;


import AbstractFactory.Color.Blue;
import AbstractFactory.Color.Color;
import AbstractFactory.Color.Green;
import AbstractFactory.Color.Red;
import AbstractFactory.Shape.Shape;

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
