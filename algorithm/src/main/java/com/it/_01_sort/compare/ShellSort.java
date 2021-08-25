package com.it._01_sort.compare;


import com.it._01_sort.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * shell排序：对于不同的步长序列来说存在不同的时间复杂度.
 *
 * @author : code1997
 * @date : 2021/3/31 23:34
 */
public class ShellSort<E extends Comparable<E>> extends Sort<E> {


    @Override
    protected void sort() {
        //例如：【16，8，4，2，1】
        List<Integer> stepSequence = shellStepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }
    }

    private List<Integer> shellStepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = this.array.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    /**
     * 分成多少列进行排序
     */
    private void sort(int step) {
        for (int col = 0; col < step; col++) {
            //对col列进行排序
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                E insertionValue = array[cur];
                //注意cur的取值范围。如果不大于col，可能会导致出现数组下标越界。
                while (cur > col && cmpElement(insertionValue, array[cur - step]) < 0) {
                    array[cur] = array[cur - step];
                    cur -= step;
                }
                array[cur] = insertionValue;
            }
        }
    }

}
