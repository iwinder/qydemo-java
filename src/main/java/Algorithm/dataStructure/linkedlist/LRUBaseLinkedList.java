package Algorithm.dataStructure.linkedlist;

import Utills.PrintUtill;

import java.util.Random;
import java.util.Scanner;

public class LRUBaseLinkedList<T> {
    private final int DEFAUL_CAPACIPY = 10;
    private SNode<T> head;
    private Integer length;
    private Integer capacipy;

    public LRUBaseLinkedList() {
        this.head = new SNode<>();
        this.length = 0;
        this.capacipy = DEFAUL_CAPACIPY;
    }

    public LRUBaseLinkedList(Integer capacipy) {
        this.head = new SNode<>();
        this.length = 0;
        this.capacipy = capacipy;
    }

    public void add(T data){
        SNode preNode = findPreNode(data);
        if (preNode != null){
            // 删除节点
            deleteElemOptim(preNode);
        }else {
            // 节点不存在，队列是否已满
            if (length>=capacipy){
                // 已满，删除队尾
                deleteElemAtEnd();
            }
        }
        // 将节点插入到头
        intsertElemAtBegin(data);
    }

    public void deleteElemOptim(SNode node){
        node.setNext(node.getNext().getNext());
        length--;
//        SNode node = head;
//        while (node.next != null){
//            if (node.getNext().element.equals(data)){
//                node.getNext().setNext(node.getNext().getNext());
//                break;
//            }
//        }
    }

    public void deleteElemAtEnd(){
        SNode node = head;

        if (node.getNext() == null) return;

        while (node.getNext().getNext() != null){
            node = node.getNext();
        }
        node.getNext().setNext(null);
        length--;

    }
    public void intsertElemAtBegin(T data){
        SNode node = new SNode(data, head.getNext());
        head.setNext(node);
        length++;
    }



    /**
     * 获取查找到元素的前一个节点
     * @param data
     * @return
     */
    private SNode findPreNode(T data){
       SNode node = head;
       while (node.getNext() != null){
           if (node.getNext().element.equals(data)){
               return node;
           }
           node = node.getNext();
       }
       return null;
    }

    private void printAll(){
        SNode node = head.getNext();
        while (node!=null){
            PrintUtill.print(node.element+">");
            node = node.getNext();
        }
        PrintUtill.printlnRule();

    }



    public class SNode<T> {
        private T element;
        private SNode next;

        public SNode() {
            this.next = null;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }


    public static void main(String[] args) {
        LRUBaseLinkedList<Integer> list = new LRUBaseLinkedList<Integer>();
        Random random = new Random(20);
        int num = 0;
        for (int i=0;i<20;i++){
            num = random.nextInt(20);
            list.add(num);
            PrintUtill.println("插入"+ num + ":");
            list.printAll();
        }

    }
}
