package com.it;

/**
 * 移动零：给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 思路：维持一个指针来索引第一个0的位置。
 *
 * @author : code1997
 * @date : 2021/8/28 16:07
 */
public class _293_MoveZeroes {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        //保存第一个0的位置
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            if (index != i) {
                nums[index] = nums[i];
                nums[i] = 0;
            }
            index++;
        }
    }
}
