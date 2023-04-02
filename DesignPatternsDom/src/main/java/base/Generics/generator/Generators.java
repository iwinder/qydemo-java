package base.Generics.generator;

import base.Generics.generator.coffee.Coffee;
import base.Generics.generator.coffee.CoffeeGenerator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用于Generator的泛型方法
 * 利用生成器，可以方便的填充一个Collection
 */
public class Generators {
    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n){
        for (int i=0;i<n;i++){
            coll.add(gen.next());
        }
        return coll;
    }

    public static void main(String[] args) {
        Collection<Coffee> coffees = fill(
                new ArrayList<Coffee>(), new CoffeeGenerator(),4
        );

        for (Coffee c: coffees){
            System.out.println(c);
        }
        Collection<Integer> fnumbers = fill(
                new ArrayList<Integer>(), new Fibonacci(),12
        );

        for (int i: fnumbers){
            System.out.print(i + ", ");
        }
    }
}
