package DesignPatterns.Creational.Factory.runoob.AbstractFactory;

import DesignPatterns.Creational.Factory.runoob.AbstractFactory.Color.Color;
import DesignPatterns.Creational.Factory.runoob.AbstractFactory.Shape.Shape;

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
