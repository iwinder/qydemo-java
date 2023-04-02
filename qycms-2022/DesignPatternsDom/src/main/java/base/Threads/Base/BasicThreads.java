package base.Threads.Base;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BasicThreads {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for Liftoff");
//        FutureTask s = new FutureTask();
//        ReentrantReadWriteLock ss = new ReentrantReadWriteLock();
//        Semaphore
//        ReentrantLock
//        CountDownLatch
//        SynchronousQueue
    }

}
