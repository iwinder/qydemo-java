package base.Generics.generator;

/**
 * 生成器，一种专门负责创建对象的类。
 * 实际上是工厂方法设计模式的一种应用。
 * 当使用生成器创建新对象时，不需要任何参数，但工厂方法一般需要参数。
 * 一般而言，一个生成器只定义一个方法，该方法用以生产新对象。
 * 在这里就是next()方法。
 * @param <T>
 */
public interface Generator<T> {
    T next();
}
