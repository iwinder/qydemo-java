package base.Collections.HashDemo.demo1;

import Utills.PrintUtill;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * 由Groundhog(3)生成的第一个实例的散列码与由Groundhog(3)生成的第二个实例的散列码是不同的，而我们正是使用后者进行查找的。
 *
 */
public class SpringDetector {
    public static <T extends Groudhog> void detectSpring(Class<T> type) throws Exception{
        Constructor<T> ghog = type.getConstructor(int.class);
        Map<Groudhog,Prediction> map = new HashMap<Groudhog, Prediction>();
        for (int i = 0; i <10;i++){
            map.put(ghog.newInstance(i),new Prediction());

        }
        PrintUtill.println("map = " + map);
        Groudhog gh = ghog.newInstance(3);
        PrintUtill.println("Looking up prediction for " +  gh);
        if (map.containsKey(gh)){
            PrintUtill.println(map.get(gh));
        }else{
            PrintUtill.println("key not found: " + gh);
        }
    }

    public static void main(String[] args) throws Exception {
        detectSpring(Groudhog.class);
    }
}
