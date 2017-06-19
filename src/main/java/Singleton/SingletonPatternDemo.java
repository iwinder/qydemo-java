package Singleton;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 17:17 下午
 */
public class SingletonPatternDemo {
    public static void main(String[] args){
        //不合法的构造函数
        //编译时错误：构造函数 SingleObject 是不可见的
//        SingleObject tobject = new SingleObject();

        //获取唯一可用的对象
        SingleObject object = SingleObject.getInstance();
        //显示消息
        object.showMessage();
    }
}
