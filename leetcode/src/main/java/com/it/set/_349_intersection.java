package com.it.set;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author : code1997
 * @date : 2021/11/17 22:37
 */
public class _349_intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[1];
        }
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        Arrays.stream(nums1).forEach(set1::add);
        Arrays.stream(nums2).forEach(value -> {
            if (result.contains(value)) {
                return;
            }
            if (!set1.add(value)) {
                result.add(value);
            }
        });
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
}
