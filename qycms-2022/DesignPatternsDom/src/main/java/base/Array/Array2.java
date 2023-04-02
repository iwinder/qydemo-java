package base.Array;

import Utills.PrintUtill;

public class Array2 {
    public static void main(String[] args) {
        // 基础数据
        int[] s = new int[3];
        PrintUtill.println(s[1]);
        int[] s2 = new int[]{1,2,3};
        int[] s3 = {1,2,3};
        PrintUtill.println(s2==s3);
        int[] s4 = s3;
        s4[1] = 2;
        for(int i=0;i<s3.length;i++) {
            PrintUtill.println("a3-"+i+":"+s3[i]);
            PrintUtill.println("a4-"+i+":"+s4[i]);
            PrintUtill.printlnRule();
        }

        // 对象
    }
}
