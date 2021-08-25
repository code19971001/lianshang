package com.it.work.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * link:https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author : code1997
 * @date :2021-03-2021/3/10 10:25
 */
public class _20_有效的括号 {

    public boolean isValid1(String s) {
        while (s.contains("{}")
                || s.contains("[]")
                || s.contains("()")) {
            s = s.replace("[]", "");
            s = s.replace("{}", "");
            s = s.replace("()", "");
        }
        return s.isEmpty();
    }

    /**
     * 如果遇到左括号就和入栈
     * 如果是右字符则
     *   如果栈为空，则不匹配。
     *   如果栈不为空，则弹出栈顶元素，与右字符进行匹配。
     *     如果不匹配，则说明括号无效。
     *     如果左右字符匹配，继续扫描下一个字符。
     * 所有字符扫描完毕
     *   如果栈为空，说明括号有效。
     *   如果栈不为空，说明括号无效
     *
     * @param s ：待扫描掉字符串
     * @return ：isValid
     */

    public boolean isValid2(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char left = stack.pop();
                if (left == '(' && c != ')') {
                    return false;
                }
                if (left == '[' && c != ']') {
                    return false;
                }
                if (left == '{' && c != '}') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static HashMap<Character,Character> map = new HashMap<>();
    static {
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }
    /**
     * 使用hashmap来实现
     */
    public  boolean isValid3(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (c !=map.get(stack.pop())) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }



}
