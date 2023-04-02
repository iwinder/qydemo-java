package DesignPatterns.Creational.Singleton.geekbang;

import java.util.concurrent.atomic.AtomicLong;

/**
 *  枚举
 *  最简单的实现方式，基于枚举类型的单例实现。这种实现方式通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。
 */
public enum IdGenerator5 {
    INSTANCE;
    private AtomicLong id = new AtomicLong(0);
    public long getId() {
        return id.incrementAndGet();
    }

    public static void main(String[] args) {
        IdGenerator5.INSTANCE.getId();
    }
}
