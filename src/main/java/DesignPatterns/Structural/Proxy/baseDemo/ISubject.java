package DesignPatterns.Structural.Proxy.baseDemo;

/**
 * 抽象主题角色
 * 负责声明真实主题与代理的共同接口。
 * 可以是接口或者抽象类。
 */
public interface ISubject {
    public void doSomeThing();
}
