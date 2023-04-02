package DesignPatterns.Behavioral.Strategy.thinkInJava;

/**
 * 转成小写
 */
public class Downcase extends Processor {
    String process(Object input){
        return ((String) input).toLowerCase();
    }
}
