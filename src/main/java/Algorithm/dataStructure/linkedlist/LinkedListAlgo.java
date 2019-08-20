package Algorithm.dataStructure.linkedlist;

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
        // 对链表进行尾插法操作
        while (curre!=null){
            // 记录要处理的下一个结点
            next = curre.next;
            // 当前结点的下一个结点指向前驱结点，这样当前结点就插入到了反转链表的头部
            curre.next = pre;
            // 记录当前结点为前驱结点，完成一次尾插
            pre = curre;
            // 当前结点指向下一个要处理的结点
            curre = next;
        }
        return pre;
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
