package DesignPatterns.Structural.Proxy.loggerDemo.dynamic;

import net.sf.cglib.core.DebuggingClassWriter;

public class LoggerCGlibProxyClient {
    public static void main(String[] args) {
        // 设置输出目录，方便之后查看CGLIB生成的class---非必选项
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./tmp/");
        LoggerCGlibProxyInterceptor proxy = new LoggerCGlibProxyInterceptor();
        PayService pay = (PayService) proxy.getInstance(WXPayService.class);
        pay.pay();
    }
}
