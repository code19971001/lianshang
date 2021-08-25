package com.it._01_sort.sort;


import com.it._01_sort.Sort;

/**
 * 计数排序：适合于对一定范围内的数据进行排序，是一种以空间换取时间的算法。在某些时间可能
 * 时间复杂度要比O(nlogn)更小。适合k比较小，就是范围i比较小的数据进行排序。
 *
 * @author : code1997
 * @date : 2021/4/1 23:06
 */
public class CountingSort extends Sort<Integer> {

    /**
     * 创建一个数组，来记录数据出现的次数.
     * 缺点：
     * 1）浪费内存空间。
     * 2）无法对负数进行排序。
     * 3）无法对自定义对象排序。
     * 4）不稳定的排序方式。
     */
    private void countingSort1() {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }

        }
        //开辟内存空间,创建计数表
        int[] countTable = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            countTable[array[i]]++;
        }
        //根据整数出现的次数，对整数进行排序
        int curIndex = 0;
        for (int i = 0; i < countTable.length; i++) {
            while (countTable[i] > 0) {
                array[curIndex++] = i;
                countTable[i] = countTable[i] - 1;
            }
        }

    }

    /**
     * 优化1：记录最小值，可以对负整数排序，可以节省一定的空间。
     * 优化2：每个次数类加上其前面的所有次数，得到的就是元素在有序列表中的位置可，然后倒叙遍历可以获得稳定性
     * 时间复杂度：O(3n+k)=>O(n+k).
     * 空间复杂度：O(n+k),k为整数的时间范围。
     * 此实现是稳定的时间复杂度。
     * 对于自定义对象，我们可以根据自定义对象的某个整数属性来进行排序。
     */
    private void countingSort2() {
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        //开辟内存空间,创建计数表
        int[] countTable = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            //假设数组的最小值为min，当前值在数组中的索引就为array[i]-min。
            //count[k-min]-p：p代表倒数第几个k。
            countTable[array[i] - min]++;
        }
        //累加前面的数的出现的次数
        for (int i = 1; i < countTable.length; i++) {
            countTable[i] += countTable[i - 1];
        }
        //遍历并给新数组赋值
        Integer[] data = new Integer[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            data[--countTable[array[i] - min]] = array[i];
        }
        //赋值给之前的初始元素
        for (int i = 0; i < data.length; i++) {
            array[i] = data[i];
        }
    }

    @Override
    protected void sort() {
        countingSort2();
    }
}
