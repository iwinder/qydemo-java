package Utills;

public class SortUtils {

    private SortUtils(){}

    public static boolean less(Comparable a, Comparable b){
        return  a.compareTo(b)<0;
    }

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
