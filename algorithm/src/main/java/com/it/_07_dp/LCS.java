package com.it._07_dp;

/**
 * 最长公共子序列
 *
 * @author : code1997
 * @date : 2021/4/24 22:08
 */
public class LCS {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abc", "abcd"));
    }

    /**
     * 空间复杂度：O(n*m)
     * 空间复杂度：O(n*m)
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        //dp[m][n]存储最长公共子序列的长度
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }
        return dp[m][n];
    }

    //空间复杂度：O(k),两序列长度的最小值
    //时间复杂度：O(2^n)
    public static int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        return longestCommonSubsequence2(text1, text1.length(), text2, text2.length());
    }

    public static int longestCommonSubsequence2(String text1, int i, String text2, int j) {
        if (i == 0 || j == 0) return 0;
        if (text1.charAt(i - 1) == text2.charAt(j - 1))
            return longestCommonSubsequence2(text1, i - 1, text2, j - 1) + 1;
        return Math.max(longestCommonSubsequence2(text1, i - 1, text2, j), longestCommonSubsequence2(text1, i, text2, j - 1));
    }

    /**
     * 使用滚动数组(两行)来减小空间的消耗
     * 空间复杂度：O(2n)
     * 时间复杂度：O(2*n)->O(2n)
     */
    public static int longestCommonSubsequence3(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        //dp[1][1]存储最长公共子序列的长度
        int[][] dp = new int[2][n + 1];
        for (int i = 1; i <= m; i++) {
            //等下与i%2
            int row = i & 1;
            int preRow = (i - 1) & 1;
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[row][j] = dp[preRow][j - 1] + 1;
                } else {
                    dp[row][j] = Math.max(dp[preRow][j], dp[row][j - 1]);
                }

            }
        }
        return dp[m & 1][n];
    }

    /**
     * 使用滚动数组(一行)来减小空间的消耗
     * 空间复杂度：O(n)
     * 时间复杂度：O(2*n)->O(2n)
     */
    public static int longestCommonSubsequence4(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        //dp[1][1]存储最长公共子序列的长度
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int cur = 0;
            for (int j = 1; j <= n; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[n];
    }


    /**
     * 我们可以选取长度更小的字符序列作为列。
     * 空间复杂度：O(n*m)
     * 时间复杂度：O(2*n)->O(2n)
     */
    public static int longestCommonSubsequence5(String text1, String text2) {
        String rowNums, colNums;
        if (text1.length() > text2.length()) {
            rowNums = text1;
            colNums = text2;
        } else {
            colNums = text1;
            rowNums = text2;
        }
        //dp[1][1]存储最长公共子序列的长度
        int[] dp = new int[colNums.length() + 1];
        for (int i = 1; i <= rowNums.length(); i++) {
            int cur = 0;
            for (int j = 1; j <= colNums.length(); j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowNums.charAt(i - 1) == colNums.charAt(j - 1)) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colNums.length()];
    }

    /**
     * 我们可以选取长度更小的字符序列作为列。
     * 空间复杂度：O(n*m)
     * 时间复杂度：O(2*n)->O(2n)
     */
    public static int longestCommonSubsequence6(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        if (chars1.length == 0 || chars2.length == 0) {
            return 0;
        }
        char[] rowChars = chars1, colChars = chars2;
        if (chars1.length < chars2.length) {
            colChars = chars1;
            rowChars = chars2;
        }
        //dp[1][1]存储最长公共子序列的长度
        int[] dp = new int[colChars.length + 1];
        for (int i = 1; i <= rowChars.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colChars.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowChars[i - 1] == colChars[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colChars.length];
    }


}
