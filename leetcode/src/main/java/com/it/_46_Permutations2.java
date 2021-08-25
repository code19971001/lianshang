package com.it;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 全排列解法：使用list来代替树
 *
 * @author : code1997
 * @date : 2021/8/24 23:36
 */
public class _46_Permutations2 {


    /**
     * 标记数组是否被使用过
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        boolean[] disable = new boolean[nums.length];
        List<Integer> result = new ArrayList<>();
        dfs(0, nums, result, list, disable);
        return list;
    }

    /**
     * 将result使用list来存储
     */
    private void dfs(int idx, int[] nums, List<Integer> result, List<List<Integer>> list, boolean[] disable) {
        if (idx == nums.length) {
            //表明找到了一个集合
            list.add(new ArrayList<>(result));
            return;
        }
        //枚举每一层的选择，每当我们回退的时候我们需要恢复现场
        for (int num : nums) {
            if (!result.contains(num)) {
                result.add(num);
                dfs(idx + 1, nums, result, list, disable);
                result.remove(result.size() - 1);
            }
        }
    }
}
