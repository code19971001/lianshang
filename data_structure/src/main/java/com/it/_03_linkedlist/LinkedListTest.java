package com.it._03_linkedlist;


import com.it._03_linkedlist.linkedlist.DoubleLinkedList;

/**
 * @author code1997
 */
public class LinkedListTest {
    public static void main(String[] args) {
        DoubleLinkedList<Object> nodes = new DoubleLinkedList<>();
        nodes.add("11");
        nodes.add("22");
        nodes.add("33");
        nodes.add("44");

        nodes.add(0, "55");
        nodes.add(2, "66");
        nodes.add(nodes.size, "77");
        nodes.add("88");
        nodes.remove(0);
        System.out.println(nodes);

    }
}
