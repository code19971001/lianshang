package com.it;

/**
 * 一维数组的动态和
 *
 * @author : code1997
 * @date : 2021/8/28 16:28
 */
public class _1480_RunSumOfArray {

    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        return nums;
    }
}
