package com.it.stack_queue;

import java.util.Stack;

/**
 * 使用栈实现一个特殊的栈，要求在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 * 思路1：使用两个栈来解决，一个作为数据栈，一个作为最小栈。
 * 数据栈：正常的存取数据。
 * 最小栈：栈顶元素为当前数据栈中的最小元素。
 *
 * @author : code1997
 * @date : 2021/11/1 21:09
 */
public class GetMin {

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public GetMin() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public int pop() {
        isEmpty();
        Integer oldVal = dataStack.pop();
        if (oldVal == minStack.peek()) {
            minStack.pop();
        }
        return oldVal;
    }

    /**
     * 往元素中添加元素
     * 条件：
     */
    public void push(Integer e) {
        dataStack.push(e);
        if (minStack.isEmpty() || minStack.peek() >= e) {
            minStack.push(e);
        }
    }

    private void isEmpty() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("当前栈中没有数据!");
        }
    }

    public int getMin() {
        isEmpty();
        return minStack.peek();
    }


}
