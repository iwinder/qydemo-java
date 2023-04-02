package base.Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CounterDemo1 {
    private final Map<String, Long> urlCounter = new ConcurrentHashMap<String, Long>();
    private static int count =0;
    //接口调用次数+1
    public long increase(String url) {
        count++;
        Long oldValue = urlCounter.get(url);
        Long newValue = (oldValue == null) ? 1L : oldValue + 1;
        urlCounter.put(url, newValue);
        return newValue;
    }



    public long increase2(String url) {
        Long oldValue, newValue;
        synchronized (this) {
            oldValue = urlCounter.get(url);
            if (oldValue == null) {
                newValue = 1l;
                //初始化成功，退出循环
                if (urlCounter.putIfAbsent(url, 1l) == null){

                }
//                    break;
                //如果初始化失败，说明其他线程已经初始化过了
            } else {
                newValue = oldValue + 1;
                //+1成功，退出循环
                if (urlCounter.replace(url, oldValue, newValue)){

                }
//                    break;
                //如果+1失败，说明其他线程已经修改过了旧值
            }
        }
        return newValue;
    }

    public long increase3(String url) {
        Long oldValue, newValue;

        if (urlCounter.containsKey(url)){
            oldValue = urlCounter.get(url);
            newValue = oldValue+1;
            urlCounter.put(url, newValue);
        }else {
            newValue = 1l;
            urlCounter.putIfAbsent(url, newValue);
        }
        return newValue;
    }



    //获取调用次数
    public Long getCount(String url){
        return urlCounter.get(url);
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        final CounterDemo1 counterDemo = new CounterDemo1();
        int callTime = 100000;
        final String url = "http://localhost:8080/hello";
        CountDownLatch countDownLatch = new CountDownLatch(callTime);
        //模拟并发情况下的接口调用统计
        for(int i=0;i<callTime;i++){
            executor.execute(new Runnable() {
//                @Override
                public void run() {
                    counterDemo.increase3(url);
//                    counterDemo.increase2(url);
                    countDownLatch.countDown();
                    count++;
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        //等待所有线程统计完成后输出调用次数
        System.out.println("调用次数："+counterDemo.getCount(url));
        System.out.println("调用次数2："+count);

        Map<String,String> test = new HashMap<String, String>();
        test.put("s","s");
    }
}
