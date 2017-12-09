package Structural.Proxy;

import Utills.PrintUtill;

public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("hahaha.jpg");

        image.display();
        PrintUtill.println("");
        image.display();
    }
}
