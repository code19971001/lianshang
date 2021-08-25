package com.it._01_sort;


import com.it._01_sort.compare.*;
import com.it._01_sort.sort.BucketSort;
import com.it._01_sort.sort.CountingSort;
import com.it._01_sort.sort.RadixSort;
import com.it.tools.Asserts;
import com.it.tools.Integers;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author : code1997
 * @date :2021-03-2021/3/27 20:21
 */
public class SortTest {

    @Test
    public void sortTest() {
        Integer[] data = Integers.random(10000, 1, 10000);
        testSort(data,
                new BobbleSort<>(),
                new SelectionSort<>(),
                new HeapSort<>(),
                new InsertionSort<>(),
                new MergeSort<>(),
                new QuickSort<>(),
                new ShellSort<>(),
                new CountingSort(),
                new RadixSort());
    }

    @SafeVarargs
    private static void testSort(Integer[] data, Sort<Integer>... sorts) {
        for (Sort<Integer> sort : sorts) {
            Integer[] tempData = Integers.copy(data);
            sort.sort(tempData);
            Asserts.test(Integers.isAscOrder(tempData));
        }
        Arrays.sort(sorts);
        Arrays.stream(sorts).forEach(System.out::println);
    }

    @Test
    public void testMargeSort() {
        Integer[] data = {1, 4, 5, 6, 1, 2};
        MergeSort<Integer> mergeSort = new MergeSort<>();
        mergeSort.sort(data);
    }

    @Test
    public void testQuickSort() {
        Integer[] data = {1, 4, 5, 6, 1, 2};
        QuickSort<Integer> quickSort = new QuickSort<>();
        quickSort.sort(data);
        System.out.println(Arrays.toString(data));
    }

    @Test
    public void testShellSort() {
        Integer[] data = {1, 4, 5, 6, 1, 2};
        Sort<Integer> shellSort = new ShellSort<>();
        shellSort.sort(data);
        System.out.println(Arrays.toString(data));
    }

    @Test
    public void testCountSort() {
        Integer[] data = {1, 4, 5, 6, 1, 2};
        Sort sort = new CountingSort();
        sort.sort(data);
        System.out.println(Arrays.toString(data));
    }

    @Test
    public void testBucketSort() {
        Double[] data = {0.34, 0.47, 0.29, 0.84, 0.45, 0.38, 0.35, 0.76};
        BucketSort bucketSort = new BucketSort();
        bucketSort.sort(data);
        System.out.println(Arrays.toString(data));
    }
}
