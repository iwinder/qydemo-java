package Algorithm.sort;

import Utills.PrintUtill;
import Utills.SortUtils;

/**
 *  希尔排序思想：使数组中任意间隔为h的元素都是有序的。这样的数组被称为h有序数组。</b>
 *
 *  一个h有序数组就是h个相互独立的有序数组编织在一起组成的一个数组。
 *
 *  在进行排序的摔时候，如果h很大，我们就能将元素移动到很远的地方，为实现更小的h有序创造方便。
 *
 *  用这种方式，对于任意以1为结尾的h序列，我们都能够将数组排序。
 */
public class Shell {
    public static void main(String[] args) {
        Integer[] a = {5, 2, 4, 6, 1, 3,8,7,9};
        sort(a);
        PrintUtill.println("排序结果");
        SortUtils.show(a);
    }

    public static void sort(Comparable[] arr){
        int len = arr.length; // 9
        int h = 1;
        while (h<len/3) {
            h = 3*h+1;
        }
        while (h>=1){ // 4 1 ~0
            // 将数组arr变为好有序
            for (int i=h; i<len; i++) { // 4 5 6 7 8 ~9;1 2 3 4 5 6 7 8
                // 将a[i]插入到a[i-h],a[i-2h],a[i-3h]...之中
                for (int j = i; j>=h && SortUtils.less(arr[j],arr[j-h]);j-=h){// 4 0;5 1;
                    SortUtils.swap(arr, j, j-h);
                }
            }
            h = h/3;
        }
    }
}
