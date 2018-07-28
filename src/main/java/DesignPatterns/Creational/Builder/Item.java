package DesignPatterns.Creational.Builder;

/**
 * Description:创建一个表示食物条目和食物包装的接口---------食物条目的接口。
 * User: wind
 * Date: 2017-06-19
 * Time: 18:41 下午
 */
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}
