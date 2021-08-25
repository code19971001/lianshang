package com.it._03_linkedlist.linkedlist;

import com.it.AbstractList;

/**
 * @author 龍
 */
public class DoubleLinkedList1<E> extends AbstractList<E> {

    private Node<E> firstNode;
    private Node<E> lastNode;

    /**
     * 定义为静态内部类
     */
    private static class Node<E> {
        Node<E> prev;
        E element;
        Node<E> next;

        public Node(Node<E> prev,E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "prev=" + prev +
                    ", element=" + element +
                    ", next=" + next +
                    '}';
        }
    }

    @Override
    public void clear() {
        //直接将头节点的的first指向null就可以，注意java的垃圾回收机制的GC Root的规则。
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return indexNode(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = indexNode(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    /**
     * 根据索引，返回指定的node节点
     *
     * @param index：指定的索引
     * @return ：索引处的node节点
     */
    private Node<E> indexNode(int index) {
        //边界检查
        rangeCheck(index);
        //当前节点指向头节点
        Node<E> cur;
        if (index < (size >>1)){
            cur = firstNode;
            //找到指定索引
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
        }else {
            cur = lastNode;
            //找到指定索引，注意此处为>，而非>=。
            for (int i = size-1; i > index  ; i--) {
                cur = cur.prev;
            }
        }

        return cur;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckAdd(index);
        if (index == size) {
            Node<E> oldLast = lastNode;
            lastNode = new Node<>(oldLast , element, null);
            //lastNode.prev.next=lastNode
            if (oldLast == null ){
                //代表是第一个元素
                firstNode = lastNode;
            } else {
                //如果没有元素：oldLast == null;调用next会出现空指针异常。
                oldLast.next = lastNode;
            }
        }else {
            //正常情况下的指向。
            Node<E> next=indexNode(index);
            Node<E> prev = next.prev;
            Node<E> eNode = new Node<>(prev, element, next);
            next.prev=eNode;
            if (prev == null){
                //代表index==0
                firstNode = eNode;
            }else {
                prev.next = eNode;
            }
        }

        //元素的个数++
        size++;
    }

    @Override
    public E remove(int index) {
        //对下标进行索引范围的检查
        rangeCheck(index);
        Node<E> node = indexNode(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        if (prev == null && next == null){
            //说明只存在一个元素
            firstNode = null;
            lastNode = null;
        }else {
            //说明存在多个元素
            if (prev == null){
                //可以理解为index==0
                next.prev = null;
                firstNode = next;
            } else {
                prev.next = next;
            }

            if (next == null){
                //可以等价：index==size-1
                lastNode = prev;
            }else {
                next.prev = prev;
            }
        }

        //元素的个数++
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = firstNode;
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            Node<E> node = firstNode;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",[");
        Node<E> tempNode = firstNode;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(tempNode.prev != null ? tempNode.prev.element+"_" : "null"+"_");
            builder.append(tempNode.element+"_");
            builder.append(tempNode.next != null? tempNode.next.element : "null");

            tempNode = tempNode.next;
        }
        builder.append("]");
        return builder.toString();
    }

}
