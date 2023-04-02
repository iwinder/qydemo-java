package DesignPatterns.Structural.Bridge.bobaDemo;

import Utills.PrintUtill;

public class BoboClient {
    public static void main(String[] args) {
        Boba boba = new Boba(new SexyTeaBrand(),new BigSku());
        PrintUtill.println(boba.desc()+"："+boba.getCost());

    }
}
