package com.it._10_queue;

import com.it._09_heap.BinaryHeap;

import java.util.Comparator;

/**
 * 优先队列的实现:我们可以使用最大堆来实现，优先级高的在堆顶。
 *
 * JDK官方的优先队列使用的也是二叉堆实现，只不过是最小堆.
 *
 * @author : code1997
 * @date : 2021/11/29 20:10
 */
public class MaxPriorityQueue<E> {


    private BinaryHeap<E> priorityQueue;

    public MaxPriorityQueue() {
        this(null);
    }

    public MaxPriorityQueue(Comparator<E> comparator) {
        priorityQueue = new BinaryHeap<>(comparator);
    }

    public int size() {
        return priorityQueue.size();
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    public void enQueue(E element) {
        priorityQueue.add(element);
    }

    public E deQueue() {
        return priorityQueue.remove();
    }

    /**
     * 堆区队头元素，实际上就对堆顶元素.
     */
    public E front() {

        return priorityQueue.get();
    }

    public void clear() {
        priorityQueue.clear();
    }

}
