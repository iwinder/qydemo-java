package AbstractFactory.Shape;

/**
 * Description:2、创建实现形状接口的实体类。Rectangle
 * User: wind
 * Date: 2017-06-19
 * Time: 15:40 下午
 */
public class Rectangle implements Shape {

    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
