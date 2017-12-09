package Behavioral.Responsibility;

import Utills.PrintUtill;

public class FileLogger extends AbstractLogger{
    public FileLogger(int level){
        this.level = level;
    }

    protected void write(String message) {
        PrintUtill.println("File::Logger: "+message);
    }
}
