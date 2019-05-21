package Others.base.Collections.Stream;

import Others.base.common.User;
import Utills.PrintUtill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListDistinctDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i=0;i<20000;i++){
            list.add(String.valueOf((int)(Math.random()*(i*10+1))));
        }


        distinctOneList(list);
        distinctOneList2(list);
        PrintUtill.printlnRule();
        String concatenated =   list.stream().collect(Collectors.joining(","));
        PrintUtill.println("concatenated: "+concatenated);
        PrintUtill.printlnRule();

        List<User> userlist = new ArrayList<User>();
        int j;
        for (int i=0;i<20000;i++){
            j = (int)(Math.random()*(i*10+1));
            userlist.add(new User("aa"+String.valueOf(j), j,j));
        }
        List s = distinctOneListObj(userlist);
        PrintUtill.printlnRule();
        List<User> s2 = userlist.stream().distinct().collect(Collectors.toList());
        PrintUtill.println("after distinct s2: "+s2.size());
//        distinctOneListObj2(userlist);
//        sortOne(s);
//        List a = distinctOneListObj(userlist);
        PrintUtill.printlnRule();
//        sortOne2(a);
//                List s = distinctOneListObj(userlist);
//        PrintUtill.printlnRule();
//        sortOne(s);
    }
    public static List<String> distinctOneList2(List<String> list){
        PrintUtill.println("before distinct2: "+list.size());
        long st = System.nanoTime();
        List<String> s =  list.stream().distinct().collect(
                Collectors.toList() );
        PrintUtill.println("distinct2 total times: "+(System.nanoTime()-st));
        PrintUtill.println("after distinct2: "+ s.size());
        return list;
    }

    public static List<String> distinctOneList(List<String> list) {
        PrintUtill.println("before distinct: "+list.size());
        long st = System.nanoTime();
        List<String> s = list.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new ConcurrentSkipListSet<>(Comparator.comparing(value->value))), ArrayList::new)
        );
        PrintUtill.println("distinct total times: "+(System.nanoTime()-st));
        PrintUtill.println("after distinct: "+s.size());
        return list;
    }

    public static List<User> distinctOneListObj(List<User> list) {
        PrintUtill.println("before distinct: "+list.size());
        long st = System.nanoTime();
        List<User> s = list.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new ConcurrentSkipListSet<>(Comparator.comparing(value->value.getName()))), ArrayList::new)
        );
        PrintUtill.println("distinct total times: "+(System.nanoTime()-st));
        PrintUtill.println("after distinct: "+s.size());
        return list;
    }

    public static List<User> distinctOneListObj2(List<User> list) {
        PrintUtill.println("before distinct2: "+list.size());
        long st = System.nanoTime();
        List<User> s = list.parallelStream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new ConcurrentSkipListSet<>(Comparator.comparing(value->value.getName()))), ArrayList::new)
        );
        PrintUtill.println("distinct total times2: "+(System.nanoTime()-st));
        PrintUtill.println("after distinct2: "+s.size());
        return list;
    }
    public static void sortOne(List<User> list){
        long st = System.nanoTime();
      List<User>  a = list.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
//        list.stream().sorted(Comparator.comparing(User::getAge));
        PrintUtill.println("sort total times: "+(System.nanoTime()-st));
//        return a;



    }
    public static void sortOne2(List<User> list){
        long st = System.nanoTime();
        Collections.sort(list, new Comparator<User>() {
            public int compare(User u1, User u2) {
                long diff = u1.getAge() - u2.getAge();
                if (diff > 0) {
                    return 1;
                }else if (diff < 0) {
                    return -1;
                }
                return 0;
            }
        });
        PrintUtill.println("sort total times2: "+(System.nanoTime()-st));
    }

//    public static List<User> distinctOneListObj2(List<User> list) {
//        PrintUtill.println("before distinct: "+list.size());
//        long st = System.nanoTime();
//        List<User> s = new ArrayList<User>();
//        for(User usr: list){
//            if(){
//
//            }
//        }
//
//
//
//        List<User> s = list.stream().collect(
//                Collectors.collectingAndThen(Collectors.toCollection(
//                        () -> new ConcurrentSkipListSet<>(Comparator.comparing(value->value.getName()))), ArrayList::new)
//        );
//        PrintUtill.println("distinct total times: "+(System.nanoTime()-st));
//        PrintUtill.println("after distinct: "+s.size());
//        return list;
//    }
}
