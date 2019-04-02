package Others.base.Threads.CAS;

import Utills.PrintUtill;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子操作类，指的是java.util.concurrent.atomic包下，一系列以Atomic开头的包装类。如
 *AtomicBoolean，AtomicInteger，AtomicLong。它们分别用于Boolean，Integer，Long类型的原子性操作。
 * 在某些情况下，性能比Synchronize更好。
 * 其底层用的是CAS机制。
 */
public class AtomicCountTest {
    public static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {
        //开启两个线程
        for(int i=0;i<20;i++){
            new Thread(
                    new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(10);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            //每个线程当中让count值自增100次
                            for (int j=0;j<100;j++){
                               count.incrementAndGet();
                            }
                        }
                    }
            ).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PrintUtill.println("Count = "+count);
    }
}
