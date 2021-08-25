package com.it._03_linkedlist;

public abstract class AbstractList<E> implements List<E> {

    protected int size;

    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index:" + index + ",size:" + size);
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            //抛出数组下标越界异常
            outOfBounds(index);
        }
    }

    protected void rangeCheckAdd(int index) {
        if (index < 0 || index > size) {
            //抛出数组下标越界异常
            outOfBounds(index);
        }
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
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }
}
