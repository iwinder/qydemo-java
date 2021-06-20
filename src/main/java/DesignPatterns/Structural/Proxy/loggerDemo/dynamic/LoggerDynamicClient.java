package DesignPatterns.Structural.Proxy.loggerDemo.dynamic;

import DesignPatterns.Structural.Proxy.loggerDemo.staticDeno.BaseService;
import DesignPatterns.Structural.Proxy.loggerDemo.staticDeno.UserService;
import Utills.PrintUtill;

public class LoggerDynamicClient {
    public static void main(String[] args) {
        LoggerDynamicProxy proxy =  new LoggerDynamicProxy();
        PayService pay = (PayService) proxy.getInstance(new WXPayService());
        pay.pay();
        PrintUtill.printlnRule();
        //
        BaseService userService = (BaseService) proxy.getInstance(new UserService());
        userService.add();

    }
}
