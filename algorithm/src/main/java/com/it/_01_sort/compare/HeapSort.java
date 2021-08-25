package com.it._01_sort.compare;

import com.it.Sort;
import com.it.tools.Integers;
import com.it.tools.Times;

/**
 * 堆排序 : 实际上是对选择排序的一种优化，堆来选最大值的过程中，时间复杂度为log(n)，
 * 因此堆排序为：nLog(n)+n。
 * 非稳定的排序方式。
 *
 * @author :code1997
 * @date : 2021/3/27 20:36
 */
public class HeapSort<E extends Comparable<E>> extends Sort<E> {
    private int heapSize;

    public static void main(String[] args) {
        Integer[] data = Integers.random(10000, 1, 20000);
        Times.test("HeapSort", () -> new HeapSort<Integer>().sort(data));
    }

    /**
     * 堆数组进行原地建堆。建堆的时间复杂度为O(n)。
     */
    private void siftDown(int index) {
        E element = array[index];
        int half = heapSize >> 1;
        while (index < half) {
            // index必须是非叶子节点
            // 默认是左边跟父节点比
            int childIndex = (index << 1) + 1;
            E child = array[childIndex];

            int rightIndex = childIndex + 1;
            // 右子节点比左子节点大
            if (rightIndex < heapSize &&
                    cmpElement(array[rightIndex], child) > 0) {
                child = array[childIndex = rightIndex];
            }

            // 大于等于子节点
            if (cmpElement(element, child) >= 0) {
                break;
            }
            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }

    @Override
    protected void sort() {
        heapSize = array.length;
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        while (heapSize > 1) {
            //交换堆顶和尾部元素
            swap(0, --heapSize);
            //对0位置进行siftDown,恢复堆的性质。
            siftDown(0);
        }
    }
}
