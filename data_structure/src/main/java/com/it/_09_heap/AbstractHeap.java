package com.it._09_heap;

import java.util.Comparator;

/**
 * @author : code1997
 * @date : 2021/11/24 23:28
 */
public class AbstractHeap<E> implements IHeap<E>{

    protected int size;

    protected Comparator<E> comparator;

    public AbstractHeap() {
        this(null);
    }

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;

    }

    /**
     * 通过改变比较策略实现最小堆
     */
    protected int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
    }

    @Override
    public void add(E element) {

    }

    @Override
    public E get() {
        return null;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E replace(E element) {
        return null;
    }
}
