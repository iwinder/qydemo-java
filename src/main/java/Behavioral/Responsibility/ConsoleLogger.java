package Behavioral.Responsibility;

import Utills.PrintUtill;

public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }
    protected void write(String message) {
        PrintUtill.println("Standaed Console::Logger: "+message);
    }
}
