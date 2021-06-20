package DesignPatterns.Structural.Proxy.loggerDemo.dynamic;

import Utills.PrintUtill;

public class ALiPayService implements PayService{
    @Override
    public void pay() {
        PrintUtill.println("微信支付>>>>>>WXPayService>>>>>>>>>pay>>>>>>>>>>>");
    }
}
