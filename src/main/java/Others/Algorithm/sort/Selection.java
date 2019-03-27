package Others.Algorithm.sort;

import Utills.PrintUtill;

import java.util.Scanner;


/**
 * 选择排序
 *
 * 不断选择剩余元素中最小的排到前面，
 *
 * 1 22 12 3 56 77 39 2
 */
public class Selection {



    private static boolean less(Comparable a, Comparable b){
        return  a.compareTo(b)<0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a){
        for (Comparable t : a) {
            PrintUtill.print(t + " ");
        }
        PrintUtill.printlnRule();
    }

    public static void main(String[] args) {
        PrintUtill.println("输入数据，空格隔开：");
        Scanner sc = new Scanner(System.in);
        String read = sc.nextLine();
        String falg = null;
//        while(sc.hasNextLine()){
//            falg = sc.nextLine();
//            if(falg.equals("\n"))  {
//                break;
//            }
//            read = falg;
//        }
//        if(!sc.hasNext()){
//            sc.close();
            PrintUtill.println("输入完成......");
//        }
        read = read.trim();
        String[] d =  read.split(" ");
        Integer[] a = new Integer[d.length];
        for (int i = 0;i<d.length;i++){
            a[i] = Integer.parseInt(d[i]);
        }
        sort(a);
        PrintUtill.println("排序结果");
        show(a);
        sc.close();


    }

    private static void sort(Comparable[] a){
        int n = a.length;
        int min;
        for (int i=0; i<n-1; i++) {
            min = i;
            for (int j=i+1; j<n;j++){
                if (less(a[j],a[min])){
                    min =j;
                    exch(a,i,min);
                }
            }
        }
    }
}
