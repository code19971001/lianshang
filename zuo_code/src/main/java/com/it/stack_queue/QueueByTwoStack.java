package com.it.stack_queue;

import java.util.Stack;

/**
 * 使用两个栈来实现队列
 * 思路：栈是先入后出，队列是先入先出。我们创建两个栈，假定分别为pop栈和push栈。
 * 注意：
 * 1.pop栈必须为空，push栈往pop栈压入数据。
 * 2.一旦push栈需要往pop栈压入数据，则必须压入push栈中的全部数据。
 *
 * @author : code1997
 * @date : 2021/11/1 21:44
 */
public class QueueByTwoStack {

    private Stack<Integer> popStack;
    private Stack<Integer> pushStack;

    public QueueByTwoStack() {
        popStack = new Stack<>();
        pushStack = new Stack<>();
    }

    public void push(Integer e) {
        pushStack.push(e);
    }

    public int peek() {
        pushDataToPopStack();
        return popStack.peek();
    }

    public int pop() {
        //如果pop栈为空，则将push栈中的数据pop到pop栈中.
        pushDataToPopStack();
        //popStack肯定不为空
        return popStack.pop();
    }

    private void pushDataToPopStack() {
        checkEmpty();
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    private void checkEmpty() {
        if (popStack.isEmpty() && pushStack.isEmpty()) {
            throw new IllegalArgumentException("当前栈为空，请仔细确认操作！");
        }
    }
}
