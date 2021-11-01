package com.it.stack_queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : code1997
 * @date : 2021/11/1 21:58
 */
public class QueueByTwoStackTest {

    QueueByTwoStack queue = new QueueByTwoStack();

    @Before
    public void init() {
        queue.push(999);
        queue.push(99);
        queue.push(9);
    }

    @Test
    public void testAll() {
        Assert.assertEquals(999, queue.peek());
        Assert.assertEquals(999, queue.pop());
        Assert.assertEquals(99, queue.peek());
        Assert.assertEquals(99, queue.pop());
        Assert.assertEquals(9, queue.peek());
        Assert.assertEquals(9, queue.pop());
        Assert.assertThrows(RuntimeException.class, () -> queue.peek());
        Assert.assertThrows(RuntimeException.class, () -> queue.pop());
    }

}