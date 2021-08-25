package com.it._03_linkedlist;


import com.it.linkedlist.DoubleLinkedList1;

public class LinkedListTest {
    public static void main(String[] args) {
        DoubleLinkedList1<Object> nodes = new DoubleLinkedList1<>();
        nodes.add("11");
        nodes.add("22");
        nodes.add("33");
        nodes.add("44");

        nodes.add(0,"55");
        nodes.add(2,"66");
        nodes.add(nodes.size,"77");
        /*nodes.remove(0);
        nodes.remove(2);
        nodes.remove(nodes.size - 1);*/
        nodes.add("88");

        nodes.remove(0);
        System.out.println(nodes);

    }
}
