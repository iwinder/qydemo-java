package base.covariantReturnType.Flowers;

/**
 * [Java之协变返回类型理解和简单实例](http://blog.csdn.net/huangwenyi1010/article/details/53454542)
 * [关于java可变（协变）返回类型的解说之一------------基类与派生类](http://www.cnblogs.com/sanra/p/4198939.html)
 * [java泛型中桥方法以及协变返回类型](https://www.cnblogs.com/wangjimmy/p/6903685.html)
 */
public class BareHisHeart {
    public static void main(String[] args) {
        Person person = new Boy();
        Flower flower = person.buy();
        flower.like();
        //!flower.love();  编译错误
        //因为是协变返回类型，所以可以向下转型
        RoseFlower roseFlower = (RoseFlower)person.buy();
        roseFlower.like();
        roseFlower.love();
    }
}
