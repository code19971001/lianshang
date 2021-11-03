package com.it._06_tree.bst;

import com.it._06_tree.printer.BinaryTrees;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author : code1997
 * @date : 2021/11/3 21:41
 */
public class BinarySearchTreeTest {

    BinarySearchTree<Integer> bst;

    @Before
    public void init() {
        bst = new BinarySearchTree<>();
        Integer[] data = {7, 4, 9, 2, 5, 8, 11, 3};
        Arrays.stream(data).forEach(bst::add);
        BinaryTrees.println(bst);
    }


    @Test
    public void preorderTraversal() {
        bst.preorderTraversal();
    }

    @Test
    public void inorderTraversal() {
        bst.inorderTraversal();
    }

    @Test
    public void postorderTraversal() {
        bst.postorderTraversal();
    }

    @Test
    public void levelOrderTraversal() {
        bst.levelOrderTraversal();
    }

    @Test
    public void preorder() {
        //1.允许我们对二叉树的元素进行自定义的访问方式
        bst.preorder(e -> System.out.print(e + 1 + "\t"));
        //2.遍历的时候我们需要在外界终止遍历，那么我们如何实现这个功能呢？？在visitor接口中定义一个成员变量来保存遍历的状态.
    }

    @Test
    public void testToString(){
        System.out.println(bst);
    }
}