package Utills;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

public class ListUtils<T> {



//    public List<Object> distinctOneList(List<Object> list) {
//        return list.stream().collect(
//                Collectors.collectingAndThen(Collectors.toCollection(
//                        () -> new ConcurrentSkipListSet<>(Comparator.comparing(value->value))), ArrayList::new)
//        );
//    }

//    public List<? extends  Number> distinctOneList(List<? extends  Number> list) {
//        return list.stream().collect(
//                Collectors.collectingAndThen(Collectors.toCollection(
//                        () -> new ConcurrentSkipListSet<>(Comparator.comparing(value->value.longValue()))), ArrayList::new)
//        );
//    }
}
