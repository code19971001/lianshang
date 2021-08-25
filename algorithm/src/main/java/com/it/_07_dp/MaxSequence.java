package com.it._07_dp;

/**
 * 最大连续子序列的和 ：使用动态规划来解决。
 *
 * @author : code1997
 * @date : 2021/4/24 15:52
 */
public class MaxSequence {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSeq = maxSeq2(nums);
        System.out.println("最大连续子序列和为：" + maxSeq);
    }

    /**
     * 时间复杂度为：O(n)
     * 空间复杂度：O(n)
     */
    public static int maxSeq1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    /**
     * 使用一个整数dp来代表上一个dp。
     * 时间复杂度为：O(n)
     * 空间复杂度：O(1)
     */
    public static int maxSeq2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int dp = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp <= 0) {
                dp = nums[i];
            } else {
                dp += nums[i];
            }
            max = Math.max(max, dp);
        }
        return max;
    }
}
