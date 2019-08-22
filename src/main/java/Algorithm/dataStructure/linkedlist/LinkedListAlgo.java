package Algorithm.dataStructure.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LinkedListAlgo {

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
     * 基于数组比较是否为回文
     * 1 2 3 4 5 4 3 slow 4 fast 3
     * 1 2 3 4 5 4   slow 3 fast 5
     * @param node
     * @return
     */
//    public boolean isPalindromeByArray(Node node){
//        if (node == null) return false;
//        Node fast = node;
//        Node slow = node;
//        if (node.next == null) return true;
//
//        /**
//         * 找到中间结点，同时保存用数组逆插左侧元素。
//         *  nodeList.add(0, slow.data); 在指定位置插入元素，原位置及之后的依次向右移动一位。
//         */
//        List<Integer> nodeList = new ArrayList<Integer>();
//        nodeList.add(0, slow.data); // 1 2 3
//        while (fast.next != null && fast.next.next != null ) {
//
//            fast = fast.next.next;
//            slow = slow.next;
//            nodeList.add(0, slow.data); // 1 2 3
//        }
//
//        Node curr = slow;
//        if (fast.next == null){
//            // fast.next为空，数据为奇数。
//            curr = slow.next;
//        }
//        int i = 0;
//        while (null != curr){
//            if (curr.data != nodeList.get(i)){
//                return false;
//            }
//            curr = curr.next;
//            i++;
//        }
//        return true;
//    }


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
        while (fast.next != null && fast.next.next != null ) {
            nodeList.push(slow.data); // 1 2 3
            fast = fast.next.next;
            slow = slow.next;
        }

        Node curr = slow;
        if (fast.next == null){
            // fast.next为空，数据为奇数。
            curr = slow.next;
        }
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
