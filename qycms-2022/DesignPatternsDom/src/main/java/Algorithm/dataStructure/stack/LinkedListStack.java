package Algorithm.dataStructure.stack;

public class LinkedListStack {
    private Node top = null;

    public void push(int data){
        Node node = new Node(data, null);
        if (top == null){
            top = node;
        }else {
            node.next = top;
            top = node;
        }
    }

    public int pop(){
        if (top == null) return -1;
        int value = top.data;
        top = top.next;
        return value;
    }

    private static class Node {
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
