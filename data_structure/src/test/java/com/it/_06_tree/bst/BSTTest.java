package com.it._06_tree.bst;

import com.it._06_tree.bean.Person;
import com.it._06_tree.file.Files;
import com.it._06_tree.printer.BinaryTrees;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author : code1997
 * @date : 2021/11/2 22:04
 */
public class BSTTest {

    @Test
    public void add() {
        Integer[] data = {7, 4, 9, 2, 5, 8, 11, 3};
        BST<Integer> bst = new BST<>();
        Arrays.stream(data).forEach(bst::add);
        BinaryTrees.println(bst);
    }

    @Test
    public void testBinarySearchTree() {
        Integer[] data = {7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Arrays.stream(data).forEach(tree::add);
        BinaryTrees.println(tree);
        System.out.println("--------------------------------------");
        BinarySearchTree<Person> searchTree = new BinarySearchTree<>();
        Arrays.stream(data).forEach(age -> searchTree.add(new Person(age)));
        BinaryTrees.println(searchTree);
        System.out.println("--------------------------------------");
        BinarySearchTree<Person> searchTree2 = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        Arrays.stream(data).forEach(age -> searchTree2.add(new Person(age)));
        BinaryTrees.println(searchTree2);
    }

    @Test
    public void testBinarySearchTree2() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add((int) (Math.random() * 100));
        }
        data.forEach(tree::add);
        BinaryTrees.println(tree);
        Files.writeToFile("src/main/resources/bst.txt", BinaryTrees.printString(tree));
    }

    @Test
    public void testBinarySearchTree3() {
        Integer[] data = {7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Arrays.stream(data).forEach(tree::add);
        BinaryTrees.println(tree);
        System.out.println("前序遍历");
        tree.preorderTraversal();
        System.out.println();
        System.out.println("中序遍历");
        tree.inorderTraversal();
        System.out.println();
        System.out.println("后序遍历");
        tree.postorderTraversal();
        System.out.println();
        System.out.println("层序遍历");
        tree.levelOrderTraversal();
        System.out.println();
        System.out.println("自定义遍历逻辑");
        tree.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                System.out.println("--" + element);
            }
        });
    }

    @Test
    public void testBinarySearchTree4() {
        Integer[] data = {7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Arrays.stream(data).forEach(tree::add);
        BinaryTrees.println(tree);
        System.out.println(tree.toString());

    }

    @Test
    public void testBinarySearchTreeHeight() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add((int) (Math.random() * 100));
        }
        data.forEach(tree::add);
        BinaryTrees.println(tree);
        System.out.println("树的高度为：" + tree.height());
        System.out.println("树的高度为：" + tree.height2());

    }

    @Test
    public void testBinarySearchTreeIsComplete() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        List<Integer> data = Arrays.asList(7, 4, 9, 2, 5);
        data.forEach(tree::add);
        BinaryTrees.println(tree);
        System.out.println("是否是完全二叉树：" + tree.isComplete());
        System.out.println("是否是完全二叉树：" + tree.isComplete2());
    }

    @Test
    public void testBinarySearchTreeRemove() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        List<Integer> data = Arrays.asList(7, 4, 9, 2, 5, 8, 11, 3, 12, 1);
        data.forEach(tree::add);
        BinaryTrees.println(tree);
        tree.remove(9);
        BinaryTrees.println(tree);
    }
}