package com.it;

import java.util.*;

/**
 * 三数之和：给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 链接：https://leetcode-cn.com/problems/3sum
 * <p>
 * 解法：
 * 1）暴力发：枚举全部的数据，时间复杂度为O(n^3)。
 * 2）优化：先排序(nlog(n)).然后遍历，定义两个指针,l,r,从两端开始向中间移动。
 *
 * @author : code1997
 * @date : 2021/8/28 16:47
 */
public class _15_ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return list;
        }
        //先排序:时间复杂度nlog(n).
        Arrays.sort(nums);
        int curSum = 0;
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {

                curSum = nums[i] + nums[l] + nums[r];
                if (curSum == 0) {
                    list.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //跳过相同的值
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    //中间逼近
                    l++;
                    r--;
                } else if (curSum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return list;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return list;
        }
        Set<List<Integer>> set = new HashSet<>();
        //先排序:时间复杂度nlog(n).
        Arrays.sort(nums);
        int curSum = 0;
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                curSum = nums[i] + nums[l] + nums[r];
                if (curSum == 0) {
                    set.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                } else if (curSum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        list.addAll(set);
        return list;
    }

}
