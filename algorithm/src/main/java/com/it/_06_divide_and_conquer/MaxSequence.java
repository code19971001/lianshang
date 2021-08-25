package com.it._06_divide_and_conquer;

/**
 * @author : code1997
 * @date : 2021/4/23 0:01
 */
public class MaxSequence {
    public static void main(String[] args) {
        int[] nums = {5, 4, -1, 7, 8};
        System.out.println(maxSeq(nums));
        System.out.println(maxSeq2(nums));
    }

    private static int maxSeq2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return maxSeq2(nums, 0, nums.length);
    }

    private static int maxSeq2(int[] nums, int begin, int end) {
        if (end - begin < 2) return nums[begin];
        int mid = (end + begin) >> 1;
        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int leftIndex = mid - 1; leftIndex >= begin; leftIndex--) {
            leftSum += nums[leftIndex];
            leftMax = Math.max(leftSum, leftMax);
        }
        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int rightIndex = mid; rightIndex < end; rightIndex++) {
            rightSum += nums[rightIndex];
            rightMax = Math.max(rightMax, rightSum);
        }
        return Math.max(Math.max(maxSeq2(nums, begin, mid), maxSeq2(nums, mid, end)), leftMax + rightMax);
    }

    private static int maxSeq(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (max < sum) {
                    max = sum;
                }
            }
        }
        return max;
    }
}
