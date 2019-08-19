package Algorithm.sort;

import Utills.PrintUtill;
import Utills.SortUtils;

public class Insertion {
    public static void main(String[] args) {
        Integer[] a = {55, 94, 87, 1, 4, 32, 11, 77, 39, 42, 64, 53, 70, 12, 9};
        sort(a);
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
            Comparable temp = arr[i]; // 2 4 6 1 3
            int j = i - 1;
            //如果将赋值放到下一行的for循环内, 会导致在第10行出现j未声明的错误
            for (; j >= 0 &&  SortUtils.less(temp, arr[j] ); j-- ) {
                arr[j + 1] = arr[j];
            }

            arr[j + 1] = temp;
        }
    }
}
