package DesignPatterns.Behavioral.Strategy.thinkInJava;

public class Apply {
    /**
     * 可以接收任何类型的Processor，并将其应用到一个Objcect对象上，然后打印出结果。
     * 像本例中，创建一个能够根据所传递的参数对象的不同而具有不同行为的方法，被称为策略设计模式。
     * 这类方法包含所要执行的算法中固定不变的部分，而“策略”包含变化的部分。
     * 策略就是传递进去的参数对象，它包含要执行的代码。
     * 这里，Processor对象就是一个策略，在main()中可看到有三种不同类型的策略应用到String类型的s对象上。
     * @param p
     * @param s
     */
    public static void process(Processor p,Object s){
        System.out.println("Using Process: "+p.getName());
        System.out.println(p.process(s));
    }
    public static String s = "Disagreement with beliefs is by definition incorrect";

    public static void main(String[] args) {
        process(new Upcase(),s);
        process(new Downcase(),s);
        process(new Splitter(),s);
    }
}
