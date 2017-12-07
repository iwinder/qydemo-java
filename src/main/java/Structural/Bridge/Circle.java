package Structural.Bridge;

/**
 * Description:
 * User: wind
 * Date: 2017-06-23
 * Time: 13:52 下午
 */
public class Circle extends Shape {
    private int x,y,radius;

    public Circle(int x,int y,int radius,DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;

    }

    public void draw() {
        drawAPI.drawCirle(radius,x,y);
    }
}
