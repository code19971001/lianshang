package com.it;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列
 *
 * @author : code1997
 * @date : 2021/8/24 23:36
 */
public class _46_Permutations {

    /**
     * 标记数组是否被使用过
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        boolean[] disable = new boolean[nums.length];
        int[] result = new int[nums.length];
        dfs(0, nums, result, list, disable);
        return list;
    }

    private void dfs(int idx, int[] nums, int[] result, List<List<Integer>> list, boolean[] disable) {
        if (idx == nums.length) {
            List<Integer> tempList = new ArrayList<>();
            Arrays.stream(result).forEach(tempList::add);
            list.add(tempList);
            return;
        }
        //枚举每一层的选择
        for (int i = 0; i < nums.length; i++) {
            if (!disable[i]) {
                result[idx] = nums[i];
                disable[i] = true;
                dfs(idx + 1, nums, result, list,disable);
                disable[i] = false;
            }
        }
    }


}
