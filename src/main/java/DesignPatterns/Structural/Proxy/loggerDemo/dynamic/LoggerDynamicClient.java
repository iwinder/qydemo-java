package DesignPatterns.Structural.Proxy.loggerDemo.dynamic;

import DesignPatterns.Structural.Proxy.loggerDemo.staticDeno.BaseService;
import DesignPatterns.Structural.Proxy.loggerDemo.staticDeno.UserService;
import Utills.PrintUtill;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

public class LoggerDynamicClient {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        LoggerDynamicProxyHandler proxy =  new LoggerDynamicProxyHandler();
        PayService pay = (PayService) proxy.getInstance(new WXPayService());
        pay.pay();
        PrintUtill.printlnRule();
        //
        BaseService userService = (BaseService) proxy.getInstance(new UserService());
        userService.add();

//        showProxyClass();
    }
    public static void showProxyClass() {
        String path = "./$Proxy0.class";
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0",
                WXPayService.class.getInterfaces());
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
