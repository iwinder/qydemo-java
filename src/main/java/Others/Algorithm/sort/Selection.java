package Others.Algorithm.sort;

import Utills.PrintUtill;
import Utills.SortUtils;

import java.util.Scanner;


/**
 * 选择排序
 *
 * 1 22 12 3 56 77 39 2
 *
 * 不断选择剩余元素中最小的排到前面，
 *
 * 工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 *
 * 选择排序的主要优点与数据移动有关。
 * 如果某个元素位于正确的最终位置上，则它不会被移动。
 * 选择排序每次交换一对元素，它们当中至少有一个将被移到其最终位置上，
 * 因此对  n个元素的表进行排序总共进行至多 n-1 次交换。
 *
 * 在所有的完全依靠交换去移动元素的排序方法中，选择排序属于非常好的一种。
 *
 *
 * 选择排序的交换操作介于 0和  (n-1)次之间。选择排序的比较操作为 n(n-1)/2次。
 * 选择排序的赋值操作介于 0 和 3(n-1)次之间。
 *
 *
 * 比较次数O(n^2)，比较次数与关键字的初始状态无关，总的比较次数  N=(n-1)+(n-2)+...+1=n*(n-1)/2。交换次数 O(n)，
 *
 * 最好情况是，已经有序，交换0次；最坏情况是，逆序，交换n-1次。
 * 交换次数比冒泡排序较少，由于交换所需CPU时间比比较所需的CPU时间多，n值较小时，选择排序比冒泡排序快。
 *
 * 原地操作几乎是选择排序的唯一优点，当空间复杂度要求较高时，可以考虑选择排序；
 * 实际适用的场合非常罕见。
 *
 */
public class Selection {

    public static void main(String[] args) {
        PrintUtill.println("输入数据，空格隔开：");
        Scanner sc = new Scanner(System.in);
        String read = sc.nextLine();
        PrintUtill.println("输入完成......");
        read = read.trim();
        String[] d =  read.split(" ");
        Integer[] a = new Integer[d.length];
        for (int i = 0;i<d.length;i++){
            a[i] = Integer.parseInt(d[i]);
        }
        sort(a);
        PrintUtill.println("排序结果");
        SortUtils.show(a);
        sc.close();


    }

    private static void sort(Comparable[] a){
        int n = a.length;
        int min;
        for (int i=0; i<n-1; i++) {
            // 初始化未排序序列中最小数据数组下标
            min = i;
            for (int j=i+1; j<n;j++){
                // 在未排序元素中继续寻找最小元素，并保存其下标
                if (SortUtils.less(a[j],a[min])){
                    min =j;
                }
            }
            // 在未排序元素中继续寻找最小元素，并保存其下标
            if (min != i) {
                SortUtils.swap(a,i,min);
            }
        }
    }
}
