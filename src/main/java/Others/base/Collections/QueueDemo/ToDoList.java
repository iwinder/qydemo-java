package Others.base.Collections.QueueDemo;


import Utills.PrintUtill;

import java.util.PriorityQueue;

/**
 * 优先级队列---- to-do列表
 * 该列表中每个对象都包含一个字符串和一个主要的及次要的优先级值，
 * 该队列的排序顺序也是通过实现Comparable而进行控制的。
 */
public class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {
    static class ToDoItem implements Comparable<ToDoItem> {
        private char primary;
        private int secodary;
        private String item;

        public ToDoItem(String td, char pri, int sec){
            primary = pri;
            secodary = sec;
            item = td;
        }

        public int compareTo(ToDoItem o) {
            if (primary > o.primary)
                return +1;
            if (primary == o.primary){
                if (secodary > o.secodary){
                    return +1;
                }else if (secodary == o.secodary){
                    return 0;
                }
            }

            return -1;
        }

        @Override
        public String toString() {
            return Character.toString(primary) + secodary + ": " + item;
        }
    }
    public void add(String td, char pri, int sec){
        super.add(new ToDoItem(td, pri, sec));
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.add("Empty trash", 'C', 4);
        toDoList.add("Feed dog", 'A', 2);
        toDoList.add("Feed bird", 'B', 7);
        toDoList.add("Mow lawn", 'C', 3);
        toDoList.add("Water lawn", 'A', 1);
        toDoList.add("Feed cat", 'B', 1);
        while(!toDoList.isEmpty()){
            PrintUtill.println(toDoList.remove());
        }
    }
}
