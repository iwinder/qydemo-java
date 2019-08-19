package base.Collections.QueueDemo;


import Utills.PrintUtill;

public class DequeTest {
    static void fillTest(Deque<Integer> deque){
        for (int i=20; i< 27; i++){
            deque.addFirst(i);
        }
        for (int i = 50; i< 55;i++){
            deque.addLast(i);
        }
    }

    public static void main(String[] args) {
        Deque<Integer> di = new Deque<Integer>();
        fillTest(di);
        PrintUtill.println(di);

        PrintUtill.printlnRule();

        while (di.size() != 0){
            PrintUtill.print(di.removeLast() + " ");
        }

    }
}
