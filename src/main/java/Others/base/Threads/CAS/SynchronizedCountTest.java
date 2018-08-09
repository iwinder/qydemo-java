package Others.base.Threads.CAS;

import Utills.PrintUtill;

/**
 * 同步锁Synchronized确保了线程安全。但某些情况下不是最优，关键在于性能。
 * Synchronixrd关键字会让没有得到锁资源的线程进入BLOCKED的状态，
 * 而后在争夺到锁资源后恢复为RUNNABLE状态，
 * 这个过程中涉及到操作系统的用户模式和内核模式的转换，代价比较高。
 * 尽管Java1.6为Synchronize做了优化，增加了从偏向锁到轻量级锁再到重量级锁的过度，
 * 但在最终转变为重量级锁之后，性能仍然较低。
 */
public class SynchronizedCountTest {
    public static int count = 0;

    public static void main(String[] args) {
        //开启两个线程
        for(int i=0;i<2;i++){
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
                                synchronized (SynchronizedCountTest.class){
                                    count++;
                                }

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
