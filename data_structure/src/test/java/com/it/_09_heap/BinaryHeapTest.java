package com.it._09_heap;

import com.it._06_tree.printer.BinaryTrees;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : code1997
 * @date : 2021/11/24 23:06
 */
public class BinaryHeapTest {

    BinaryHeap<Integer> maxHeap;

    @Before
    public void init() {
        maxHeap = new BinaryHeap<>();
        maxHeap.add(68);
        maxHeap.add(72);
        maxHeap.add(43);
        maxHeap.add(50);
        maxHeap.add(38);
        maxHeap.add(10);
        maxHeap.add(90);
        maxHeap.add(65);
        System.out.println("max heap init successful!");
    }

    @Test
    public void testPrint() {
        BinaryTrees.println(maxHeap);
    }

    @Test
    public void testRemove() {
        BinaryTrees.println(maxHeap);
        maxHeap.remove();
        BinaryTrees.println(maxHeap);
        maxHeap.remove();
        BinaryTrees.println(maxHeap);
    }

    @Test
    public void testReplace() {
        BinaryTrees.println(maxHeap);
        maxHeap.replace(1);
        BinaryTrees.println(maxHeap);
    }

}