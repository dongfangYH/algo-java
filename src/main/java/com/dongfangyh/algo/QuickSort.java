package com.dongfangyh.algo;

import java.util.Arrays;

/**
 * @author henry.liu
 * @date 2023/8/28
 **/
public class QuickSort {

    public static int[] sort(int[] arr, int startIdx, int endIdx){
        if (startIdx >= endIdx){
            return arr;
        }
        int lIdx = startIdx;
        int rIdx = endIdx;
        int base = arr[startIdx];
        boolean voidInL = true;
        while (lIdx < rIdx){
            if (voidInL){
                if (arr[rIdx] >= base){
                    rIdx--;
                }else {
                    arr[lIdx] = arr[rIdx];
                    voidInL = false;
                }
            }else {
                if (arr[lIdx] <= base){
                    lIdx++;
                }else {
                    arr[rIdx] = arr[lIdx];
                    voidInL = true;
                }
            }
        }
        arr[lIdx] = base;

        sort(arr, startIdx, lIdx - 1);
        sort(arr, lIdx + 1, endIdx);
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {47, 29, 71, 99, 78, 19, 24, 47};
        System.out.println(Arrays.toString(sort(arr, 0,  arr.length - 1)));
    }
}
