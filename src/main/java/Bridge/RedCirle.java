package Bridge;

/**
 * Description:创建实现了 DrawAPI 接口的实体桥接实现类。
 * User: wind
 * Date: 2017-06-23
 * Time: 13:46 下午
 */
public class RedCirle implements DrawAPI {
    public void drawCirle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color:red, radius: "+radius+", x: "+x+",y: "+y+"]");
    }
}
