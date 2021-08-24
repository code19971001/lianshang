package com.it;

/**
 * 编辑距离
 * href：https://leetcode-cn.com/problems/edit-distance/
 *
 * @author : code1997
 * @date : 2021/8/17 22:42
 */
public class _72_EditDistance {

    public static void main(String[] args) {
        System.out.println(minDistance("arise", "mice"));
    }

    /**
     * dp[i][j]代表从s1[0,i)转化为s2[0,j)的最少转换步骤。
     * todo:将二维数组再转化为一维数组来进行解决
     */
    public static int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        char[] shortChars;
        char[] longChars;
        shortChars = word1.toCharArray();
        longChars = word2.toCharArray();
        int[][] dp = new int[shortChars.length + 1][longChars.length + 1];
        //w1的空字串转化为w2的空串的最小操作数为0
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i;
            for (int j = 1; j < dp[i].length; j++) {
                if (shortChars[i - 1] == longChars[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    dp[i][j]++;
                }
            }
        }
        return dp[shortChars.length][longChars.length];
    }
}
