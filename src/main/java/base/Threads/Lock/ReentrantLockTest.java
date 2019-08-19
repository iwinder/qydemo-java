package base.Threads.Lock;

import Utills.PrintUtill;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    int a = 0;
    ReentrantLock lock = new ReentrantLock();
    public void writer(){
        lock.lock(); // 获取锁
        try{
            a++;
        }finally {
            lock.unlock(); //释放锁
        }
    }

    public void reader(){
        lock.lock(); // 获取锁
        try {
            int i =a;
            PrintUtill.print("i:"+i);
        }finally {
            lock.unlock(); // 释放锁
        }
    }
}
