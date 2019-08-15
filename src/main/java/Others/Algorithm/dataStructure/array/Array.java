package Others.Algorithm.dataStructure.array;

import Utills.PrintUtill;

/**
 * 无扩容，导致插入移动后元素丢失。
 */
public class Array {
    // 整型数据data保存数据
    private int[] data;
    // 数组长度
    private int n;
    // 实际元素个数
    private int count;

    public Array( int capacity) {
        this.data = new int[capacity];
        this.n = capacity;
        this.count = 0;
    }

    public int find(int index){
        if(index<0 || index >= count){
            return -1;
        }

        return data[index];
    }

    /**
     * 向索引位置插入新元素
     * <br>
     * 该index位置之后顺序向后移动一位
     * @param index
     * @param value
     * @return
     */
    public boolean insert(int index,int value){
        if(count==n){
            PrintUtill.println("已达到最大值");
            return false;
        }
        if(index<0 || index >= n){
            PrintUtill.println("范围有误");
            return false;
        }
        for(int i = count;i>index;--i){
            data[i] = data[i-1];
        }
        data[index] = value;
        ++count;
        return true;
    }

    /**
     * 根据索引删除元素
     * <br>
     * 从删除位置开始，将后面的元素向前移动一位
     * @param index
     * @return
     */
    public boolean delete(int index){
        if(index<0 || index >= n){
            PrintUtill.println("范围有误");
            return false;
        }
        for (int i = index+1; i<count;i++){
            data[i-1] = data[i];
        }
        count--;
        return true;
    }

    public void printAll(){
        PrintUtill.println("数组中值如下：");
        for (int a: data) {
            PrintUtill.println(a);
        }
    }

    public static void main(String[] args) {
        Array tmp = new Array(5);
//        tmp.insert(1,10);
//        tmp.insert(2,9);
//        tmp.insert(4,3);
//        tmp.insert(3,2);
//        tmp.insert(5,4);
//        tmp.insert(0,23);
        tmp.insert(0, 3);

        tmp.insert(3, 10);
        tmp.insert(0, 4);
        tmp.insert(1, 5);
        tmp.insert(3, 9);
//        tmp.insert(3, 11);
        tmp.printAll();



    }
}
