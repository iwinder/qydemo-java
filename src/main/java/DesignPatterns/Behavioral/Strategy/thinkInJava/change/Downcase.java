package DesignPatterns.Behavioral.Strategy.thinkInJava.change;

public class Downcase extends StringProcessor {
    public String process(Object input) {
        return ((String) input).toLowerCase();
    }
}
