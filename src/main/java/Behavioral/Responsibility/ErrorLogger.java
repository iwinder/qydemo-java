package Behavioral.Responsibility;

import Utills.PrintUtill;

public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }
    protected void write(String message) {
        PrintUtill.println("Error Console::Logger: "+message);
    }
}
