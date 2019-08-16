package Others.Algorithm.dataStructure.linkedlist;

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

    public void insertAfter(Node p, int value){
        Node newNode = new Node(value, null);
        insertAfter(p, newNode);
    }

    public void insertAfter(Node p, Node newNode){
        if (p==null) return;

        newNode.next = p.next;
        p.next = newNode;
    }


    public void insertBefore(Node p, int value){
        Node newNode = new Node(value, null);
        insertBefore(p, newNode);
    }

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

    public Node inverseLinkList_head(Node p){
        Node root = new Node(0,null);
        root.next = p;


        Node curr = p.next;
        p.next = null;

        Node next = null;

        while (curr != null){
            // curr 2 5 3 6
            // next保存下一个节点 5 3 6
            // curr.next  1
            // root.next  1 2 5 3 6
            next = curr.next;
            // curr.next保存上一个节点，第一个为p
            curr.next = root.next;
            // root.next ->curr,第一个为null;
            root.next = curr;
            curr = next;
        }

        return root.next;

    }

    public static Node reverseList(Node head) {
        // 创建一个临时结点，当作尾插法的逻辑头结点
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
     *  1 2 3 4 5
     * @param p
     * @return
     */
    public Node inverseLinkList(Node p){
        if (p == null || p.next == null) return p;

        Node pre = null;
        Node r = p;
        Node next = null;

//        Node s = null;
//        while (r!=null){
//            s = r;
//            next = r.next;
//            r.next = pre;
//            pre = r;
//            r = next;
//        }
//        return s;
        while (r.next!=null){
            next = r.next;
            r.next = pre;
            pre = r;
            r = next;
        }
        r.next = pre;
        return r;
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
    }

    public static void main(String[] args) {
        SinglyLinkedList link = new SinglyLinkedList();
        System.out.println("hello");
        //int data[] = {1};
        //int data[] = {1,2};
        //int data[] = {1,2,3,1};
        //int data[] = {1,2,5};
        //int data[] = {1,2,2,1};
        // int data[] = {1,2,5,2,1};
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
