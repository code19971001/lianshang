package com.it;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列解法2：利用原来的nums数组，可以省略两个中间过程的数组
 *
 * @author : code1997
 * @date : 2021/8/24 23:36
 */
public class _46_Permutations3 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        dfs(0, list, nums);
        return list;
    }

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
            swap(nums, idx, i);
            dfs(idx + 1, list, nums);
            //恢复现场
            swap(nums, idx, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
