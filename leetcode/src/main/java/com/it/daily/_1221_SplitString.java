package com.it.daily;

import java.util.Stack;

/**
 * @author : code1997
 * @date : 2021/9/7 0:14
 */
public class _1221_SplitString {

    /**
     * 使用栈来解决
     * 时间复杂度: O(1)
     * 空间复杂度: O(n)
     */
    public int balancedStringSplit(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int res = 0;
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        stack.push(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            //判断当前元素与栈顶是否是平衡字符串
            if ((!stack.isEmpty()) && isBalanceStr(stack.peek(), chars[i])) {
                //弹出栈顶元素
                stack.pop();
                if (stack.isEmpty()) {
                    res++;
                }
            } else {
                stack.push(chars[i]);
            }
        }
        return res;
    }

    /**
     * 使用一个变量代替栈
     */
    public int balancedStringSplit2(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int res = 0;
        //统计L的数量
        int cnt = 0;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            //判断当前元素与栈顶是否是平衡字符串
            if (aChar == 'L') {
                cnt++;
            } else {
                cnt--;
            }
            if (cnt == 0) {
                res++;
            }
        }
        return res;
    }

    /**
     * 平衡字符串：c1!=null,c2!=null,c1!=c2
     */
    private boolean isBalanceStr(Character c1, Character c2) {
        return c1 != null && c2 != null && (!c1.equals(c2));
    }

    public static void main(String[] args) {
        Stack<Character> characters = new Stack<>();
        System.out.println(characters.peek());
    }
}
