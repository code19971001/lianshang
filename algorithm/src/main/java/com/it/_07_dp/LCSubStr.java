package com.it._07_dp;

/**
 * 最长公共子串: 求两个字符串的最长公共子串。
 *
 * @author : code1997
 * @date : 2021/4/26 20:48
 */
public class LCSubStr {
    public static void main(String[] args) {
        System.out.println(findLength2("abc", "abcd"));
    }

    public static int findLength1(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        if (chars1.length == 0 || chars2.length == 0) {
            return 0;
        }
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        int max = 0;
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max;
    }

    /**
     * 使用一维数组来优化空间
     */
    public static int findLength2(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        if (chars1.length == 0 || chars2.length == 0) {
            return 0;
        }
        char[] rowNums = chars1;
        char[] colNums = chars2;
        if (rowNums.length < colNums.length) {
            rowNums = chars2;
            colNums = chars1;
        }
        int[] dp = new int[colNums.length + 1];
        int max = 0;
        for (int i = 1; i <= rowNums.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colNums.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowNums[i - 1] == colNums[j - 1]) {
                    dp[j] = leftTop + 1;
                    max = Math.max(dp[j], max);
                } else {
                    dp[j] = 0;
                }
            }
        }
        return max;
    }

    /**
     * 使用一维数组来优化空间
     */
    public static int findLength3(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        if (chars1.length == 0 || chars2.length == 0) {
            return 0;
        }
        char[] rowNums = chars1;
        char[] colNums = chars2;
        if (rowNums.length < colNums.length) {
            rowNums = chars2;
            colNums = chars1;
        }
        int[] dp = new int[colNums.length + 1];
        int max = 0;
        for (int i = 1; i <= rowNums.length; i++) {
            for (int j = colNums.length; j >=1; j--) {
                if (rowNums[i - 1] == colNums[j - 1]) {
                    dp[j] = dp[j-1] + 1;
                    max = Math.max(dp[j], max);
                } else {
                    dp[j] = 0;
                }
            }
        }
        return max;
    }



}
