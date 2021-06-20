package DesignPatterns.Structural.Proxy.imageDemo;

import Utills.PrintUtill;

public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk();
    }
    public void display() {
        PrintUtill.println("Displaying "+fileName);
    }

    private void loadFromDisk(){
        PrintUtill.println("Loading "+fileName);
    }
}
