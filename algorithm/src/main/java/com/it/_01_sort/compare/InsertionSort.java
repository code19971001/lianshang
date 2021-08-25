package com.it._01_sort.compare;

import com.it.Sort;
import com.it.tools.Integers;

/**
 * 插入排序：执行过程中将我们的序列分成两部分，头部是已经排好序的，尾部是待排序的。
 *
 * @author : code1997
 * @date : 2021/3/28 10:31
 */
public class InsertionSort<E extends Comparable<E>> extends Sort<E> {

    public static void main(String[] args) {
        Integer[] data = Integers.random(10, 1, 20);
        assert data != null;
        insertionSort3(data);
        Integers.println(data);

    }

    /**
     * 时间复杂度和逆序对的数量成正比，如果逆序对越多则时间复杂度越高，最差为n^2。
     * 如果数组完全是升序的，则时间复杂度为O(n).
     * 空间复杂度O(1)。
     * 属于稳定排序。
     */
    private static void insertionSort1(Integer[] array) {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            //找到array【i】应该存在的位置
            while (cur > 0 && array[cur] < array[cur - 1]) {
                int temp = array[cur];
                array[cur] = array[cur - 1];
                array[cur - 1] = temp;
                cur--;
            }
        }
    }

    /**
     * 优化1：将"交换"转为"挪动"，备份待插入元素，将比待插入元素大的元素往后挪动。
     * 如果逆序对越多，优化就越明显。
     */
    private static void insertionSort2(Integer[] array) {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            int insertionValue = array[cur];
            //找到array【i】应该存在的位置
            while (cur > 0 && insertionValue < array[cur - 1]) {
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = insertionValue;
        }
    }

    /**
     * 优化2：使用二分搜索的思想，如果是有序数组的查找，则二分查找的时间复杂度为log(n)。
     */
    private static void insertionSort3(Integer[] array) {
        for (int begin = 1; begin < array.length; begin++) {
            int insertionIndex = searchIndex(array, begin);
            int cur = begin;
            int insertionValue = array[cur];
            while (cur > insertionIndex) {
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = insertionValue;
        }
    }

    /**
     * 找到第一个大于v的索引的位置。
     */
    private static int searchIndex(Integer[] array, int end) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = end;
        int v = array[end];
        while (left < right) {
            int mid = (left + right) >> 1;
            if (v < array[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 找到第一个大于v的索引的位置。
     */
    private int searchIndex(E[] array, int end) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = end;
        E v = array[end];
        while (left < right) {
            int mid = (left + right) >> 1;
            if (cmpElement(v, array[mid]) < 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 二分查找的实现。
     */
    private static int binarySearch(int[] array, int v) {
        if (array == null || array.length == 0) {
            //未找到
            return -1;
        }
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) {
                end = mid;
            } else if (v > array[mid]) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 时间复杂度依旧未O(n^2)，尽管使用了二分搜索，但是仅仅减少了寻找时间，但是挪动还是很多的次数O(n)。
     */
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int insertionIndex = searchIndex(array, begin);
            E insertionValue = array[begin];
            for (int i = begin; i > insertionIndex; i--) {
                array[i] = array[i - 1];
            }
            array[insertionIndex] = insertionValue;
        }
    }
}
