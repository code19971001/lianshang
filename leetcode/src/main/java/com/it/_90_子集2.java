package com.it;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : code1997
 * @date : 2021/3/31 23:26
 */
public class _90_子集2 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<Integer>());
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

        }

        return lists;
    }
}
