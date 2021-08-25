package com.it._06_tree.bst;

import com.it._06_tree.printer.BinaryTrees;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author : code1997
 * @date :2021-03-2021/3/20 20:51
 */
public class AVLTest {

    @Test
    public void testAdd() {
        Integer[] data = {85, 19, 69, 3, 7, 99, 95};
        BST<Integer> bst = new BST<>();
        AVL<Integer> avl = new AVL<>();
        Arrays.stream(data).forEach(bst::add);
        Arrays.stream(data).forEach(avl::add);
        BinaryTrees.println(bst);
        BinaryTrees.println(avl);
        avl.remove(99);
        avl.remove(85);
        BinaryTrees.println(avl);
        avl.remove(95);

        BinaryTrees.println(avl);
    }

    @Test
    public void testRemove() {

    }
}
