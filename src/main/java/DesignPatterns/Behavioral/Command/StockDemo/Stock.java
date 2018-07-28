package DesignPatterns.Behavioral.Command.StockDemo;

import Utills.PrintUtill;

/**
 * 1、命令接收者角色
 */
public class Stock {
    private String name = "好吃的";
    private int quantity = 10;

    public void buy(){
        PrintUtill.println("Stock [ Name: "+name+", Quantity: "+quantity+"] bought");
    }

    public void sell(){
        PrintUtill.println("Stock [ Name: "+name+", Quantity: "+quantity+"] sold");
    }
}
