package com.it;

/**
 * @author : code1997
 * @date : 2021/9/6 23:00
 */
public class _704_BinarySearch {

    public int search(int[] nums, int target) {
        int res = -1;
        int li = 0;
        int ri = nums.length - 1;
        while (li <= ri) {
            int mid = (ri + li - 1) >> 1;
            if (nums[mid] == target) {
                res = mid;
                break;
            } else if (nums[mid] < target) {

                li = mid + 1;
            } else {
                ri = mid;
            }
        }
        return res;
    }
}
