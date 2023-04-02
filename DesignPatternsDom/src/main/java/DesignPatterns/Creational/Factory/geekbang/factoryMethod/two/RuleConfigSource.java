package DesignPatterns.Creational.Factory.geekbang.factoryMethod.two;

import DesignPatterns.Creational.Factory.geekbang.base.IRuleConfigParser;
import DesignPatterns.Creational.Factory.geekbang.base.InvalidRuleConfigException;
import DesignPatterns.Creational.Factory.geekbang.base.RuleConfig;
import DesignPatterns.Creational.Factory.geekbang.factoryMethod.one.*;

/**
 *  资源解析器
 *
 *  根据配置文件的后缀（json、xml、yaml、properties），
 *  选择不同的解析器（JsonRuleConfigParser、XmlRuleConfigParser……），
 *  将存储在文件中的配置解析成内存对象 RuleConfig
 */
public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExt = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = null;
        IRuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExt);
        if (null == parserFactory) {
            throw new InvalidRuleConfigException(
                    "Rule config file format is not supported: " + ruleConfigFilePath);
        }
        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private String getFileExtension(String ruleConfigFilePath) {

        //...解析文件名获取扩展名，比如rule.json，返回json
        String ext = "json";
        return ext;
    }
}