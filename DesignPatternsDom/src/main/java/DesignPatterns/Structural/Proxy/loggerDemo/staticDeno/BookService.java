package DesignPatterns.Structural.Proxy.loggerDemo.staticDeno;

import Utills.PrintUtill;

public class BookService implements BaseService{

    @Override
    public void add() {
        PrintUtill.println("BookService add 调用>>>>>>>>>>windcoder.con>>>>>>>>>>>>>>");
    }

    @Override
    public void query() {
        PrintUtill.println("BookService query 调用>>>>>>>>>>windcoder.con>>>>>>>>>>>>>>");
    }

    @Override
    public void update() {
        PrintUtill.println("BookService update 调用>>>>>>>>>>windcoder.con>>>>>>>>>>>>>>");
    }
}
