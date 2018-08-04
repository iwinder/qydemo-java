package Others.base.Generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类型参数推断避免了重复的泛型参数列表。
 */
public class New {
    public static <K,V>Map<K,V> map(){
        return new HashMap<K, V>();
    }

    public static <T> List<T> list(){
        return new ArrayList<T>();
    }

    public static void main(String ars[]){
        Map<String,List<String>> sls = New.map();

    }
}
