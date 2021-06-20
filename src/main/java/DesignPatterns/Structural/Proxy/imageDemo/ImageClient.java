package DesignPatterns.Structural.Proxy.imageDemo;

import Utills.PrintUtill;

public class ImageClient {

    public static void main(String[] args) {
        Image image = new ProxyImage("hahaha.jpg");

        image.display();
        PrintUtill.println("");
        image.display();
    }
}
