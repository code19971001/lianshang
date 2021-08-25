package com.it._07_dp;

/**
 * 最长递增子序列
 *
 * @author : code1997
 * @date : 2021/4/24 16:31
 */
public class LIS {
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        int numSize = nums.length;
        if (nums == null || numSize == 0) {
            return 0;
        }
        //dp最后一个坐标保存最大的连续递增字串的长度。
        int[] dp = new int[numSize + 1];
        for (int i = 0; i < numSize; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //当前的value大于前面某个坐标的value的时候，dp=dp[j]+1;
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            dp[numSize] = Math.max(dp[numSize], dp[i]);
        }
        return dp[numSize];
    }

    public static int lengthOfLIS2(int[] nums) {
        int numSize = nums.length;
        if (numSize == 0) {
            return 0;
        }
        //dp最后一个坐标保存最大的连续递增字串的长度。
        int[] top = new int[numSize];
        int length = 0;
        for (int i = 0; i < numSize; i++) {
            int j = 0;
            while (j < length) {
                if (top[j] >= nums[i]) {
                    top[j] = nums[i];
                    break;
                }
                j++;
            }
            if (j == length) top[length++] = nums[i];
        }
        return length;
    }

    public static int lengthOfLIS3(int[] nums) {
        int numSize = nums.length;
        if (nums == null || numSize == 0) {
            return 0;
        }
        //dp最后一个坐标保存最大的连续递增字串的长度。
        int[] top = new int[numSize];
        int length = 0;
        for (int i = 0; i < numSize; i++) {
            //使用二分查找找到需要放置的位置[left,right]
            int left = 0, right = length;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (nums[i] <= top[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            //新建一个牌堆
            top[left] = nums[i];
            if (left == length) {
                length++;
            }
        }
        return length;
    }
}
