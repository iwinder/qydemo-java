package Others.base.Threads.CAS;

import Utills.PrintUtill;

/**
 * 代码不是线程安全，所以结果可能小于200.
 *
 */
public class CountTest {
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
                                count++;
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
