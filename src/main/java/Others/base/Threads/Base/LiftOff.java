package Others.base.Threads.Base;

import Utills.PrintUtill;

public class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final  int id = taskCount++;
    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }
    public String status(){
        return "#" + id + "(" + (countDown>0?countDown:"Liftoff!") + "),";
    }

    public void run() {
        while (countDown-->0){
            PrintUtill.print(status());
            Thread.yield(); // ---该调用是对线程调度器的一种建议，它声明“我已经执行完生命周期中最重要的部分了。刺客正式切换给其他任务执行一段时间的大好时机。”这完全是选择性的
        }

    }
}
