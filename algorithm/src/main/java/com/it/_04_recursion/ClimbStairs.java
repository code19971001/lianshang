package com.it._04_recursion;

/**
 * 上楼梯：楼梯存在n阶台阶，上楼可以一次上1阶，也可以一步上2阶，走完n阶台阶共存在多少种不同的走法？
 * f(n)=f(n-1)+f(n-2),实际依旧是斐波那契数列。
 *
 * @author : code1997
 * @date : 2021/4/20 22:39
 */
public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs1(4));
        System.out.println(climbStairs2(4));
    }

    private static int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    private static int climbStairs2(int n) {
        if (n <= 2) {
            return 1;
        }
        int[] ints = new int[]{1, 2, 0};
        for (int i = 3; i <= n; i++) {
            ints[2] = ints[0] + ints[1];
            ints[0] = ints[1];
            ints[1] = ints[2];
        }
        return ints[2];
    }


}
