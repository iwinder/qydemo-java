package DesignPatterns.Creational.Prototype.runoob;

/**
 * Description: 1、创建一个实现了 Clonable 接口的抽象类。
 * User: wind
 * Date: 2017-06-19
 * Time: 22:10 下午
 */
public abstract class Shape implements Cloneable{
    private String id;
    protected String type;

    abstract void draw();

    public String getType(){
        return type;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public Object clone(){
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
