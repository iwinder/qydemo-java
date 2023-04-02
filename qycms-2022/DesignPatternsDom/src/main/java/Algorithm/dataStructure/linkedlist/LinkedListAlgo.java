package Algorithm.dataStructure.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LinkedListAlgo {


    /**
     * 检测环
     * 快慢指针
     * @param node
     * @return
     */
    public boolean checkCircle(Node node){
        if (node  == null) return false;
        Node fast = node.next;
        Node slow = node;

        while (fast.next !=null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * 合并有序链表
     * @param l1
     * @param l2
     * @return
     */
    public Node mergeTwoLists(Node l1, Node l2){
        Node soldier = new Node(0,null);// 哨兵结点
        Node p = soldier;
        while ( l1 != null && l2 != null){
            if (l1.data < l2.data){
                p.next = l1;
                l1 = l1.next;
            }else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;
        return soldier.next;
    }


    /**
     *  1 2 3 4 5
     * @param list
     * @param k
     * @return
     */
    public Node removeLastKth(Node list, int k){
        // 快指针
        Node fast = list;
        // 让fast先走k-1步
        int i = 1;
        while (fast!=null && i<k){
            fast = fast.next;
            i++;
        }
        // fast为空说明结点数少于k个
        if (fast == null) return list;
        Node slow = list;
        Node pre = null;
        while (fast.next  !=null){
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        if (pre == null){
            list = list.next;
        }else {
            pre.next = pre.next.next;
        }
        return list;
    }







    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }
}
