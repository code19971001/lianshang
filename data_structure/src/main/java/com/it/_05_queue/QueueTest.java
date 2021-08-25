package com.it._05_queue;

import org.junit.Test;

/**
 * @author : code1997
 * @date :2021-03-2021/3/10 12:14
 */
public class QueueTest {
    @Test
    public void testMyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        System.out.println(queue.deQueue());
        System.out.println(queue.front());
        System.out.println(queue.size());
        queue.clear();
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
    }

    @Test
    public void testMyDequeue() {
        System.out.println("--------dequeue----------");
        MyDequeue<Integer> dequeue = new MyDequeue<>();
        dequeue.enQueueFront(11);
        dequeue.enQueueFront(22);
        dequeue.enQueueRear(33);
        dequeue.enQueueRear(44);
        //尾 44 33 11 22  头
        System.out.println(dequeue.deQueueFront());
        System.out.println(dequeue.deQueueFront());
        System.out.println(dequeue.deQueueFront());
        System.out.println(dequeue.deQueueFront());
    }

    @Test
    public void testMyCircleQueue() {
        MyCircleQueue<Integer> circleQueue = new MyCircleQueue<>();
        for (int i = 0; i < 10; i++) {
            circleQueue.enQueue(i);
        }
        for (int i = 0; i < 5; i++) {
            circleQueue.deQueue();
        }
        for (int i = 15; i <= 20; i++) {
            circleQueue.enQueue(i);
        }
        System.out.println(circleQueue);
        while (!circleQueue.isEmpty()) {
            System.out.println(circleQueue.deQueue());
        }
    }

    @Test
    public void testMyCircleDequeue() {
        MyCircleDequeue<Integer> circleDequeue = new MyCircleDequeue<>();
        for (int i = 0; i < 10; i++) {
            circleDequeue.enQueueFront(i + 1);
            circleDequeue.enQueueRear(i + 100);
        }
        //MyCircleDequeue{capacity=22, front=20, size=20,
        // elements=[8, 7, 6, 5, 4, 3, 2, 1, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, null, null, 10, 9]}
        System.out.println(circleDequeue);
        for (int i = 0; i < 3; i++) {
            circleDequeue.deQueueFront();
            circleDequeue.deQueueRear();
        }
        //MyCircleDequeue{capacity=22, front=1, size=14,
        // elements=[null, 7, 6, 5, 4, 3, 2, 1, 100, 101, 102, 103, 104, 105, 106, null, null, null, null, null, null, null]}
        System.out.println(circleDequeue);
        circleDequeue.enQueueFront(11);
        circleDequeue.enQueueFront(12);
        //MyCircleDequeue{capacity=22, front=21, size=16,
        // elements=[11, 7, 6, 5, 4, 3, 2, 1, 100, 101, 102, 103, 104, 105, 106, null, null, null, null, null, null, 12]}
        System.out.println(circleDequeue);
        while (!circleDequeue.isEmpty()) {
            System.out.println(circleDequeue.deQueueFront());
        }

    }

    @Test
    public void testOptimization() {
        int n = 13;
        int m = 10;
        int index;
        //如果：front+size=13;length=10;那么取模为3，实际为front+size-length
        //如果：front+size=7;length=10;那么取模为7,实际为本身front+size-0.
        //限制：front+size<length*2.对于本案例是合适的

        //形式1:
        if (n >= m) {
            index = n - m;
        } else {
            index = n - 0;
        }

        //形式2：
        index=n-(n>=m?m:0);

    }


}
