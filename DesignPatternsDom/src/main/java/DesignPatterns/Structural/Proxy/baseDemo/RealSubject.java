package DesignPatterns.Structural.Proxy.baseDemo;

import Utills.PrintUtill;

/**
 * 真实主题角色
 * 也被称作被代理类，负责执行系统的真正业务逻辑。
 */
public class RealSubject implements ISubject{
    @Override
    public void doSomeThing() {
        PrintUtill.println("真正的对象做一些事情");
    }
}
