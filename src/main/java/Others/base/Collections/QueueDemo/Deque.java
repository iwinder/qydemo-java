package Others.base.Collections.QueueDemo;

import java.util.LinkedList;

/**
 * 双向队列（双端队列）就像是一个队列，但可以在任何一端添加或移除元素。
 * 在LinkedList中包含支持双向队列的方法，但Java标准库中没有任何显式地用于双向队列的接口。
 * 因此，LinkedList无法实现这样的接口，也无法去向上转型到Deque.
 * 但可以使用组合来创建一个Deque类，并直接从LinkedList中暴露相关的方法。
 *
 * 不太可能在两端都放入元素并抽取它们，因此，Deque不如Queue常用。
 * @param <T>
 */
public class Deque<T> {
    private LinkedList<T> deque = new LinkedList<T>();

    public void addFirst(T e){
        deque.addFirst(e);
    }

    public void addLast(T e){
        deque.addLast(e);
    }
    public T getFirst(){
        return deque.getFirst();
    }

    public T getLast(){
        return deque.getLast();
    }

    public T removeFirst(){
        return deque.removeFirst();
    }

    public T removeLast(){
        return deque.removeLast();
    }

    public int size(){
        return deque.size();
    }

    public String toString(){
        return deque.toString();
    }
}
