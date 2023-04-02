package DesignPatterns.Creational.Singleton.geekbang;

import java.util.concurrent.atomic.AtomicLong;

/**
 *  双重检测
 *  双重检测实现方式既支持延迟加载、又支持高并发的单例实现方式。只要 instance 被创建之后，再调用 getInstance() 函数都不会进入到加锁逻辑中。所以，这种实现方式解决了懒汉式并发度低的问题。
 */
public class IdGenerator3 {

    private AtomicLong id = new AtomicLong(0);
//    private static  IdGenerator3 instance;
    private static volatile IdGenerator3 instance;

    private  IdGenerator3(){}
    public static IdGenerator3 getInstance(){
        if (null == instance) {
            synchronized (IdGenerator3.class) {
                if (null == instance) {
                    instance = new IdGenerator3();
                }
            }
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
