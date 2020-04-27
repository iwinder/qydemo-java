package DesignPatterns.Creational.Singleton.runoob;

/**
 * Description:1、创建一个 Singleton 类。
 * User: wind
 * Date: 2017-06-19
 * Time: 17:14 下午
 */
public class SingleObject {
    //创建一个 SingleObject 的对象
    private static SingleObject instance = new SingleObject();
    //让构造函数为private，这样该类就不会被实例化
    private SingleObject(){}

    public static SingleObject getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }
}
