package Others.base.SimpleProxy.DynamicProxyDemo;

import Others.base.SimpleProxy.Animal;
import Others.base.SimpleProxy.Dog;
import Utills.PrintUtill;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * 通过调用静态方法Proxy.newProxyInstance()可以创建动态代理，这个方法需要得到：
 *  1. 一个类加载器（你通常可以从已经被加载的对象获取其类加载器，然后传递给它，如此处的Animal.class.getClassLoader()）
 *  2. 一个你希望该代理类实现的接口列表（不是类或抽象类）,如 new Class[]{Animal.class}
 *  3. InvocationHandler接口的一个实现，如DynamicProxyHandler
 *
 *  动态代理可以将所有调用重定向到调用处理器。
 *  通常会向调用处理器的构造器传递一个“实际”对象的引用，从而使得调用处理器在执行其中介任务时，可以将请求转发。
 *
 *  invoke()方法中传递进来了代理对象，以防使用时需要区分请求的来源，但在许多情况下并不关心这一点。
 *
 *  在invoke()内部，在代理调用方法时，对接口的调用将被重定向为对代理的调用,因此要格外当心。
 *
 *  通常，执行被代理的操作，然后使用Method.invoke()将请求转发给被代对象，并传入必需的参数。
 *  初看起来可能有些受限，就像只能执行泛化操作一样。
 *  但可以通过传递其他的参数，来过滤某些方法的调用。
 */
public class SimpleDynamicProxy {
    public static void consumer(Animal iface){
        iface.doBark();
        iface.somethingElse("windCoder.com DynamicProxy");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        consumer(dog);
        // Insert a proxy and call again
        Animal prox = (Animal) Proxy.newProxyInstance(
                Animal.class.getClassLoader(),
                new Class[]{Animal.class},
                new DynamicProxyHandler(dog)
        );
        consumer(prox);
        PrintUtill.println("---------------------------");
        showProxyClass();
    }


    public static void showProxyClass() {
        String path = "./$Proxy0.class";
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0",
                Dog.class.getInterfaces());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
