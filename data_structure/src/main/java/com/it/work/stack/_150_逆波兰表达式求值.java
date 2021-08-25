package com.it.work.stack;

import java.util.Stack;

/**
 * link：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 * @author : code1997
 * @date :2021-03-2021/3/10 12:00
 */
public class _150_逆波兰表达式求值 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])){
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.push(num2+num1);
            }else if ("-".equals(tokens[i])){
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.push(num2-num1);
            }else if ("*".equals(tokens[i])){
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.push(num2*num1);
            }else if ("/".equals(tokens[i])){
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.push(num2/num1);
            }else {
                stack.push(Integer.parseInt(tokens[i]));
            }

        }
        return stack.pop();
    }
}
