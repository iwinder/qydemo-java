package AbstractFactory;

import AbstractFactory.Color.Color;
import AbstractFactory.Shape.Shape;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 16:11 下午
 */
public abstract class AbstractFactory {
    abstract Color getColor(String color);
    abstract Shape getShape(String shape);
}
