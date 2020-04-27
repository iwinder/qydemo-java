package DesignPatterns.Creational.Singleton.geekbang;


import DesignPatterns.DesignConcept.IdGenerator.demo1.IdGenerator;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 饿汉模式
 *
 * 不支持延迟加载
 *
 * 饿汉式的实现方式，在类加载的期间，就已经将 instance 静态实例初始化好了，所以，instance 实例的创建是线程安全的。不过，这样的实现方式不支持延迟加载实例。
 */
public class IdGenerator1 {
    private AtomicLong id = new AtomicLong(0);
    private static final IdGenerator1 instantce = new IdGenerator1();

    private IdGenerator1(){}


    public static IdGenerator1 getInstance() {
        return instantce;
    }

    public  long getId() {
        return id.incrementAndGet();
    }

}
