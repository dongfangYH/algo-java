package com.dongfangyh.algo;

public class DP {

    public static long fibonacci(int n){
        if (n < 0) {
            throw new IllegalArgumentException("n can not be negative.");
        }
        if (n == 0 || n == 1){
            return 1l;
        }
        long[] store = new long[n+1];
        store[0] = 1l;
        store[1] = 1l;
        for (int i = 2; i <= n; i++){
            store[i] = (store[i - 1] + store[i - 2]);
        }
        return store[n];
    }

    /**
     * example : arr = int[]{1, 3, 4, 2, 7, 9, 6, 8}
     * fx[i] means the maximum ascending sequence which ends in arr[i]
     * @param arr
     * @return the length of maximum ascending sequence in @param arr
     */
    public static int lis(int[] arr){
        if (arr.length == 0){
            return 0;
        }
        if (arr.length == 1){
            return 1;
        }
        int[] fx = new int[arr.length];
        fx[0] = 1;
        for (int i = 1; i < arr.length; i++){
            int cl = 1;
            for (int j = i - 1; j >= 0; j--){
                if (arr[i] >= arr[j]){
                    cl = Math.max(fx[j] + 1, cl);
                }
            }
            fx[i] = cl;
        }

        int r = 0;
        for (int tr : fx){
            r = Math.max(r, tr);
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(50));

        int[] testData = new int[]{1, 3, 4, 2, 7, 9, 6, 8};
        System.out.println(lis(testData));
        int[] testData2 = new int[]{1, 3, 3, 4, 2, 7, 9, 6, 8};
        System.out.println(lis(testData2));
    }
}
