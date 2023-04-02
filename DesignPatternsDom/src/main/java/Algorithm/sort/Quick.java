package Algorithm.sort;

import Utills.SortUtils;

public class Quick {
    private static void sort(Comparable[] a, int lo, int hi){
        if (hi<=lo) return;
        int j =  partition(a, lo, hi);
        sort(a,lo,j-1);
        sort(a,j+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int l = lo, r = hi+1;
        Comparable pivot = a[lo];
        while (l!=r){
            while (l<r && SortUtils.less(a[l], pivot)) {
                l++;
            }
            while(r>l && SortUtils.less(a[r], pivot)){
                r++;
            }
            if (l<r){
                Comparable p = a[l];
                a[l] = a[r];
                a[r] = p;
            }
        }
        a[lo] = a[l];
        a[l] = pivot;
        return l;
    }

}
