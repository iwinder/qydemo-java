package DesignPatterns.Structural.Bridge;

/**
 * Description:
 * User: wind
 * Date: 2017-06-23
 * Time: 13:50 下午
 */
public abstract class Shape {
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
