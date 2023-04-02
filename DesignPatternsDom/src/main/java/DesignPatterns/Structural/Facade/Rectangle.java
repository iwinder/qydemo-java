package DesignPatterns.Structural.Facade;

import Utills.PrintUtill;

public class Rectangle implements Shape {

    public void draw() {
        PrintUtill.println("Rectangle:draw()");
    }
}
