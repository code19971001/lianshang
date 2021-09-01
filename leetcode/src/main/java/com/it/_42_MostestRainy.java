package com.it;

/**
 * 接雨水：给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author : code1997
 * @date : 2021/9/1 22:14
 */
public class _42_MostestRainy {


    /**
     * 创建两个数组分别存储每个索引处的左边，右边最大值.
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int lastIndex = height.length - 2;
        int[] leftMaxes = new int[height.length];
        //动态规划dp[i]=Math.max(dp[i-1],height[i])
        for (int i = 1; i <= lastIndex; i++) {
            leftMaxes[i] = Math.max(leftMaxes[i - 1], height[i - 1]);
        }
        int[] rightMaxes = new int[height.length];
        for (int i = lastIndex; i >= 1; i--) {
            rightMaxes[i] = Math.max(rightMaxes[i + 1], height[i + 1]);
        }
        //初始化leftMaxes和rightMaxes.
        int water = 0;
        //遍历每一根柱子上可以存放多少水
        for (int i = 1; i <= lastIndex; i++) {
            int min = Math.min(leftMaxes[i], rightMaxes[i]);
            if (height[i] < min) {
                water += (min - height[i]);
            }
        }
        return water;
    }

    /**
     * 使用一个数组来表示右边的最小值
     * 空间复杂度：O(n)
     * 时间复杂度：O(n)
     */
    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int lastIndex = height.length - 2;
        int[] rightMaxes = new int[height.length];
        for (int i = lastIndex; i >= 1; i--) {
            rightMaxes[i] = Math.max(rightMaxes[i + 1], height[i + 1]);
        }
        //初始化leftMaxes和rightMaxes.
        int water = 0;
        int leftMax = 0;
        //遍历每一根柱子上可以存放多少水
        for (int i = 1; i <= lastIndex; i++) {
            leftMax = Math.max(leftMax, height[i - 1]);
            int min = Math.min(leftMax, rightMaxes[i]);
            if (height[i] < min) {
                water += (min - height[i]);
            }
        }
        return water;
    }

    /**
     * 比较难以想象.实际上我们不需要求得右边的最大值，只需要知道右边存在柱子的高度大于左边的最大的值的即可.
     * <p>
     * 空间复杂度：O(1)
     * 时间复杂度：O(n)
     */
    public int trap3(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int l = 0, r = height.length - 1, lowerMax = 0, water = 0;
        while (l < r) {
            //height[l]和height[r]中比较小的那个.
            int lower = height[height[l] <= height[r] ? l++ : r--];
            //找出lowerMax中最大的那个
            lowerMax = Math.max(lowerMax, lower);
            water += lowerMax - lower;
        }
        return water;
    }
}
