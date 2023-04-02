package DesignPatterns.Behavioral.Responsibility;

import Utills.PrintUtill;

public class ChainPatternDemo {
    private static AbstractLogger getChainOfLoggers(){
        AbstractLogger erorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        erorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return erorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();
        loggerChain.logMessage(AbstractLogger.INFO,"This is an information.");
        PrintUtill.println("");

        loggerChain.logMessage(AbstractLogger.DEBUG,
                "This is an debug level information.");
        PrintUtill.println("");

        loggerChain.logMessage(AbstractLogger.ERROR,
                "This is an error level information.");
        PrintUtill.println("");


    }
}
