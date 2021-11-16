package com.it._06_tree.bst;

import com.it._06_tree.printer.BinaryTrees;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author : code1997
 * @date : 2021/11/12 15:22
 */
public class RBTTest {

    @Test
    public void afterAdd() {
        Integer[] data = {55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
        RBT<Integer> rbt = new RBT<>();
        Arrays.stream(data).forEach(rbt::add);
        BinaryTrees.println(rbt);
    }

    @Test
    public void afterRemove() {
        Integer[] data = {55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
        RBT<Integer> rbt = new RBT<>();
        Arrays.stream(data).forEach(rbt::add);
        BinaryTrees.println(rbt);
        System.out.println("------------------------");
        for (Integer datum : data) {
            rbt.remove(datum);
            System.out.println("[" + datum + "]");
            BinaryTrees.println(rbt);
            System.out.println("------------------------");
        }
    }
}