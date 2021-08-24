package com.it.tree;

import com.it.printer.BinaryTrees;

import java.util.Random;

/**
 * @author : code1997
 * @date : 2021/8/23 22:06
 */
public class TestMorris {

    private static class MorrisTree extends BinarySearchTree<Integer> {
        @Override
        public void inorderTraversal() {
            System.out.println();
            morris(root);
            System.out.println();
        }

        /**
         * 使用morris的方式实现空间复杂度为O(1)级别的二叉树中序遍历
         *
         * @param node
         */
        private void morris(Node<Integer> node) {
            if (node == null) {
                return;
            }
            while (node != null) {
                if (node.leftChild != null) {
                    //找到当前节点的前驱节点
                    Node<Integer> precursor = node.leftChild;
                    while (precursor.rightChild != null && precursor.rightChild != node) {
                        precursor = precursor.rightChild;
                    }
                    if (precursor.rightChild == null) {
                        precursor.rightChild = node;
                        node = node.leftChild;
                    } else {
                        //precursor.rightChild == node
                        precursor.rightChild = null;
                        System.out.print(node.element + "\t");
                        node = node.rightChild;
                    }
                } else {
                    System.out.print(node.element + "\t");
                    node = node.rightChild;
                }
            }
        }

        /**
         * 递归的方式实现中序遍历
         */
        private void inorder(Node<Integer> node) {
            if (node == null) {
                return;
            }
            inorder(node.leftChild);
            System.out.print(node.element + "\t");
            inorder(node.rightChild);
        }
    }

    public static void main(String[] args) {
        MorrisTree morrisTree = new MorrisTree();
        for (int i = 0; i < 10; i++) {
            morrisTree.add(new Random().nextInt(100));
        }
        System.out.println("-------before morris--------");
        BinaryTrees.print(morrisTree);
        //遍历前后不能改变树的结构
        morrisTree.inorderTraversal();
        System.out.println("-------after morris--------");
        BinaryTrees.print(morrisTree);
    }
}
