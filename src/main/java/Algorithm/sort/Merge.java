package Algorithm.sort;

import Utills.SortUtils;

/**
 * 归并排序的核心思想
 *
 * 如果要排序一个数组，我们先把数组从中间分成前后两部分，然后对前后两部分分别排序，
 * 再将排好序的两部分合并在一起，这样整个数组就都有序了。
 *
 * 归并排序使用的就是 分治思想。
 * 分治，顾名思义，就是分而治之，将一个大问题分解成小的子问题来解决。小的子问题解决了，大问题也就解决了。
 *
 * 归并排序用的是分治思想，可以用递归来实现
 */
public class Merge {
    private static Comparable[] aux;
    public static void main(String[] args) {

    }

    /**
     * 将所有的元素复制到aux[]中，然后归并回a[]中。
     * 方法在归并时进行了四次判断：
     * 左半边用尽（取右半边的元素）、
     * 右半边用尽（取左半边的元素）、
     *  右半边的当前元素小于左半边的当前元素（取右半边的元素）、
     *  右半边的当前元素大于等于左半边的当前元素（取左半边的元素）
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
    public static void merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        for (int k = lo;k<=hi;k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k<=hi;k++) {
            if (i > mid) {
                a[k] = aux[j++];
            }else if (j>hi) {
                a[k] = aux[i++];
            }else if (SortUtils.less(aux[j], aux[i])) {
                a[k] = aux[j++];
            }else {
                a[k] = aux[i++];
            }
        }

    }

    /**
     * 自顶向下归并排序
     * @param a
     */
    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
    }

    public static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo,mid);   // 将左半边排序
        sort(a,mid+1,hi); // 将右半边排序
        merge(a, lo, mid, hi); // 归并结果
    }


    /**
     * 自底向上的归并排序
     * @param a
     */
    public void sortBU(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1;sz<N;sz = sz+sz){
            for (int lo = 0;lo<N-sz;lo+=sz+sz){
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
            }
        }
    }


}
