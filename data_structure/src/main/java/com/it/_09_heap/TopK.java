package com.it._09_heap;

import com.it._06_tree.printer.BinaryTrees;

/**
 * 从n个整数中，找出最大的前k个数(k远远小于n)
 * 全排序：O(nlogn)
 * 堆：自下而上的下溢：O(nlogk)
 *
 * @author : code1997
 * @date : 2021/11/25 23:51
 */
public class TopK {

    public static void main(String[] args) {
        Integer[] data = {1, 44, 2, 5, 7, 10, 3, 14, 55, 4, 56, 23, 67, 34};
        BinaryHeap<Integer> minHeap = new BinaryHeap<>((o1, o2) -> o2 - o1);
        //假设找top5
        int k = 5;
        //O(nlogk)
        for (Integer datum : data) {
            if (minHeap.size < k) {
                minHeap.add(datum);
                continue;
            }
            if (minHeap.get() < datum) {
                //replace的时间复杂度为O(logk)
                minHeap.replace(datum);
            }
        }
        BinaryTrees.println(minHeap);
    }
}
