package Others.base.Generics.generator.coffee;

import Others.base.Generics.generator.Generator;
import Utills.PrintUtill;

import java.util.Iterator;
import java.util.Random;

/**
 * 参数化的Generator接口确保next()的返回值是参数的类型。
 * CoffeeGenerator同时还实现了Iterable接口，所以可以在循环语句中使用。
 * 但需要一个“末端哨兵”来判断何时停止，这正是第二个构造器的功能。
 */
public class CoffeeGenerator implements Generator<Coffee>,Iterable<Coffee> {
    private Class[] types = {
            Latte.class,Mocha.class,Cappuccino.class, Ameicano.class, Breve.class
    };
    private static Random rand = new Random(47);

    public CoffeeGenerator(){}

    private int size = 0;
    public CoffeeGenerator(int sz){
        size = sz;
    }
    public Coffee next(){
        try {
            return (Coffee)types[rand.nextInt((types.length))].newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    class  CoffeeItreator implements Iterator<Coffee>{
        int count = size;
        public boolean hasNext(){
            return count>0;
        }

        public Coffee next(){
            count--;
            return CoffeeGenerator.this.next();
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }

    };
    public Iterator<Coffee> iterator() {
        return new CoffeeItreator();
    }


    public static void main(String[] args) {
        CoffeeGenerator gen = new CoffeeGenerator();
        for (int i=0;i<5;i++){
            PrintUtill.println(gen.next());
        }
        for(Coffee c:new CoffeeGenerator(5)){
            PrintUtill.println(c);
        }
    }
}
