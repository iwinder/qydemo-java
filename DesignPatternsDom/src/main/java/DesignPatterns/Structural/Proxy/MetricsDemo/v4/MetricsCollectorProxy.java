package DesignPatterns.Structural.Proxy.MetricsDemo.v4;

import DesignPatterns.DesignConcept.Aggregator.v2.RequestInfo;
import DesignPatterns.DesignConcept.Aggregator.v5.MetricsCollector;
import DesignPatterns.Structural.Proxy.MetricsDemo.v2.IUserController;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 的动态代理利用反射机制，动态生成一个实现原始实现的类接口的匿名类，在调用原始类具体方法前调用InvocationHandler来处理。
 * cglib动态代理是利用asm开源包，修改原始类的字节码动态生成原始类的子类来处理。
 *
 */
public class MetricsCollectorProxy {
    private MetricsCollector metricsCollector;

    public MetricsCollectorProxy() {
        this.metricsCollector = new MetricsCollector();
    }

    public Object createProxy(Object proxiedObject) {
        Class<?>[] interfaces = proxiedObject.getClass().getInterfaces();
        DynamicProxyHandler handler = new DynamicProxyHandler(proxiedObject);
        return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(), interfaces, handler);
    }

    private  class DynamicProxyHandler implements InvocationHandler {

        private Object proxiedObject;

        public DynamicProxyHandler(Object proxiedObject) {
            this.proxiedObject = proxiedObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTimeStamp = System.currentTimeMillis();
            Object result = method.invoke(proxiedObject, args);
            long endTimeStamp = System.currentTimeMillis();
            long responseTime = endTimeStamp - startTimeStamp;
            String apiName = proxiedObject.getClass().getName() + ":" + method.getReturnType();
            RequestInfo requestInfo = new RequestInfo(apiName, (double) responseTime, startTimeStamp);
            metricsCollector.recordRequest(requestInfo);
            return result;
        }
    }

    /**
     * 使用示例
     * @param args
     */
    public static void main(String[] args) {
        MetricsCollectorProxy metricsCollectorProxy = new MetricsCollectorProxy();
        IUserController userController = (IUserController)metricsCollectorProxy.createProxy(new UserController());
    }
}
