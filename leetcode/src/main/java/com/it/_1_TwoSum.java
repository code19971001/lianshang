package com.it;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : code1997
 * @date : 2021/8/19 20:33
 */
public class _1_TwoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
    }

    /**
     * hash表：时间复杂度O(nlogn)
     * 本解法的时间复杂度为O(n),需要遍历一遍
     */
    public static int[] twoSum(int[] nums, int target) {
        //遍历一边将nums加入到map中去
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])){
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[2];
    }

}
