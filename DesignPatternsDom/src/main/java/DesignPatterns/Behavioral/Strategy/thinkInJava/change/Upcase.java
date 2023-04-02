package DesignPatterns.Behavioral.Strategy.thinkInJava.change;

public class Upcase extends StringProcessor {
    public String process(Object input) {
        return ((String) input).toUpperCase();
    }
}
