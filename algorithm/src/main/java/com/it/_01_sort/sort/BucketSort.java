package com.it._01_sort.sort;

import com.it.Sort;

import java.util.LinkedList;
import java.util.List;

/**
 * 空间复杂度：O（n+m）,m是桶的数量。
 * 时间复杂度：初始化桶为O(n)，对每个桶进行单独排序m*O(n/m*log(n/m))=O(n+n*log(n/m))=O(n+n*logn-n*logm)，
 *
 * @author : code1997
 * @date : 2021/4/3 22:10
 */
public class BucketSort extends Sort<Double> {

    private void bucketSort() {
        //创建桶并添加元素
        List<Double>[] buckets = new List[array.length];
        for (int i = 0; i < array.length; i++) {
            int bucketIndex = (int) (array[i] * array.length);
            List<Double> bucket = buckets[bucketIndex];
            if (bucket == null) {
                bucket = new LinkedList<>();
                buckets[bucketIndex] = bucket;
            }
            bucket.add(array[i]);
        }
        //对每个桶进行排序。
        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) {
                continue;
            }
            buckets[i].sort(null);
            //遍历覆盖
            for (Double d : buckets[i]) {
                array[index++] = d;
            }
        }


    }


    @Override
    protected void sort() {
        bucketSort();
    }
}
