package com.it._01_sort.compare;

import com.it.Sort;
import com.it.tools.Asserts;
import com.it.tools.Integers;
import com.it.tools.Times;

/**
 * SelectionSort : 选择排序，每轮获取一个最大的元素，和最后一个元素进行交换。
 *
 * @author : code1997
 * @date : 2021/3/27 20:36
 */
public class SelectionSort<E extends Comparable<E>> extends Sort<E> {

    public static void main(String[] args) {
        Integer[] data = Integers.random(100, 1, 1000);
        Times.test("selectionSort1", () -> {
            assert data != null;
            selectionSort1(data);
        });
        Asserts.test(Integers.isAscOrder(data));
    }

    /**
     * 相对与冒泡排序来说，他的效率要高一些。
     */
    private static void selectionSort1(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] >= array[maxIndex]) {
                    maxIndex = begin;
                }
            }
            //交换
            int temp = array[end];
            array[end] = array[maxIndex];
            array[maxIndex] = temp;
        }
    }

    @Override
    public void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, maxIndex) >= 0) {
                    maxIndex = begin;
                }
            }
            //交换
            swap(maxIndex, end);
        }
    }
}
