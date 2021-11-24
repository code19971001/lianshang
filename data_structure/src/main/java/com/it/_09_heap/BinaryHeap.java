package com.it._09_heap;

import com.it._06_tree.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * 二叉堆：二叉堆的逻辑结构就是一颗完全二叉堆.鉴于完全二叉树的一些特性，二叉堆的底层(物理结构)一般使用数组来实现.
 * <p>
 * 索引i的规律：n为元素数量.
 * 1.如果i=0，他就是根节点
 * 2.如果i>0，他的父节点编号为floor(i-1)/2
 * 3.如果2i+1<=n-1,他的左子节点编号为2i+1，他的右子节点为2i+2
 * 4.如果2i+1>n-1,他无左子节点，也没有右子节点
 *
 * @author : code1997
 * @date : 2021/11/24 22:13
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

    protected static final int DEFAULT_CAPACITY = 10;

    protected E[] elements;

    public BinaryHeap() {
        this(null);
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 1.确定容量
     * 2.将元素添加到最后
     * 3.循环判断新添加的节点是否大于父节点
     * 3.1如果不存在父节点或者小于父节点，退出循环.
     * 3.2如果大于父节点，两者的值进行交换
     */
    @Override
    public void add(E element) {
        elementNotNullyCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }

    private void siftUp(int index) {
        E o = elements[index];
        while (index > 0) {
            int pIndex = (index - 1) >> 1;
            E p = elements[pIndex];
            if (compare(o, p) <= 0) {
                return;
            }
            //交换
            E tmp = elements[index];
            elements[index] = elements[pIndex];
            elements[pIndex] = tmp;
            index = pIndex;
        }
    }


    /**
     * 优化交换操作
     */
    private void siftUp2(int index) {
        E o = elements[index];
        while (index > 0) {
            int pIndex = (index - 1) >> 1;
            E p = elements[pIndex];
            if (compare(o, p) <= 0) {
                break;
            }
            //父节点的值覆盖当前节点
            elements[index] = elements[pIndex];
            index = pIndex;
        }
        //index<=0或者当前index的值小于父节点的值，可以直接覆盖index即可.
        elements[index] = o;
    }

    /**
     * 保证要有capacity的容量足够，如果容量不够就进行扩容，每次扩容为老的容量的1.5倍.
     */
    private void ensureCapacity(int capacity) {
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

    /**
     * 返回最大值
     */
    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    /**
     * 删除堆顶元素，不能按照普通数组的方式来删除，
     * 1.我们需要将最后的值覆盖掉根节点，然后删除最后的值。
     * 2.我们比较根节点和左右子节点的值：循环操作
     * 2.1如果大于子节点值，那么无需要操作.
     * 2.2如果小于子节点值，则选出子节点中比较大的值进行交换
     */
    @Override
    public E remove() {
        emptyCheck();
        int lastIndex = --size;
        E maxValue = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);
        return maxValue;
    }


    /**
     * 优化交换操作
     */
    private void siftDown(int index) {
        E o = elements[index];
        //必须保证index位置节点是非叶子节点，要求index<非叶子节点的个数
        while (index < (size >> 1)) {
            //获取左右节点中比较大的那一个
            int lIndex = (index << 1) + 1;
            int rIndex = lIndex + 1;
            E maxChild = elements[lIndex];
            if (rIndex < size && compare(elements[rIndex], maxChild) > 0) {
                maxChild = elements[lIndex = rIndex];
            }
            if (compare(o, maxChild) >= 0) {
                break;
            }
            //覆盖
            elements[index] = elements[lIndex];
            index = lIndex;
        }
        //index<=0或者当前index的值小于父节点的值，可以直接覆盖index即可.
        elements[index] = o;
    }

    /**
     * 删除堆顶元素，并添加新元素
     * 做法一：先删除再添加.相当于先siftDown然后siftUp.
     * 做法二：覆盖堆顶，然后进行siftDown
     */
    @Override
    public E replace(E element) {
        elementNotNullyCheck(element);
        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("heap is empty");
        }
    }

    private void elementNotNullyCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        Integer index = ((Integer) node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        Integer index = ((Integer) node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(Integer) node];
    }
}
