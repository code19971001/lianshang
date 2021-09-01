package com.it;

/**
 * 盛最多水的容器.
 *
 * @author : code1997
 * @date : 2021/9/1 21:40
 */
public class _11_BigestContainer {

    /**
     * 使用双指针
     */
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int maxArea = Math.min(height[0], height[height.length - 1]) * (height.length - 1);
        if (height.length == 2) {
            return maxArea;
        }
        int l = 0;
        int r = height.length - 1;
        int cur = 0;
        while (l < r) {
            if (height[l] > height[r]) {
                cur = r;
                while (l < r && height[cur] >= height[r]) {
                    r--;
                }
            } else {
                cur = l;
                while (l < r && height[cur] >= height[l]) {
                    l++;
                }
            }
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
        }
        return maxArea;
    }

    public int maxArea2(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int l = 0;
        int r = height.length - 1;
        int minH = 0;
        int maxArea = 0;
        while (l < r) {
            minH = Math.min(height[l], height[r]);
            maxArea = Math.max(maxArea, minH * (r - l));
            while (l < r && height[l] <= minH) {
                l++;
            }
            while (l < r && height[r] <= minH) {
                r--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(new _11_BigestContainer().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(new _11_BigestContainer().maxArea2(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
