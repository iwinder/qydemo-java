package DesignPatterns.Structural.Proxy.loggerDemo.staticDeno;

import Utills.PrintUtill;

public class LoggerClinet {
    public static void main(String[] args) {
        LoggerServiceProxy logger = new LoggerServiceProxy(new UserService());
        logger.add();
        PrintUtill.println("\n");
        logger.query();
        PrintUtill.println("\n");
        logger.update();
        PrintUtill.printlnRule();
        logger = new LoggerServiceProxy(new BookService());
        logger.add();
        PrintUtill.println("\n");
        logger.query();
        PrintUtill.println("\n");
        logger.update();

    }
}
