package Algorithm.dataStructure.stack;

import Utills.PrintUtill;

public class ArrayStack {
    private Integer[] data;
    private int capacipy;
    private int count;

    public ArrayStack(int capacipy) {
        this.data = new Integer[capacipy];
        this.capacipy = capacipy;
        this.count = 0;
    }

    public boolean push(int i){
        if (count == capacipy) return false;
        data[count] = i;
        count++;
        return true;
    }

    public Integer pop(){
        if (count == 0) return null;
        Integer tmp = data[--count];
        return tmp;
    }

    public static void main(String[] args) {
        Integer[] demo = {1,2,3,4,5,6,7};

        ArrayStack stck = new ArrayStack(8);

        for(Integer i : demo){
            PrintUtill.print(i+" ");
            stck.push(i);
        }
        PrintUtill.printlnRule();
        int lean = stck.count;
        for (int i =0;i<lean;i++){
            PrintUtill.print(stck.pop()+" ");
        }
    }
}
