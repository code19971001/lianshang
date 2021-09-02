package com.it;

/**
 * 只包含0，1，2的数组进行原地排序.
 * 要求：我们之前学的排序算法是不符合要求的.
 * 空间复杂度：O(1)
 * 时间复杂度：O(n)
 *
 * @author : code1997
 * @date : 2021/9/2 22:27
 */
public class _75_SortColors {

    /**
     * 1:什么都不做.
     * 0:和左边第一个不为0的值交换.
     * 2:和右边最后一个不为2的值进行交换，然后再次判断当前位置.
     */
    public void sortColors(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        //一定要注意i<=r的判断，防止再次被替换掉
        for (int i = 0; i < nums.length && i <= r; i++) {
            if (nums[i] == 0) {
                swap(nums, l++, i);
            }
            if (nums[i] == 2) {
                swap(nums, i, r--);
                //替换之后还需要再次进行判断.防止被跳过.
                i--;
            }
        }
    }

    private void swap(int[] num, int l, int r) {
        int temp = num[l];
        num[l] = num[r];
        num[r] = temp;
    }

}
