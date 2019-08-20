package Algorithm.dataStructure.linkedlist;

import Utills.PrintUtill;

import java.util.Random;
import java.util.Scanner;

/**
 * LRU缓存淘汰算法单链表实现</b>
 * TODO: 维护一个有序单链表（此处实现为带头单链表），越靠近链表尾部的结点是越早之前访问的。
 * 当有一个新的数据被访问时，从链表头开始顺序遍历链表。
 * 若此数据之前已被存在链表中，遍历得到并从原位置删除，再插入到链表头部。
 * 若此数据之前未被存在链表中：
 * 若缓存未满，将该数据插入链表头部；
 * 若缓存已满，删除链表尾部节点，将新数据插入链表头部，
 *
 * @param <T>
 */
public class LRUBaseLinkedList<T> {
    /**
     * 默认容量
     */
    private final int DEFAUL_CAPACITY = 10;

    /**
     * 头结点
     */
    private SNode<T> head;
    /**
     * 链表长度
     */
    private Integer length;
    /**
     * 链表容量
     */
    private Integer capacipy;

    public LRUBaseLinkedList() {
        this.head = new SNode<>();
        this.length = 0;
        this.capacipy = DEFAUL_CAPACITY;
    }

    public LRUBaseLinkedList(Integer capacipy) {
        this.head = new SNode<>();
        this.length = 0;
        this.capacipy = capacipy;
    }

    /**
     *  缓存数据
     * @param data
     */
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

    /**
     * 删除某个结点
     * @param node
     */
    public void deleteElemOptim(SNode node){
        node.setNext(node.getNext().getNext());
        length--;
    }

    /**
     * 删除链表尾部的结点
     */
    public void deleteElemAtEnd(){
        SNode node = head;

        if (node.getNext() == null) return;

        while (node.getNext().getNext() != null){
            node = node.getNext();
        }
        node.getNext().setNext(null);
        length--;

    }

    /**
     * 在头部插入结点
     * @param data
     */
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
