package com.it;

/**
 * @author : code1997
 * @date : 2021/8/29 12:21
 */
public class _1588_SumOfAllOddSubArray {

    public int sumOddLengthSubarrays(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int length = 1;
        int sum = 0;
        while (length <= arr.length) {
            for (int i = 0; i < arr.length - length + 1; i++) {
                for (int j = 0; j < length; j++) {
                    sum += arr[i + j];
                }
            }
            length += 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new _1588_SumOfAllOddSubArray().sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
    }
}
