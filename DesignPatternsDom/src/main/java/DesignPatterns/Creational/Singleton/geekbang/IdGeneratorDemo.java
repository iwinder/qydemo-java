package DesignPatterns.Creational.Singleton.geekbang;

import Utills.PrintUtill;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class IdGeneratorDemo {
    private final static int THREAD_CORE_NUMBER = 5;
    private final static int THREAD_MAX_NUMBER = 20;
    private final static int THREAD_NUMBER = 100;
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(THREAD_CORE_NUMBER,THREAD_MAX_NUMBER,
                200, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(THREAD_CORE_NUMBER));

        for (int i=1; i<=THREAD_NUMBER;i++) {
            int tmp = i;
//            if (executor.getActiveCount()<THREAD_MAX_NUMBER) {
                executor.execute(() -> PrintUtill.println(tmp +": " +IdGenerator3.getInstance().getId()));
//            }

        }
    }
}
