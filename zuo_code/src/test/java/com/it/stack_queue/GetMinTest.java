package com.it.stack_queue;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : code1997
 * @date : 2021/11/1 21:23
 */
public class GetMinTest {

    @Test
    public void pop() {
        //空栈判断
        Assert.assertThrows(RuntimeException.class, () -> {
            GetMin getMin = new GetMin();
            getMin.pop();
        });
        //判断pop的值
        GetMin stack = new GetMin();
        stack.push(999);
        stack.push(99);
        stack.push(9);
        Assert.assertEquals(9, stack.getMin());
        stack.pop();
        Assert.assertEquals(99, stack.getMin());
        //空栈判断
        Assert.assertThrows(RuntimeException.class, () -> {
            stack.pop();
            stack.pop();
            stack.getMin();
        });
    }
}