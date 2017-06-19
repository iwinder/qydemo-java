package Factory;

/**
 * Description: 3、创建一个工厂，生成基于给定信息的实体类的对象
 * User: wind
 * Date: 2017-06-19
 * Time: 15:45 下午
 */
public class ShapeFactory {
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            //equalsIgnoreCase 忽略大小写，equals不忽略大小写
            return new Circle();
        }else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
