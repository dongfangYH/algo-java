package com.dongfangyh.algo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author henry.liu
 * @date 2023/8/24
 **/
public class Fib {

    public static long fib(int n){
        if (n == 0){
            return 0L;
        }
        if (n == 1){
            return 1L;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static long fib2(int n){
        long[] record = new long[n+1];
        record[0] = 0L;
        record[1] = 1L;

        int c = 2;

        while (c <= n){
            long v = record[c - 2] + record[c - 1];
            record[c++] = v;
        }
        return record[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(10));
        System.out.println(fib2(50));
    }
}
