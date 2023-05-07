package com.dongfangyh.algo;

public class BinarySearch {

    /**
     *
     * @param arr
     * @return -1 if the given target was not found, else return index of the arr
     */
    public static int binarySearch(int[] arr, int target){
        int l = 0, r = arr.length - 1;
        if (r == l) {
            return arr[r] == target ? r : -1;
        }

        // 1 2 3 4 5
        // 0 1 2 3 4 t = 2

        while ( r - l >= 1){
            if (arr[r] == target) {
                return r;
            }
            if (arr[l] == target){
                return l;
            }
            int mid =  (l + r) >> 1;
            if (arr[mid] == target){
                return mid;
            }
            if (arr[mid] > target){
                r = mid - 1;
            }
            if (arr[mid] < target){
                l = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 13, 15, 19, 21};
        System.out.println(binarySearch(arr, 2));
    }
}
