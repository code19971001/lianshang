package com.it._01_sort.compare;


import com.it._01_sort.Sort;

/**
 * 归并排序：
 * 1）不断地将当前序列分割为2个子序列，直到不能再次分割，序列中只剩下一个元素。
 * 2）不断地将2个子序列合并为一个有序的序列。
 *
 * @author : code1997
 * @date : 2021/3/29 22:15
 */
public class MergeSort<E extends Comparable<E>> extends Sort<E> {
    public static void main(String[] args) {
        Integer[] array = {0, 1, 5, 6, 3, 4, 5, 9};
        MergeSort<Integer> eMergeSort = new MergeSort<>();
        eMergeSort.marge2(0, array.length >> 1, array.length);
    }


    /**
     * 堆[begin,end)范围内的数据进行归并排序。
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        marge(begin, mid, end);
    }

    /**
     * 将[begin ,mid)和[mid ,end)范围中的数据合并到一起。
     * 为了更好的进行merge操作，最好将其中1组序列备份出来，推荐将左边的数组进行备份。
     */
    private void marge(int begin, int mid, int end) {
        //备份[begin，mid)数组。
        System.arraycopy(array, begin, leftArray, 0, mid - begin);
        int index = begin;

        for (int i = begin, j = mid; i <= mid && j <= end; i++, j++) {
            if (i == mid) {
                break;
            }
            if (j == end) {
                //说明右边数组到头了。
                System.arraycopy(leftArray, i - begin, array, index, mid - i);
                break;
            }
            if (cmpElement(leftArray[i - begin], array[j]) <= 0) {
                array[index++] = leftArray[i - begin];
                j--;
            } else {
                array[index++] = array[j];
                i--;
            }
        }
    }

    /**
     * 将[begin ,mid)和[mid ,end)范围中的数据合并到一起。
     * 为了更好的进行merge操作，最好将其中1组序列备份出来，推荐将左边的数组进行备份。
     * 时间复杂度：O(n)。
     */
    private void marge2(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;
        //备份数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }
        //必定有一个到了终点：
        // 左边的数组到了重点，无需任何操作
        // 右边数组到了重点，需要复制左边数组的剩余内容到array中去。
        while (li < le) {
            if (ri < re && cmpElement(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }

    }

    private E[] leftArray;

    @Override
    protected void sort() {
        leftArray = (E[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

}
