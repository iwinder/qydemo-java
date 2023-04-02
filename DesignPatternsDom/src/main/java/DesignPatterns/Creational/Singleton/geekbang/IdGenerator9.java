package DesignPatterns.Creational.Singleton.geekbang;

import java.util.concurrent.ConcurrentHashMap;

public class IdGenerator9 {
    private static final ConcurrentHashMap<String, IdGenerator9> instances
            = new ConcurrentHashMap<>();
    private IdGenerator9() {}

    public static IdGenerator9 getInstance(String name) {
        instances.putIfAbsent(name, new IdGenerator9());
        return instances.get(name);
    }

    public static void main(String[] args) {
        IdGenerator9 i1 =  IdGenerator9.getInstance("User.class");
        IdGenerator9 i2 =  IdGenerator9.getInstance("User.class");
        IdGenerator9 i3 =  IdGenerator9.getInstance("Order.class");
    }
}
