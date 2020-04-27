package DesignPatterns.Creational.Singleton.geekbang;


import java.util.concurrent.atomic.AtomicLong;

/**
 * 懒汉模式
 *
 * 支持延迟加载。
 *
 * synchronized 锁，导致函数的并发度很低。
 * 量化一下的话，并发度是 1，也就相当于串行操作。
 * 使用时，若偶尔调用还可以接受。
 * 若频繁使用，将出现频繁加锁、释放锁及并发度低等问题，会导致性能瓶颈，此时该方法便不可取。
 *
 *
 * 懒汉式相对于饿汉式的优势是支持延迟加载。这种实现方式会导致频繁加锁、释放锁，以及并发度低等问题，频繁的调用会产生性能瓶颈。
 */
public class IdGenerator2 {
    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator2 instance;

    private IdGenerator2(){}


    public static synchronized IdGenerator2 getInstance() {
        if (instance == null) {
            instance = new IdGenerator2();
        }

        return instance;
    }

    public  long getId() {
        return id.incrementAndGet();
    }

}
