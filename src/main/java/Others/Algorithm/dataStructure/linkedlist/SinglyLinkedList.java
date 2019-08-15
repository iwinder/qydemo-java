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
            while (q!=null){
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

}
