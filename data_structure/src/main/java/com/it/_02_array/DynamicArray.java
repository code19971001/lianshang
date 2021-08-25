package com.it._02_array;

/**
 * 动态数组本质还是一个数组,既然是数组实际上在声明的时候就需要声明好数组的容量,动态数组指的是当数组的容量不够的时候可以进行动态的扩容.
 * 对于数组的操作一定要注意数组越界的问题.
 * 本次实现允许添加null到数组中
 *
 * @author : code1997
 */
public class DynamicArray<E> {
    /**
     * 记录实际元素的数量
     */
    private int size = 0;
    /**
     * 存放元素的数组
     */
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public DynamicArray(int capacity) {
        //如果初始化容量太小会导致频繁的扩容，会降低新增的性能.
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }

    public DynamicArray() {
        //初始化参数
        this(DEFAULT_CAPACITY);
    }

    /**
     * 返回集合中实际元素的个数
     */
    public int size() {
        return size;
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index:" + index + ",size:" + size);
    }

    private void rangeCheckAdd(int index) {
        if (index < 0 || index > size) {
            //抛出数组下标越界异常
            outOfBounds(index);
        }
    }

    /**
     * 向动态数组中添加元素
     */
    public void add(E element) {
        add(size, element);
    }

    /**
     * 可以在这里判断是否允许存储NULL值
     */
    public void add(int index, E element) {
        //边界检验
        rangeCheckAdd(index);
        ensureCapacity(size + 1);
        /*
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }*/
        if (size - index >= 0) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = element;
        size++;
    }

    /**
     * 保证要有capacity的容量足够，如果容量不够就进行扩容，每次扩容为老的容量的1.5倍.
     */
    public void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        //进行数据的拷贝
        if (size >= 0) {
            System.arraycopy(elements, 0, newElements, 0, size);
        }
        //替换原有的数组
        elements = newElements;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取指定下标的元素
     */
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 删除指定位置处的元素
     */
    public E remove(int index) {
        rangeCheck(index);
        E oldElement = elements[index];
        //将数组中的元素进行前移覆盖即可。
        //可以保证下标不会出现越界的情况
        if (size - (index + 1) >= 0) {
            System.arraycopy(elements, index + 1, elements, index + 1 - 1, size - (index + 1));
        }
        //注意此处的是先--再清空。
        elements[--size] = null;
        return oldElement;
    }

    /**
     * 设置指定位置的元素，并返回改位置的就元素
     */
    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 查看元素的索引，如果没找到则返回-1
     */
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 是否包含某元素
     */
    public boolean contains(E element) {
        return indexOf(element) == ELEMENT_NOT_FOUND;
    }

    /**
     * 清除集合
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }


    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            //抛出数组下标越界异常
            outOfBounds(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(elements[i]);
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * 对象死亡之后的回调，会进入到一个finalize队列，但是线程的优先级比较低，不一定会立即执行，在这个方法中也可能会拯救这个对象
     * 一般情况下，我们不建议重写这个方法。
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("element - finalized");
    }

    public E remove(E element) {
        return remove(indexOf(element));
    }
}


