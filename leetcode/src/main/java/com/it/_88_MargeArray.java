package com.it;

/**
 * @author : code1997
 * @date : 2021/9/2 21:43
 */
public class _88_MargeArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = nums1.length - 1; i >= 0; i--) {
            //注意对于m和和n非0判断
            if (n == 0) {
                break;
            } else if (m == 0 || nums1[m - 1] <= nums2[n - 1]) {
                nums1[i] = nums2[--n];
            } else {
                nums1[i] = nums1[--m];
            }
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1;
        int i2 = n - 1;
        int cur = nums1.length - 1;
        while (i2 >= 0) {
            if (i1 >= 0 && nums2[i2] < nums1[i1]) {
                nums1[cur--] = nums1[i1--];
            } else {
                nums1[cur--] = nums2[i2--];
            }
        }
    }

    public static void main(String[] args) {
        //new _88_MargeArray().merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
        new _88_MargeArray().merge(new int[]{2, 0}, 1, new int[]{1}, 1);
    }
}
