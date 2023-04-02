package DesignPatterns.Creational.Factory.geekbang.base;

public class JsonRuleConfigParser implements IRuleConfigParser {
    @Override
    public RuleConfig parse(String configText) {
        RuleConfig config = new RuleConfig();
        // ...省略具体实现
        return config;
    }
}
