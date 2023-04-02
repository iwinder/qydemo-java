package DesignPatterns.Structural.Proxy.loggerDemo.staticDeno;

import Utills.PrintUtill;

public class UserService implements BaseService {


    @Override
    public void add() {
        PrintUtill.println("UserService add 调用>>>>>>>>>>windcoder.con>>>>>>>>>>>>>>");
    }

    @Override
    public void query() {
        PrintUtill.println("UserService query 调用>>>>>>>>>>windcoder.con>>>>>>>>>>>>>>");
    }

    @Override
    public void update() {
        PrintUtill.println("UserService update 调用>>>>>>>>>>windcoder.con>>>>>>>>>>>>>>");
    }
}
