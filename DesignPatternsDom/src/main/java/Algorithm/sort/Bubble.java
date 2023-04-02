package Algorithm.sort;

import Utills.PrintUtill;
import Utills.SortUtils;

public class Bubble {
    public static void main(String[] args) {
        Integer[] a = {5, 2, 4, 6, 1, 3,8,7,9,0};
        sort(a);
        PrintUtill.println("排序结果");
        SortUtils.show(a);
    }

    /**
     * 4，5，6，3，2，1
     *
     *
     * 冒泡，从后向前排序，j+1>j则，交换j+1与J的顺序，将j+1的值向前一位，</b>
     * 与选择排序类似，但这个直接交换顺序，选择排序内层排序先记录下标，最后交换。</b>
     *
     * 冒泡排序只会操作相邻的两个数据。
     * 每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。
     * 如果不满足就让它俩互换。
     * 一次冒泡会让至少一个元素移动到它应该在的位置，重复 n 次，就完成了 n 个数据的排序工作。
     *
     *
     *
     * 冒泡的过程只涉及相邻数据的交换操作，只需要常量级的临时空间，所以它的空间复杂度为 O(1)，是一个原地排序算法。
     *
     * 在冒泡排序中，只有交换才可以改变两个元素的前后顺序。
     * 为了保证冒泡排序算法的稳定性，当有相邻的两个元素大小相等的时候，我们不做交换，
     * 相同大小的数据在排序前后不会改变顺序，所以冒泡排序是稳定的排序算法。
     *
     * 最好情况下，要排序的数据已经是有序的了，我们只需要进行一次冒泡操作，就可以结束了，所以最好情况时间复杂度是 O(n)。
     * 而最坏的情况是，要排序的数据刚好是倒序排列的，我们需要进行 n 次冒泡操作，所以最坏情况时间复杂度为 O(n^2）
     * @param arr
     */
    public static void sort(Comparable[] arr){
        int len = arr.length;
        boolean flag;
        for (int i=0;i<len;i++){
            flag = false;
            for (int j=0;j<len-1-i;j++) {
                    if (SortUtils.less(arr[j+1],arr[j])){
                        SortUtils.swap(arr,j+1, j);
                        flag = true;
                    }
            }
            if (!flag){
                break;
            }
        }
    }
}
