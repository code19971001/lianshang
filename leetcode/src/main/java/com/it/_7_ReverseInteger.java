package com.it;

import java.util.Stack;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31,2^31− 1] ，就返回 0。
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 *
 * @author : code1997
 * @date : 2021/8/31 22:14
 */
public class _7_ReverseInteger {

    /**
     * 垃圾解法：利用栈。
     */
    public int reverse1(int x) {
        StringBuilder builder = new StringBuilder();
        if (x < 0) {
            builder.append("-");
            x = -x;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : String.valueOf(x).toCharArray()) {
            stack.push(c);
        }
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        try {
            return Integer.parseInt(builder.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 需要判断是否存在溢出:利用公式进行反推.
     */
    public int reverse2(int x) {
        int res = 0;
        int last = 0;
        int temp = 0;
        while (x != 0) {
            last = res;
            res = res * 10 + x % 10;
            temp = (res - x % 10) / 10;
            if (last != temp) {
                return 0;
            }
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new _7_ReverseInteger().reverse1(123));
    }
}
