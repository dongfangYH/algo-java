package com.dongfangyh.algo;

import java.util.Arrays;

/**
 * @author henry.liu
 * @date 2023/7/4
 **/
public class Test {
    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 6, 8, 11, 11, 12, 17};
        int[] b = new int[]{2, 5, 7, 15};
        int[] rs = combine(a, b);
        System.out.println(Arrays.toString(rs));
    }

    private static int[] combine(int[] a, int[] b) {
        int[] rs = new int[a.length + b.length];
        int idx = rs.length - 1;
        int aId = a.length - 1;
        int bId = b.length - 1;
        while (true){

            if (aId < 0){
                while (bId >= 0){
                    rs[idx] = b[bId];
                    bId--;
                    idx--;
                }
                break;
            }

            if (bId < 0){
                while (aId >= 0){
                    rs[idx] = a[aId];
                    aId--;
                    idx--;
                }
                break;
            }

            if (a[aId] >= b[bId]){
                rs[idx] = a[aId];
                aId--;
            }else {
                rs[idx] = b[bId];
                bId--;
            }
            idx--;
        }
        return rs;
    }
}
