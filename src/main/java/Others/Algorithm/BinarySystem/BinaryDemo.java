package Others.Algorithm.BinarySystem;

import Utills.PrintUtill;

import java.util.HashMap;

/**              before
 * 001 1   1       1
 * 010 2   1       1
 * 011 3   2       2
 * 100 4   1       1
 * 101 5   2       2
 * 110 6   2       3
 * 111 7   3       4
 * 1011 11
 * 101100 12
 */
public class BinaryDemo {
    public static void main(String[] args) {
        Integer[] map = s(5);
//        for(Integer i:map){
//            PrintUtill.println(i);
//        }
    PrintUtill.printlnRule();
        Integer[] map2 = s2(7);
        for(Integer i:map2){
            PrintUtill.println(i);
        }
        PrintUtill.printlnRule();
        PrintUtill.println("1&0 :"+(1&0));
        PrintUtill.println("7&0 :"+(7&0));
        PrintUtill.println("11<<1 :"+(11<<2));
        PrintUtill.println("n>>1 :"+(11>>1));

        PrintUtill.println("BitCount5: "+BitCount5(7));
    }

    public static Integer[] s(int n){
        long st1 = System.nanoTime();
       Integer[] map = new Integer[n+1];
        int pow2 = 1;
        int before = 1;
        for (int i=1;i<=n;i++) {
            if(i==pow2){
                before = 1;
                map[i] = 1;
                pow2 <<= 1; // 1 2 4 8 16
            } else {
                map[i] = map[before]+1;
                before += 1;
            }
        }
        PrintUtill.println("get s total times: "+(System.nanoTime()-st1));
        return map;
    }

    public static Integer[] s2(int n){
        long st = System.nanoTime();
        Integer[] map = new Integer[n+1];
        int num;
        int nums;
        for (int i=1;i<=n;i++) {
            num = i;
            nums =0;
            while (num!=0){
                num&=(num-1);
                nums++;
            }
            map[i] = nums;
        }
        PrintUtill.println("get s2 total times: "+(System.nanoTime()-st));
        return map;
    }


    /**
     * https://www.cnblogs.com/graphics/archive/2010/06/21/1752421.html
     * https://blog.csdn.net/qq_15905107/article/details/81089565
     * @param n
     * @return
     */
    static  int BitCount5(int n){
        int tmp = n - ((n >>1) &033333333333) - ((n >>2) &011111111111);
        PrintUtill.println("tmp: "+tmp);
        return ((tmp + (tmp >>3))&030707070707)%63;
    }
}
