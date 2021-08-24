package com.it;

/**
 * 礼物的最大价值
 * 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/
 *
 * @author : code1997
 * @date : 2021/8/17 1:30
 */
public class _offer_41_LastValueOfGift {
    public static void main(String[] args) {

    }

    public static int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        //初始化dp
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    /**
     * 优化1：使用单行数组来代替dp的二维数组
     */
    public static int maxValue2(int[][] grid) {
        int[] dp = new int[grid[0].length];
        //初始化dp
        dp[0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            //初始化每行的第一个值
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < grid[i].length; j++) {
                dp[j] = Math.max(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[grid[0].length - 1];
    }

}
