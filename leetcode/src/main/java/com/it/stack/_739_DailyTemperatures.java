package com.it.stack;

import java.util.Stack;

/**
 * 方式1：利用最大栈来寻找左边，或者右边第一个比当前值大的索引.
 * 方式2：还可以使用倒推法来解决(dp)
 *
 * @author : code1997
 * @date : 2021/9/8 22:42
 */
public class _739_DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return null;
        }
        //分别存放左边和右边第一个比该位置元素大的所以.
        int[] res = new int[temperatures.length];
        //存放索引
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                //需要弹出元素
                Integer pop = stack.pop();
                //设置右边第一个比当前值大的元素.
                res[pop] = Math.max(i - pop, 0);
            }
            //如果栈顶不为空，说明栈顶元素是当前元素左边第一个最大的值的索引.,如果为空，这说明左边没有比他大的值，
            stack.push(i);
        }
        return res;
    }

    /**
     * dp:易于理解版本
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return null;
        }
        int[] dp = new int[temperatures.length];
        dp[temperatures.length - 1] = 0;
        for (int i = temperatures.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (true) {
                if (temperatures[i] < temperatures[j]) {
                    dp[i] = j - i;
                    break;
                } else if (dp[j] == 0) {
                    dp[i] = 0;
                    break;
                } else if (temperatures[i] == temperatures[j]) {
                    dp[i] = dp[j] + j - i;
                    break;
                } else {
                    j = j + dp[j];
                }
            }

        }
        return dp;
    }


    /**
     * dp:精简版本.
     */
    public int[] dailyTemperatures3(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return null;
        }
        int[] dp = new int[temperatures.length];
        dp[temperatures.length - 1] = 0;
        for (int i = temperatures.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (true) {
                if (temperatures[i] < temperatures[j]) {
                    dp[i] = j - i;
                    break;
                } else if (dp[j] == 0) {
                    dp[i] = 0;
                    break;
                }
                j = j + dp[j];
            }

        }
        return dp;
    }

    public static void main(String[] args) {
        new _739_DailyTemperatures().dailyTemperatures2(new int[]{34, 80, 80, 34, 34, 80, 80, 80, 80, 34});
    }
}
