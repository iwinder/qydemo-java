package Behavioral.Strategy.thinkInJava;

import java.util.Arrays;

/**
 *
 * split()方法是String类的一部分，它接收String类型的对象，并以传递进来的参数作为边界，将该Strign对象分隔开，然后返回一个数组String[]。
 * 在这里被用来当做创建String数组的快捷方式。
 */
public class Splitter extends Processor {
    String process(Object input){
        return Arrays.toString(((String) input).split(" "));
    }
}
