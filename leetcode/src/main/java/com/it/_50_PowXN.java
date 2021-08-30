package com.it;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）
 * <p>
 * 快速幂(分治)的思想:
 * eg: 3^20 = 3^10 * 3^10  3^21 = 3^10 * 3^10 * 3^10
 * <p>
 * <p>
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

    /**
     * 快速幂扩展：求 x^y%z
     * 注意：如果x,y比较大的时候就可能会出现溢出的情况.就算我们res改为long也可能会存在溢出的问题.
     * 公式：(a*b)%p == ((a%p)*(b%p))%p
     */
    public int myMod1(int x, int y, int z) {
        if (x < 0 || y < 0 || z == 0) {
            return 0;
        }
        //为了更加的简洁，可以提前%z.
        int res = 1 % z;
        x %= z;
        while (y > 0) {
            if ((y & 1) == 1) {
                //res *= x;
                res = (res * x) % z;
            }
            //x *= x;
            x = (x * x) % z;
            //舍弃最后一个二进制位
            y >>= 1;
        }
        return res % z;
    }

    /**
     * 快速幂扩展：求 x^y%z , 使用递归的方式
     * 注意：如果x,y比较大的时候就可能会出现溢出的情况.就算我们res改为long也可能会存在溢出的问题.
     * 公式：(a*b)%p == ((a%p)*(b%p))%p
     */
    public int myMod2(int x, int y, int z) {
        if (y == 0) {
            //终止条件
            return 1 % z;
        }
        int half = myMod2(x, y >> 1, z);
        half *= half;
        if ((y & 1) == 0) {
            //偶数
            return half % z;
        } else {
            //基数
            return (half * (x % z)) % z;
        }
    }

    public static void main(String[] args) {
        System.out.println(new _50_PowXN().myMod2(123, 456, 789));
    }

}
