package Behavioral.Strategy.thinkInJava;

/**
 * 处理器
 */
public class Processor {
    public String getName(){
        return getClass().getSimpleName();
    }

    Object process(Object input){
        return input;
    }
}
