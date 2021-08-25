package com.it._01_sort.sort;

import com.it.Sort;

/**
 * 基数排序：非常适合于整数排序，尤其是非负整数。
 * 实现：分别为每位数使用计数排序来进行排序。
 * 时间复杂度：O(d*(n+k)),d是最大值的位数。k是进制数(比如十进制，就是10)，属于稳定排序。
 *
 * @author : code1997
 * @date : 2021/4/2 0:23
 */
public class RadixSort extends Sort<Integer> {

    private void radixSort() {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        for (int i = 1; i <= max; i *= 10) {
            countingSort(i);
        }
    }

    /**
     * 对0到9进行排序,因此为了简单，数组长度就直接定义为10.
     */
    private void countingSort(int divider) {
        //开辟内存空间,创建计数表
        int[] countTable = new int[10];
        for (int i = 0; i < array.length; i++) {
            //假设数组的最小值为min，当前值在数组中的索引就为array[i]-min。
            //count[k-min]-p：p代表倒数第几个k。
            countTable[array[i] / divider % 10]++;
        }
        //累加前面的数的出现的次数
        for (int i = 1; i < countTable.length; i++) {
            countTable[i] += countTable[i - 1];
        }
        //遍历并给新数组赋值
        Integer[] data = new Integer[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            data[--countTable[array[i] / divider % 10]] = array[i];
        }
        //赋值给之前的初始元素
        for (int i = 0; i < data.length; i++) {
            array[i] = data[i];
        }
    }


    @Override
    protected void sort() {
        radixSort();

    }
}
