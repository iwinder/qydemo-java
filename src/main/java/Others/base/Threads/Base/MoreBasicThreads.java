package Others.base.Threads.Base;

/**
 * 输出说明不同任务的执行在线程被换进换出时混在了一起。这种交换是由县城调度器自动控制的。
 * 一个线程会创建一个独立的执行线程，在对start()的调用完成之后，它仍旧会继续存在。
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i =0 ;i<5;i++)
            new Thread(new LiftOff()).start();
        System.out.println("Waiting for Liftoff");
    }

}
