package DesignPatterns.Behavioral.Strategy.thinkInJava;

/**
 * 转成大写
 */
public class Upcase extends Processor {
    String process(Object input){
        return ((String) input).toUpperCase();
    }
}
