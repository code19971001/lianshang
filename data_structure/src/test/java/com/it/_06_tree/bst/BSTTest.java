package com.it._06_tree.bst;

import com.it._06_tree.printer.BinaryTrees;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author : code1997
 * @date : 2021/11/2 22:04
 */
public class BSTTest {

    @Test
    public void add() {
        Integer[] data = {7,4,9,2,5,8,11,3};
        BST<Integer> bst = new BST<>();
        Arrays.stream(data).forEach(bst::add);
        BinaryTrees.println(bst);
    }
}