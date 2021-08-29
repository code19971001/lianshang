package com.it;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）
 * <p>
 * 快速幂(分治)的思想:
 * eg: 3^20 = 3^10 * 3^10  3^21 = 3^10 * 3^10 * 3^10
 *
 *
 * 疑问：设计一个算法，求x^y%z,也可以使用这种方式来求解。
 *
 * @author : code1997
 * @date : 2021/8/29 13:27
 */
public class _50_PowXN {


    /**
     * 方法1：分治 + 递归
     * 时间复杂度：T(n) = T(n/2) + O(1) = O(log(n))
     * 空间复杂度：O(log(n))，即为递归的层数，也是栈的深度。
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        //兼容负奇数.
        if (n == -1) {
            return 1 / x;
        }
        //二进制最小位是1的时候，代表是奇数，对于负数/2和右移动一位的区别。
        double half = myPow(x, n >> 1);
        half *= half;
        //x = (n < 0) ? (1/x) : x;
        return (n & 1) == 1 ? half * x : half;
    }

    /**
     * 方法1：分治 + 迭代
     * 时间复杂度：O(log(n)) : n为对n的二进制拆分的时间复杂度.
     * 空间复杂度：O(1)
     */
    public double myPow2(double x, int n) {
        double res = 1.0;
        //注意如果是-2^31次方，那么转为正数可能会溢出，因此可以改为long
        long absN = Math.abs((long) n);
        while (absN > 0) {
            if ((absN & 1) == 1) {
                //如果最后一个二进制位是1，就累乘。
                res *= x;
            }
            x *= x;
            //舍弃最后一个二进制位
            absN >>= 1;
        }
        return n < 0 ? 1 / res : res;
    }
}
