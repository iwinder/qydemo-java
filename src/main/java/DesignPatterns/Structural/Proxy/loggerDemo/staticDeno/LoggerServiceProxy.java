package DesignPatterns.Structural.Proxy.loggerDemo.staticDeno;

import Utills.PrintUtill;

import java.text.SimpleDateFormat;

public class LoggerServiceProxy implements BaseService{
    private BaseService baseService;
    private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");

    public LoggerServiceProxy(BaseService baseService) {
        this.baseService = baseService;
    }

    @Override
    public void add() {
        before();
        this.baseService.add();
        after();
    }

    @Override
    public void query() {
        before();
        this.baseService.query();
        after();
    }

    @Override
    public void update() {
        before();
        this.baseService.update();
        after();
    }

    private void before() {
        PrintUtill.println("日志代理开始>>>>>>>>>>>>>" + sdf.format(System.currentTimeMillis()) + ">>>>>>>>>>>");
    }

    private void after() {
        PrintUtill.println("日志代理完成>>>>>>>>>>>>>" + sdf.format(System.currentTimeMillis()) + ">>>>>>>>>>>");
    }
}
