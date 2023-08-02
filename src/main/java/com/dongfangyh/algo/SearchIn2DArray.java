package com.dongfangyh.algo;

/**
 * @author henry.liu
 * @date 2023/7/19
 **/
public class SearchIn2DArray {

    /**
     * 1 2 8  9
     * 2 4 9  12
     * 4 7 10 13
     * 6 8 11 15
     * 查询二维数组中是否包含指定数字
     * @param arr
     * @param target
     * @return
     */
    public boolean search(int[][] arr, int target){
        for (int r = 0; r < arr.length; r++){
            for (int i = 0; i < arr[r].length; i++){
                if (arr[r][i] == target){
                    return true;
                }
                if (arr[r][i] > target){
                    break;
                }
            }
        }
        return false;
    }

    public boolean search2(int[][] arr, int target){
        int r = 0;
        int c = arr[r].length - 1;
        while (c >= 0 && r <= arr.length - 1) {
            if (arr[r][c] == target){
                return true;
            }
            if (arr[r][c] > target){
                c--;
                continue;
            }
            if (arr[r][c] < target){
                r++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        int target = 0;
        SearchIn2DArray instance = new SearchIn2DArray();
        System.out.println(instance.search(arr, target));
        System.out.println(instance.search2(arr, target));
    }
}
