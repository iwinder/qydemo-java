package base.Collections.HashDemo.demo1;

import java.util.Random;

public class Prediction {
    private static Random rand = new Random(47);
    private boolean shodow = rand.nextDouble() > 0.5;
    public String toString(){
        if(shodow){
            return "Six more weeks of Winter!";
        }else{
            return "Early Spring!";
        }
    }

}
