package Utills;

public class SortUtils {

    private SortUtils(){}

    /**
     * 判断a是否小于b
     * @param a
     * @param b
     * @return
     */
    public static boolean less(Comparable a, Comparable b){
        return  a.compareTo(b)<0;
    }

    /**
     * 交换数组a的i和j位置上的数据
     * @param a
     * @param i
     * @param j
     */
    public static void swap(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a){
        for (Comparable t : a) {
            PrintUtill.print(t + " ");
        }
        PrintUtill.printlnRule();
    }
}
