package base.Generics.Manipulator;

/**
 * 由于有了擦除，Java编译器无法将 manipublate在obj上调用f()这一需求 映射到HasF所拥有的f()这一事实上。
 * 为了调用f()，必须协助泛型类，给定泛型类的边界，以此告知编译器只能接收遵循这个边界的类型，这里重用了extends关键字。
 * @param <T>
 */
public class Manipulator<T> {
    private T obj;
    public Manipulator(T x){
        obj = x;
    }
    public void manipublate(){
        // error: 找不到 f()方法。
        // obj.f();
    }
}
