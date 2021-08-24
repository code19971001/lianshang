package com.it;

/**
 * @author : code1997
 * @date : 2021/8/23 0:41
 */
public class _1646_GetMaxNum {

    public static void main(String[] args) {
        System.out.println(getMaximumGenerated(7));
    }

    /**
     * 方法一：暴力法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int getMaximumGenerated(int n) {
        if (n < 2) {
            return n;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        int max = nums[1];
        for (int i = 2; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = nums[i >> 1];
            } else {
                nums[i] = nums[i >> 1] + nums[(i >> 1) + 1];
            }
            max = Math.max(nums[i], max);
        }
        return max;
    }
}
