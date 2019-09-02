package Algorithm.dataStructure.linkedlist;

import Utills.PrintUtill;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SinglyLinkedList {
    private Node head = null;

    /**
     * 插入到表头
     * @param value
     */
    public void insertToHead(int value){
        Node node = new Node(value, null);
        insertToHead(node);
    }

    /**
     *  将节点插入表头
     * @param newNode
     */
    public void insertToHead(Node newNode){
        if (head==null){
            head = newNode;
        }else {
             newNode.next = head;
             head = newNode;
        }
    }

    /**
     * 添加新元素结点
     * @param value
     */
    public void insertTail(int value){
        Node newNode = new Node(value, null);
        if (head==null){
            head = newNode;
        }else {
            Node q = head;
            while (q.next!=null){
                q = q.next;
            }
            newNode.next = q.next;
            q.next = newNode;
        }
    }

    /**
     * 在结点之后插入
     * @param p
     * @param value
     */
    public void insertAfter(Node p, int value){
        Node newNode = new Node(value, null);
        insertAfter(p, newNode);
    }

    /**
     * 在结点之后插入
     * @param p
     * @param newNode
     */
    public void insertAfter(Node p, Node newNode){
        if (p==null) return;

        newNode.next = p.next;
        p.next = newNode;
    }

    /**
     * 结点之前插入
     * @param p
     * @param value
     */
    public void insertBefore(Node p, int value){
        Node newNode = new Node(value, null);
        insertBefore(p, newNode);
    }

    /**
     * 结点之前插入
     * @param p
     * @param newNode
     */
    public void insertBefore(Node p, Node newNode){
        if (p==null) return;
        if (p==head){
            insertToHead(newNode);
            return;
        }
        Node q = head;
        while (q!=null && q.next!=p){
            q = q.next;
        }
        if(q==null) return;
        newNode.next = p;
        q.next = newNode;
    }

    /**
     * 根据结点删除
     * @param oldNode
     */
    public void deleteByNode(Node oldNode){
        if (oldNode == null || head == null) return;
        if (oldNode == head) {
            head = head.next;
            return;
        }
        Node p = head;
        while (p != null && p.next != oldNode ){
            p = p.next;
        }
        if (p == null) return;
        p.next = oldNode.next;
    }

    /**
     * 根据值删除结点
     * @param value
     */
    public void deleteByValue(int value){
        if (head == null) return;

        Node p = head;
        Node q = null;
        while ( p != null && p.data != value){
            q = p;
            p = p.next;
        }
        if (p==null) return;
        if (q == null){
            head = head.next;
        }else {
            q.next = q.next.next;
        }
    }


    // 链表反转
    /**
     * 带头链表反转
     * @param p
     * @return
     */
    public Node inverseLinkList_head(Node p){
        Node root = new Node(0,null);
        root.next = p;


        Node curr = p.next;
        p.next = null;

        Node next = null;

        while (curr != null){
            next = curr.next;
            curr.next = root.next;
            root.next = curr;
            curr = next;
        }

        return root.next;

    }

    /**
     * 带逻辑头结点链表反转
     * http://wiki.jikexueyuan.com/project/for-offer/question-sixteen.html
     * @param head
     * @return
     */
    public static Node reverseList(Node head) {
        // 创建一个临时结点，当作头插法的逻辑头结点
        Node root = new Node(0, null);
        // 逻辑头结点点的下一个结点为空
        root.next = null;
        // 用于记录要处理的下一个结点
        Node next;
        // 当前处理的结点不为空
        while (head != null) {
            // 记录要处理的下一个结点
            next = head.next;
            // 当前结点的下一个结点指向逻辑头结点的下一个结点
            head.next = root.next;
            // 逻辑头结点的下一个结点指向当前处理的结点
            root.next = head;
            // 上面操作完成了一个结点的头插
            // 当前结点指向下一个要处理的结点
            head = next;
        }
        // 逻辑头结点的下一个结点就是返回后的头结点
        return root.next;
    }

    /**
     * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
     * @param list
     * @return
     */
    public Node reverse(Node list){
        if (list == null || list.getNext() == null) return list;
        // 用于记录当前处理的结点的
        Node curre = list;
        // 用于记录当前结点的前驱结点
        // 前驱结点开始为null，因为反转后的最后一个结点的下一个结点，即null
        // 也是反转链表的头结点
        Node pre = null;
        // 当前结点的下一个结点
        Node next = null;
        // 对链表进行头插法操作
        while (curre!=null){
            // 记录要处理的下一个结点
            next = curre.next;
            // 当前结点的下一个结点指向前驱结点，这样当前结点就插入到了反转链表的头部
            curre.next = pre;
            // 记录当前结点为前驱结点，完成一次头插
            pre = curre;
            // 当前结点指向下一个要处理的结点
            curre = next;
        }
        return pre;
    }

    /**
     * 不设置逻辑头结点的链表反转
     *  1 2 3 4 5 8
     * @param list
     * @return
     */
    public Node inverseLinkList(Node list){
        if (list == null || list.next == null) return list;

        Node pre = null;
        Node curre = list;
        Node next = null;

        while (curre.next!=null){
            next = curre.next;
            curre.next = pre;
            pre = curre;
            curre = next;
        }
        curre.next = pre;
        return curre;
    }

    // 判断是否为回文

    /**
     * 不含逻辑头节点的回文链表判断
     * 思路：
     * 遍历一遍链表,得到链表长度n,根据长度的奇偶,找到中间节点,将左半边的链表反转,然后从中间节点分两个方向向左右两边遍历,是否是回文;
     * 对左半部分链表进行反转,还原为最初的链表（目前函数未实现左半部分链表还原）
     * 只需要固定的若干个临时变量,不需要额外开辟空间
     * 时间复杂度为O(n),空间复杂度为O(1)
     * 1 2 3 4 5 8 9 slow 4 fast 9
     * 1 2 3 4 5 8  slow  3 fast 5
     * 其他思路：
     * https://juejin.im/post/5c806fc5518825146453fa61
     * https://studygolang.com/articles/15727
     * @return
     */
    public  boolean isPalindrome(Node head){
        if (head == null) return false;
        PrintUtill.println("开始执行找到中间节点");
        Node fast = head;
        Node slow = head;
        if (fast.next == null){
            System.out.println("只有一个元素");
            return true;
        }
        /**
         * 快的一次走两步,慢的一次走一步那么最后快的结束了慢的走了一半
         */
        while (fast.next !=null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        Node leftLink = null;
        Node rightLink = null;
        /**
         * 获取中间结点左右两部分，反转左侧部分。
         * fast.next == null ：节点数目为奇数,且slow一定为为中间节点
         * fast.next.next  == null ：节点数目为偶数，slow、slow.next 均为中心结点
         */
        rightLink = slow.next;
        if (fast.next  == null){
            leftLink = inverseLinkListToEnd(slow).next;
        }else {
            leftLink = inverseLinkListToEnd(slow);
        }
        /**
         * 从此处开始同步向两边比较
         */
        return TFResult(leftLink, rightLink);
    }

    /**
     * 比较是否为回文
     * @param left
     * @param right
     * @return
     */
    public boolean TFResult(Node left, Node right){

        while (left != null && right != null){
            if (left.data != right.data){
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
    }


    /**
     * 返回左半部分的中点之前的那个节点，返回以end结点为头节点的链表。
     * @param end
     * @return
     */
    public Node inverseLinkListToEnd(Node end){
        Node pre = null;
        Node cur = head;
        Node next = null;

        /**
         * 反转中间结点之前的结点
         */
        while (cur!=end){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        return cur;
    }

    /**
     * 基于数组比较是否为回文
     * 1 2
     * @param node
     * @return
     */
    public boolean isPalindromeByArray(Node node){
        if (node == null) return false;
        Node fast = node;
        Node slow = node;
        if (node.next == null) return true;

        /**
         * 找到中间结点，同时保存用数组逆插左侧元素。
         *  nodeList.add(0, slow.data); 在指定位置插入元素，原位置及之后的依次向右移动一位。
         */
        List<Integer> nodeList = new ArrayList<Integer>();
        nodeList.add(0, slow.data);
        while (fast.next != null && fast.next.next != null ) {

            fast = fast.next.next;
            slow = slow.next;
            nodeList.add(0, slow.data);
        }


        if (fast.next != null){
            // fast.next不为空，数据为偶数。
            slow = slow.next;
        }
        Node curr = slow;
        int i = 0;
        while (null != curr){
            if (curr.data != nodeList.get(i)){
                return false;
            }
            curr = curr.next;
            i++;
        }
        return true;
    }

    /**
     * 基于栈比较是否为回文
     * @param node
     * @return
     */
    public boolean isPalindromeByStack(Node node){
        if (node == null) return false;
        Node fast = node;
        Node slow = node;
        if (node.next == null) return true;

        /**
         * 找到中间结点，同时保存用数组逆插左侧元素。
         *  nodeList.add(0, slow.data); 在指定位置插入元素，原位置及之后的依次向右移动一位。
         */
        Stack<Integer> nodeList = new Stack<Integer>();
        nodeList.push(slow.data); // 1 2 3
        while (fast.next != null && fast.next.next != null ) {

            fast = fast.next.next;
            slow = slow.next;
            nodeList.push(slow.data); // 1 2 3
        }


        if (fast.next != null){
            // fast.next不为空，数据为偶数。
            slow = slow.next;
        }
        Node curr = slow;
        int i = 0;
        while (null != curr){
            if (curr.data != nodeList.pop()){
                return false;
            }
            curr = curr.next;
            i++;
        }
        return true;
    }

    public static class Node{
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

    public static void main(String[] args) {
        SinglyLinkedList link = new SinglyLinkedList();
        System.out.println("hello");
//        int data[] = {1};
//        int data[] = {1,2};
//        int data[] = {1,2,3,1};
//        int data[] = {1,2,5};
//        int data[] = {1,2,2,1};
//         int data[] = {1,2,5,2,1};
        int data[] = {1,2,5,3,6};

        for(int i =0; i < data.length; i++){
            //link.insertToHead(data[i]);
            link.insertTail(data[i]);
        }



        Node p2 = link.head;
        while(p2 != null){
            System.out.println("ab"+p2.data);
            p2 = p2.next;
        }
        PrintUtill.println("是否为回文：" + link.isPalindromeByArray(link.head));
        // link.printAll();
         Node p = link.reverseList(link.head);
         while(p != null){
             System.out.println("aa"+p.data);
             p = p.next;
         }
        Node p3 = link.head;
        while(p3 != null){
            System.out.println("ac"+p3.data);
            p3 = p3.next;
        }

    }

}
