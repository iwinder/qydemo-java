package DesignPatterns.Structural.Proxy.baseDemo;

public class SubejctClient {
    public static void main(String[] args) {
        SubjectProxy subjectProxy = new SubjectProxy(new RealSubject());
        subjectProxy.doSomeThing();
    }
}
