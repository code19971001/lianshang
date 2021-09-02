package com.it;

import java.util.Arrays;

/**
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
 * 注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），
 * 请返回[-1,-1]。
 * <p>
 * 链接：https://leetcode-cn.com/problems/sub-sort-lcci
 *
 * @author : code1997
 * @date : 2021/9/2 23:05
 */
public class meeting_16_SubSort {

    /**
     * 双指针：
     * 从左往右遍历,右指针的位置是最后一个逆序对.
     * 从右往左，左指针的位置是最后一个逆序对.
     */
    public int[] subSort(int[] array) {
        int li = -1;
        int ri = -1;
        //防止数组越界
        if (array == null || array.length == 0) {
            return new int[]{li, ri};
        }
        int temp = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < temp) {
                ri = i;
            }
            temp = Math.max(temp, array[i]);
        }
        temp = array[array.length - 1];
        for (int i = array.length - 2; i >= 0; i--) {
            if (array[i] > temp) {
                li = i;
            }
            temp = Math.min(temp, array[i]);
        }
        return new int[]{li, ri};
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new meeting_16_SubSort().subSort(new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19})));
    }
}
