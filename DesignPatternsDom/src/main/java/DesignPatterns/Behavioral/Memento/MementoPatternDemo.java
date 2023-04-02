package DesignPatterns.Behavioral.Memento;

import Utills.PrintUtill;

public class MementoPatternDemo {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        PrintUtill.println("Current State: "+originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        PrintUtill.println("First saved State: "+originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        PrintUtill.println("Second saved State: "+originator.getState());
    }
}
