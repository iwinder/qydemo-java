package base.Threads.Base;

import base.Threads.ThreadLocal.SequenceNumber;
import Utills.PrintUtill;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapDemo {
    public static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer> ();
    public static AtomicInteger  count = new AtomicInteger();
    public static void main(String[] args) {


        for(int i=0;i<100;i++){
//            final int key =count.incrementAndGet();
            new Thread(new Runnable() {
                Integer old;
                @Override
                public void run() {
                    for (int j=0;j<20;j++){
//                        old = map.get(j);
//                        count.set(old ==null ? 1:old);
//                        map.put(count.incrementAndGet(),j);
//                        map.compute(j,(k, oldValue)-> count.incrementAndGet() );
                        map.compute(j,(k, oldValue)-> oldValue==null ? 1 : (oldValue+1) );
//                        add(j,1);
//                        add2(j,1);
//                        map.compute(j,(k, oldValue)-> oldValue==null ? : (oldValue+1) );
//                        add3(j ,1);
//                        map.compute(j,(k, oldValue)-> oldValue==null ? 1 : count.incrementAndGet() );
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.forEach((k,v)->PrintUtill.println(k+ " : " + v));

        PrintUtill.println("Count = "+count);
    }

    public static void add(Integer key, Integer value){
        Integer oldValue = map.get(key);
        while(true){
            if (null == oldValue){
                if (map.putIfAbsent(key, value) == null) {
                    break;
                }
            }else {
                 value = oldValue+1;
//                count.set(oldValue);
//                value = count.incrementAndGet();
                if (map.replace(key, oldValue, value)) {
                 break;
                }

            }
        }

    }

    public static void add3(Integer key, Integer value){
        Integer oldValue = map.get(key);
            if (null == oldValue){
                if (map.putIfAbsent(key, value) == null) {
//                    break;
                }
            }else {
//                value = oldValue+1;
                count.set(oldValue);
                value = count.incrementAndGet();
                if (map.replace(key, oldValue, value)) {
//                    break;
                }

            }


    }



    public static void add2(Integer key, Integer value){
//        Integer oldValue = map.get(key);
        map.computeIfAbsent(key,k->{
            Integer oldValue = map.get(key);
            return oldValue==null? 1 : count.incrementAndGet();});
//        while(true){
//            if (null == oldValue){
//                if (map.putIfAbsent(key, value) == null) {
//                    break;
//                }
//            }else {
//                value = oldValue +1;
//                if (map.replace(key, oldValue, value)) {
//                    break;
//                }
//
//            }
//        }

    }

//    private static class TestClient extends Thread{
////        private SequenceNumber sn;
////        public TestClient(SequenceNumber sn){
////            this.sn = sn;
////        }
//        ConcurrentHashMap map = new ConcurrentHashMap();
//        public void run(){
//            //④每个线程打出3个序列值
//            for (int i = 0; i < 3;i++){
//                map.put()
////                System.out.println("thread["+Thread.currentThread().getName()+"] sn["+sn.getNextNum()+"]");
////                System.out.println("thread["+Thread.currentThread().getName()+
////                        "] sn["+this.sn.getNextNum()+"]");
//            }
//        }
//    }
}
