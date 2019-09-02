package Algorithm.dataStructure.linkedlist;

import Utills.PrintUtill;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * LRU缓存淘汰算法数组实现</b>
 * 不支持null的缓存
 * @param <T>
 */
public class LRUBasedArray<T> {
    private final int DEFAUL_CAPACITY = 10;
    private Integer capacity;
    private Integer count;
    private T[] value;
    /**
     * 用于元素记录所在数组位置
     */
    private Map<T, Integer> holder;

    public LRUBasedArray() {
        this.capacity = this.DEFAUL_CAPACITY;
        this.value = (T[]) new Object[capacity];
        this.count = 0;
        this.holder = new HashMap<T, Integer>(capacity);
    }

    public LRUBasedArray(Integer capacity) {
        this.capacity = capacity;
        this.value = (T[]) new Object[capacity];
        this.count = 0;
        this.holder = new HashMap<T, Integer>(capacity);
    }

    /**
     * 缓存数据
     * @param data
     */
    public void add(T data){
        if (data == null){
            throw new IllegalArgumentException("该缓存容器不支持null!");
        }
        Integer index = holder.get(data);
        if (index != null){
            // 向右移动
            update(index);
        }else {
            // 是否已满
            if (isFull()){
                // 删除，更新
                removeAndCache(data);
            }else {
                // 右移元素更新
                cache(data, count);
            }
        }
    }

    /**
     * 数据之前已在数组中，将数组中的对应数据更新到数组开始。
     * @param index
     */
    private void update(Integer index){
        T key = value[index];
        rightOffer(index);
        value[0] = key;
        holder.put(key,0);
    }

    /**
     * 向数组插入新数据
     * @param data
     * @param end
     */
    private void cache(T data, Integer end){
        rightOffer(end);
        value[0] = data;
        holder.put(data,0);
        count++;
    }

    /**
     * 删数组最后一位，并将新数据保存到数组开始
     * @param data
     */
    private void removeAndCache(T data){
        T key = value[--count];
        holder.remove(key);
        cache(data, count);
    }


    /**
     * index左侧的统一向右移动一位
     * @param index
     */
    private void rightOffer(Integer index){
        for (int i=index;i>0;i--){
            value[i] = value[i-1];
            holder.put(value[i],i);
        }
    }

    /**
     * 判断数组是否已满
     * @return
     */
    private boolean isFull(){
        return count == capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(value[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LRUBasedArray<Integer> array = new LRUBasedArray();
        Random random = new Random(20);
        int num = 0;
        for (int i=0;i<20;i++){
            num = random.nextInt(20);
            array.add(num);
            PrintUtill.println("插入"+ num + ":");
            PrintUtill.println(array.toString());
        }
    }

}
