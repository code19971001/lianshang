package com.it._01_sort.compare;

import com.it.Sort;
import com.it.tools.Integers;
import com.it.tools.Times;

/**
 * 冒泡排序 : 时间复杂度度为O(n^2)
 *
 * @author : code1997
 * @date : 2021/3/27 20:36
 */
public class BobbleSort<E extends Comparable<E>> extends Sort<E> {

    public static void main(String[] args) {
        Integer[] data1 = Integers.ascOrder(1, 10000);
        Integer[] data2 = Integers.copy(data1);
        Integer[] data3 = Integers.copy(data1);

        Times.test("bobbleSort1", () -> {
            assert data1 != null;
            bobbleSort1(data1);
        });
        Times.test("bobbleSort2", () -> bobbleSort2(data2));
        Times.test("bobbleSort3", () -> bobbleSort3(data3));
    }

    public static void bobbleSort1(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin - 1] > array[begin]) {
                    int temp = array[begin - 1];
                    array[begin - 1] = array[begin];
                    array[begin] = temp;
                }
            }
        }
        
    }

    /**
     * 优化1：如果某一轮都没有进行交换，则认为已经有序。
     * 注 : 对数据有要求，要提前有序才可能会有所提升。
     */
    public static void bobbleSort2(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin - 1] > array[begin]) {
                    sorted = false;
                    int temp = array[begin - 1];
                    array[begin - 1] = array[begin];
                    array[begin] = temp;
                }
            }
            if (sorted) {
                break;
            }
        }
    }

    /**
     * 优化2：如果序列尾部已经局部有序，则可以记录最后一次交换的位置，减少比较的次数。
     */
    public static void bobbleSort3(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            //用于记录最后一次交换的位置,这里的赋值比较重要，如果数据完全有序，那么不要进行操作即可。
            int sortedIndex = -1;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin - 1] > array[begin]) {
                    int temp = array[begin - 1];
                    array[begin - 1] = array[begin];
                    array[begin] = temp;
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }


    @Override
    public void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            //用于记录最后一次交换的位置,这里的赋值比较重要，如果数据完全有序，那么不要进行操作即可。
            int sortedIndex = -1;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin - 1, begin) > 0) {
                    swap(begin, begin - 1);
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }
}
