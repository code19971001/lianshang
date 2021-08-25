package com.it._07_dp;

/**
 * 0-1背包问题
 *
 * @author : code1997
 * @date : 2021/4/26 21:51
 */
public class _0_1_package {

    public static void main(String[] args) {

        int[] values = {6, 3, 5, 4, 6};
        int[] weights = {2, 2, 6, 5, 4};
        int capacity = 10;
        System.out.println(articlePackage3(values, weights, capacity));
        System.out.println(perfectArticlePackage(values, weights, capacity));
    }

    private static int articlePackage(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (weights.length != values.length || capacity <= 0) return 0;
        int[][] dp = new int[values.length + 1][capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i - 1]) {
                    //不足以选择
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //选择和不选择i中哪个价值比较大，则选择。
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                }
            }
        }
        return dp[values.length][capacity];
    }

    private static int articlePackage2(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (weights.length != values.length || capacity <= 0) return 0;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= 1; j--) {
                if (j >= weights[i - 1]) {
                    //选择和不选择i中哪个价值比较大，则选择。
                    dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
                }
            }
        }
        return dp[capacity];
    }

    private static int articlePackage3(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (weights.length != values.length || capacity <= 0) return 0;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            //如果最大承重等于物品重量的时候，也是最后拥有选择权力的时候。
            for (int j = capacity; j >= weights[i - 1]; j--) {
                if (j >= weights[i - 1]) {
                    //选择和不选择i中哪个价值比较大，则选择。
                    dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
                }
            }
        }
        return dp[capacity];
    }


    /**
     * 要求总重量恰好等w。
     * 如果返回-1，表示无法凑够重量
     */
    private static int perfectArticlePackage(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (weights.length != values.length || capacity <= 0) return 0;
        int[] dp = new int[capacity + 1];
        for (int j = 1; j <= capacity; j++) {
            dp[j] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= values.length; i++) {
            //如果最大承重等于物品重量的时候，也是最后拥有选择权力的时候。
            for (int j = capacity; j >= weights[i - 1]; j--) {
                if (j >= weights[i - 1]) {
                    //选择和不选择i中哪个价值比较大，则选择。
                    dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
                }
            }
        }
        return dp[capacity] < 0 ? -1 : dp[capacity];
    }

}
