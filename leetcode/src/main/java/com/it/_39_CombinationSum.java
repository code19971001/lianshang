package com.it;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : code1997
 * @date : 2021/8/26 23:50
 */
public class _39_CombinationSum {
    /**
     * 对于给我们的数组我们应该先对其进行排序.
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return list;
        }
        dfs(list);
        return list;
    }

    /**
     * 进入下一层需要传递的是需要凑够的值，
     * @param list
     */
    private void dfs(List<List<Integer>> list) {

    }
}
