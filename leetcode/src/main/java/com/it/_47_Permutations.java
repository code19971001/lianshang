package com.it;

import java.util.*;

/**
 * 全排列2：给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * @author : code1997
 * @date : 2021/8/24 23:36
 */
public class _47_Permutations {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        dfs(0, list, nums);
        return list;
    }

    /**
     * 解1：在添加的时候增加判断，如果已经存在就不添加
     */
    private void dfs(int idx, List<List<Integer>> list, int[] nums) {
        if (idx == nums.length) {
            //保存组合
            List<Integer> tempList = new ArrayList<>();
            Arrays.stream(nums).forEach(tempList::add);
            list.add(tempList);
            return;
        }
        //枚举每一层的选择，每当我们回退的时候我们需要恢复现场
        for (int i = idx; i < nums.length; i++) {
            if (!isRepeat(nums, idx, i)) {
                swap(nums, idx, i);
                dfs(idx + 1, list, nums);
                //恢复现场
                swap(nums, idx, i);
            }
        }
    }

    /**
     * 保证每个数字在index位置只会出现一次。
     */
    private boolean isRepeat(int[] nums, int idx, int i) {
        for (int j = idx; j < i; j++) {
            if (nums[j] == nums[i]) {
                return true;
            }
        }
        return false;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        List<List<Integer>> permute = new _47_Permutations().permuteUnique(new int[]{1, 1, 2});
        permute.forEach(System.out::println);
    }

}
