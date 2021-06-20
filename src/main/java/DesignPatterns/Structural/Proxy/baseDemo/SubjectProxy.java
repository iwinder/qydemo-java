package DesignPatterns.Structural.Proxy.baseDemo;

import Utills.PrintUtill;

/**
 * 代理主题角色
 * 也被成为代理类。由于内部持有真实主题角色的引用，所以可以完全代理真实主题角色，同时可以在调用真实对象的方法前增加一些新的处理代码。
 *
 * 代理模式主要用于代码增强，将非功能性的附加需求与业务功能解耦，放到代理中统一处理，方便开发人员只关注业务功能方面的开发。
 */
public class SubjectProxy implements ISubject{
    private ISubject subject;

    public SubjectProxy(ISubject subject) {
        this.subject = subject;
    }

    @Override
    public void doSomeThing() {
        before();
        this.subject.doSomeThing();
        after();
    }

    private void before() {
        PrintUtill.println("真正对象执行操作之前的附加功能>>>>>>>>>");
    }

    private void after() {
        PrintUtill.println("真正对象执行操作之后的附加功能>>>>>>>>>");
    }

}
