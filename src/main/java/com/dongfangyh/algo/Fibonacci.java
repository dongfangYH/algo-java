package com.dongfangyh.algo;

public class Fibonacci {

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

    public static void main(String[] args) {
        System.out.println(fibonacci(50));
    }
}
