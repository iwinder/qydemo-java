package DesignPatterns.Creational.AbstractFactory;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 16:23 下午
 */
public class FactoryProducer {
    public static AbstractFactory getEactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        }else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return  null;
    }
}
