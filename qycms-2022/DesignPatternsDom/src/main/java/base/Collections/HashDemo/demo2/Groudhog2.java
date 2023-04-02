package base.Collections.HashDemo.demo2;

import base.Collections.HashDemo.demo1.Groudhog;

public class Groudhog2 extends Groudhog {
    public Groudhog2(int number) {
        super(number);
    }

    public int hashCode(){
        return number;
    }
    public boolean equals(Object o){
        return o instanceof Groudhog2 && (number == ((Groudhog2) o).number);
    }
}
