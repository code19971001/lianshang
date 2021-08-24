package com.it;

import java.util.Arrays;

/**
 * 假设把某个股票的价格按照时间的先后顺序存储在数组中，请问买卖该股票一次可以获得的最大利润是多少？
 * <p>
 * 必须先买后卖。
 *
 * @author : code1997
 * @date : 2021/8/17 21:28
 */
public class _121_GoodTimeToBuyOrSell {

    public static void main(String[] args) {
        System.out.println(maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
    }

    /**
     * 使用两个变量分别用于记录历史股票最低价以及最大利润
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minPrice = prices[0];
        int maxValue = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= minPrice) {
                maxValue = Math.max(maxValue, prices[i] - minPrice);
            } else {
                minPrice = prices[i];
            }
        }
        return maxValue;
    }


    /**
     * 使用动态规划的解决：定义一个数组来记录，相邻两天买卖的利润.然后求最大子序列和.
     * 注意：只是一种思路，时间复杂度并不低。O(n)
     */
    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] dp = new int[prices.length];
        dp[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = prices[i] - prices[i - 1];
        }
        System.out.println(Arrays.toString(dp));
        //求最大连续子序列的和
        int initDp = dp[0];
        int maxValue = 0;
        for (int i = 1; i < dp.length; i++) {
            if (initDp <= 0) {
                initDp = dp[i];
            } else {
                initDp += dp[i];
            }
            maxValue = Math.max(initDp, maxValue);
        }
        return maxValue;
    }


}
