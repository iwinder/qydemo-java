package Algorithm.sort;

import Utills.PrintUtill;
import Utills.SortUtils;

/**
 * 首先，我们将数组中的数据分为两个区间，排序区间和未排序区间。
 * 初始已排序区间只有一个元素，就是数组的第一个元素。
 * 插入算法的核心思想是取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，并保证已排序区间数据一直有序。
 * 重复这个过程，直到未排序区间中元素为空，算法结束。
 *
 *
 * 插入排序也包含两种操作，一种是元素的比较，一种是元素的移动。
 * 当我们需要将一个数据 a 插入到已排序区间时，需要拿 a 与已排序区间的元素依次比较大小，找到合适的插入位置。
 * 找到插入点之后，我们还需要将插入点之后的元素顺序往后移动一位，这样才能腾出位置给元素 a 插入。
 *
 * 从实现过程可以很明显地看出，插入排序算法的运行并不需要额外的存储空间，所以空间复杂度是 O(1)，也就是说，这是一个原地排序算法。
 *
 * 最好是时间复杂度为 O(n).最坏情况时间复杂度为 O(n^2）。平均时间复杂度为O(n^2）。
 */
public class Insertion {
    public static void main(String[] args) {
        Integer[] a = {55, 94, 87, 1, 4, 32, 11, 77, 39, 42, 64, 53, 70, 12, 9};
        sort3(a);
        PrintUtill.println("排序结果");
        SortUtils.show(a);
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i =1;i<n;i++) {
            for (int j=i; j>0 && SortUtils.less(a[j], a[j-1]); j--) {
                SortUtils.swap(a, j, j-1);
            }
        }
    }

    public static void sort2(Comparable[] a) {
        int n = a.length;
        Comparable key;
        int j;
        for (int i =1;i<n;i++) {
            key = a[i];
            j = i-1;
            while(j>=0 && SortUtils.less(key, a[j-1]) ) {
                a[j+1] = a[j];
                j-- ;
            }
            a[j+1] = key;
        }
    }

    //将arr[i] 插入到arr[0]...arr[i - 1]中 5 2 4 6 1 3
    public static void sort3(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++ ) {
            // 未排序区间的第一个值
            Comparable temp = arr[i]; // 2 4 6 1 3
            // 左侧排序区间的最后一个元素
            int j = i - 1;
            // 从左侧排序区间的最后一个元素起依次向前比较，找到
            //如果将赋值放到下一行的for循环内, 会导致在第10行出现j未声明的错误
            for (; j >= 0 &&  SortUtils.less(temp, arr[j] ); j-- ) {
                arr[j + 1] = arr[j]; // 移动数据
            }

            arr[j + 1] = temp; // 插入数据
        }
    }


    public static void sort4(Comparable[] arr){
        int n = arr.length;
        if (n<1) return;
        for (int i = 1;i<n;i++){
            Comparable value = arr[i];
            int j = i -1;
            for (;j>=0;j--){
                if (SortUtils.less(value,arr[j])){
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }
            arr[j+1] = value;
        }
    }
}
