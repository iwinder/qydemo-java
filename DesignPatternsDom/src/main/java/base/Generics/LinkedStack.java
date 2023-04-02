package base.Generics;

/**
 * 泛型实现堆栈类---递归形式
 *  通过末端哨兵判断堆栈何时为空。
 *  在构造LinkedStack时创建的。
 *  每调用一次push()方法，就会创建一个Node<T>对象,并将其链接到前一个Node<T>对象。
 *  当调用pop()方法时，总返回top.item，然后丢弃当前top所指向的Node<T>，并将top转移到下一个Node<T>对象，除非遇到末端哨兵，此时不再移动top。
 *  当已经到达末端，客户端程序依旧调用pop()方法，则只能得到null,说明堆栈已经空了。
 *
 * @param <T>
 */
public class LinkedStack<T> {

    private static class Node<U> {
        U item;
        Node<U> next;
        Node(){
            item = null;
            next = null;
        }
        public Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        boolean end(){
            return item == null && next ==null;
        }
    }
    // 末端哨兵 End sentinel
    private Node<T> top = new Node<T>();

    public void push(T item){
        top = new Node<T>(item, top);
    }

    public  T pop(){
        T result = top.item;
        if (!top.end()){
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> lss = new LinkedStack<String>();
        for (String s:"WindCoder com stun".split(" ")){
            lss.push(s);
        }
        String s;
        while ((s=lss.pop())!=null){
            System.out.println(s);
        }
    }
}
