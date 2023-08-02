package com.dongfangyh.algo;

/**
 * 描述
 * 累加数是一个只包含数字 0 - 9 的字符串，并且字符串中的数字可以形成累加序列。
 * 一个有效的累加序列至少包含 3 个数字，数字长度不固定，除了最开始的两个数之外，序列中的每个后续数字必须是它之前两个数字之和。
 * 给定一个只包含数字 0 - 9 的字符串 s，判断该字符串是否是一个累加数，如果是则返回 true，否则，返回 false。
 * ----------------------------------------------------------------------------------------------------
 * 样例1：
 * 输入："11235813"
 * 输出：true
 * 解释：
 * 累加序列为：1，1，2，3，5，8，13
 * 1 + 1 = 2
 * 1 + 2 = 3
 * 2 + 3 = 5
 * 3 + 5 = 8
 * 5 + 8 = 13
 * ----------------------------------------------------------------------------------------------------
 * 样例2：
 * 输入："01212"
 * 输出：true
 * 解释：
 * 累加序列为：0，12，12
 * 0 + 12 = 12
 * ----------------------------------------------------------------------------------------------------
 * 样例3：
 * 输入："111"
 * 输出：false
 * ----------------------------------------------------------------------------------------------------
 * @author henry.liu
 * @date 2023/6/25
 **/
public class CumulativeNumber {

    public static boolean isCumulativeNumber(String numberStr) {
        try{
            if (numberStr == null || numberStr.length() < 3){
                return false;
            }
            // 代表每个序列数的位数 n1Len <= n2Len <= n3Len
            int n1Len = 1, n2Len = 1 ,n3Len = 1;
            // 结果的最大长度
            int maxN3Len = numberStr.length() / 2;
            int idx = numberStr.length();
            while (n3Len <= maxN3Len){
                int n3s = idx - n3Len;
                int n2s = n3s - n2Len;
                int n1s = n2s - n1Len;
                long n3 = Long.parseLong(numberStr.substring(n3s, idx));
                long n2 = Long.parseLong(numberStr.substring(n2s, n3s));
                long n1 = Long.parseLong(numberStr.substring(n1s, n2s));

                if (n3 == n1 + n2){
                    if (n1Len + n2Len + n3Len == idx){
                        return true;
                    }
                    idx = n3s;
                    n3Len = n2Len;
                    n2Len = n3Len - 1 > 0 ? n3Len - 1 : n3Len;
                    n1Len = 1;
                }else {
                    if (n1Len + n2Len + n3Len == idx){
                        n2s = n3s - n1Len;
                        n1s = n2s - n2Len;
                        n2 = Long.parseLong(numberStr.substring(n2s, n3s));
                        n1 = Long.parseLong(numberStr.substring(n1s, n2s));
                        return n1 + n2 == n3;
                    }
                    if (n1Len < n2Len){
                        n1Len++;
                    }else if (n2Len < n3Len){
                        n2Len++;
                        n1Len = 1;
                    }else {
                        n3Len++;
                        n2Len = n3Len - 1;
                        n1Len = 1;
                        idx = numberStr.length();
                    }
                }
            }
            return false;
        }catch (Exception e){
            throw new RuntimeException(numberStr);
        }
    }

    public static void main(String[] args) {
        System.out.println(isCumulativeNumber("11235813"));     //exp true
        System.out.println(isCumulativeNumber("01212"));        //exp true
        System.out.println(isCumulativeNumber("111"));          //exp false
        System.out.println(isCumulativeNumber("199100199"));    //exp true
        System.out.println(isCumulativeNumber("12012122436"));  //exp true
        // 1+1=2 1+2=3 2+3=5 3+5=8 5+8=13 8+13=21 13+21=34 21+34=55 34+55=89 55+89=144
        // 89+144=233 144+233=377 233+377=610 377+610=987 610+987=1597
        System.out.println(isCumulativeNumber("11235813213455891442333776109871597")); //exp true
    }
}
