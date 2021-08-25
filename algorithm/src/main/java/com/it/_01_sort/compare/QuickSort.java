package com.it._01_sort.compare;


import com.it._01_sort.Sort;

/**
 * @author : code1997
 * @date : 2021/3/31 20:58
 */
public class QuickSort<E extends Comparable<E>> extends Sort<E> {


    private void quickSort(int begin, int end) {
        if (end - begin <= 1) {
            return;
        }
        //第一个元素为轴点元素，因此先备份轴点元素
        int pivotIndex = pivotIndex(begin, end);
        quickSort(begin, pivotIndex);
        quickSort(pivotIndex + 1, end);
    }

    /**
     * 轴点的构造
     */
    private int pivotIndex(int begin, int end) {
        //随机选取一个位置的元素和begin替换即可
        swap(begin, (int) (Math.random() * (end - begin)) + begin);
        E pivot = array[begin];
        //true:从左到右; flase:从右到左
        boolean flag = false;
        //我们的认定的范围是[ )，因此需要注意顺序。
        end--;
        while (begin < end) {
            if (!flag) {
                //从右往左
                //对于轴点的判断，当某个值和轴点元素相等的时候，我们就将其放到另外一边会导致分割的比较均匀，可以变相的降低时间复杂度。
                if (cmpElement(array[end], pivot) > 0) {
                    end--;
                } else {
                    array[begin] = array[end];
                    begin++;
                    //翻转方向
                    flag = !flag;
                }
            } else {
                //从左往右
                if (cmpElement(pivot, array[begin]) > 0) {
                    begin++;
                } else {
                    array[end] = array[begin];
                    end--;
                    flag = !flag;
                }
            }
        }
        //代表轴点所在的位置
        array[begin] = pivot;
        return begin;
    }


    @Override
    protected void sort() {
        quickSort(0, array.length);
    }
}
