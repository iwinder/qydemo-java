package Others.base.Collections;

import Others.base.cloneDemo.Person;
import Utills.PrintUtill;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CounterDemo2 {
    private static final Map<String, Person> Persons = new ConcurrentHashMap<String, Person>();

    public static Person getLdapTemplate(Person person){
        String key = String.valueOf(person.getCode());
        Boolean s = null;
        count2++;
        person.setA(String.valueOf(count2));
        if(Persons.containsKey(key)) {
            s = isNotOldPerson(Persons.get(key), person);
            if(s) {
                Persons.put(key, person);
            }
        }else {
            Persons.putIfAbsent(key, person);
        }
        PrintUtill.println("Persons.get(key):"+Persons.get(key).toString()+" s:"+s);
        return Persons.get(key);
    }

    private static boolean isNotOldPerson(Person person, Person newPerson) {
        Boolean isOld = true;
        if (person!=null) {
            isOld =( !person.getName().equals(newPerson.getName())  ||
                    person.getAge()!=newPerson.getAge() || !person.getCode().equals(newPerson.getCode()));

        }
        return isOld;
    }
    private static int count =0;
    private static int count2 =0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        int callTime = 200;
        Person person = new Person("lilioq"+99,199,99);
        getLdapTemplate(person);
        person = new Person("alioq"+29,129,29);
        getLdapTemplate(person);
        person = new Person("aslioq"+22,222,22);
        getLdapTemplate(person);
        person = new Person("awind"+152,1523,152);
        getLdapTemplate(person);
        CountDownLatch countDownLatch = new CountDownLatch(callTime);
        for(int i=0;i<callTime;i++){
            count = i;
            executor.execute(new Runnable() {
                Person person = new Person("lili"+count,count,count);
                public void run() {
                    getLdapTemplate(person);
                    countDownLatch.countDown();
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
        System.out.println("调用次数："+Persons.size());

        PrintUtill.printlnRule();
        PrintUtill.println(Persons.toString());
        Map<String, Person> map = new TreeMap<String, Person>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj1.compareTo(obj2);
                    }
                });
        map.putAll(Persons);
        PrintUtill.printlnRule();
        PrintUtill.println(map.toString());
    }

}
