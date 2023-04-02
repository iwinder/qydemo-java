package DesignPatterns.Creational.Singleton.geekbang;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class IdGenerator8 {
    private long serverNo;
    private String serverAddress;
    private static final int SERVER_COUNT = 3;

    private static final ConcurrentHashMap<Long, IdGenerator8> instances
            = new ConcurrentHashMap<>();

    static {
        instances.put(1L, new IdGenerator8(1L, "192.134.22.138:8080"));
        instances.put(2L, new IdGenerator8(2L, "192.134.22.139:8080"));
        instances.put(3L, new IdGenerator8(3L, "192.134.22.140:8080"));
    }

    private IdGenerator8(long serverNo, String address) {
        this.serverNo = serverNo;
        this.serverAddress = address;
    }

    public IdGenerator8 getInstance(long serverNo) {
        return instances.get(serverNo);
    }
    public IdGenerator8 getRandomInstance() {
        Random r = new Random();
        int no = r.nextInt(SERVER_COUNT)+1;
        return instances.get(no);
    }

}
