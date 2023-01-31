package com.dongfangyh.algo;

public class Iter {


    public static boolean primeDetect(int number){

        if (number < 0){
            throw new IllegalArgumentException("arg should not be negative number.");
        }

        if (number <= 1){
            return false;
        }

        if (number == 2) {
            return true;
        }

        // if a number greater than 2 and is an even number, it can't be a prime number
        if (number % 2 == 0){
            return false;
        }

        boolean isPrime = true;
        for (int i = number/2; i > 2; i--){
            if (number % i == 0){
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++ ){
            boolean result = primeDetect(i);
            System.out.println(i + " is " + (result ? "" : "not ") + "a prime number.");
        }
    }
}
