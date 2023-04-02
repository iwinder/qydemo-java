package DesignPatterns.Creational.Singleton.geekbang;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程唯一的单例
 */
public class IdGenerator6 {
    private AtomicLong id = new AtomicLong(0);
    private static final ConcurrentHashMap<Long, IdGenerator6> instances
            = new ConcurrentHashMap<>();

    private IdGenerator6() {}
    public static IdGenerator6 getInstance() {
        Long currentThreadId = Thread.currentThread().getId();
        instances.putIfAbsent(currentThreadId, new IdGenerator6());
        return instances.get(currentThreadId);
    }
    public long getId() {
        return id.incrementAndGet();
    }
}
