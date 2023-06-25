package com.dongfangyh.algo;

/**
 * @author henry.liu
 * @date 2023/5/30
 **/
public class HIndex {
    public static int hIndex(int[] citations) {
        int[] bucket = new int[citations.length + 1];
        for (int i = 0; i < citations.length; i++){
            if (citations[i] >= citations.length){
                bucket[citations.length]++;
            }else {
                bucket[citations[i]]++;
            }
        }
        int sum = 0;
        for (int j = bucket.length - 1; j > 0; j--){
            sum += bucket[j];
            if (sum >= j){
                return j;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[]citations = {1};
        System.out.println(hIndex(citations));
    }
}
