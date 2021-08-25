package com.it._03_linkedlist.linkedlist;

import com.it.AbstractList;

public class SingleLinkedList1<E> extends AbstractList<E> {

    private Node<E> firstNode;

    /**
     * 定义为静态内部类
     */
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void clear() {
        //直接将头节点的的first指向null就可以
        firstNode = null;
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
        Node<E> cur = firstNode;
        //找到指定索引
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckAdd(index);
        if (index == 0) {
            //firstNode.next=new Node<>(element,firstNode.next);
            firstNode = new Node<>(element, firstNode);
        } else {
            //获取添加索引处的前一个节点
            Node<E> preNode = indexNode(index - 1);
            //创建一个新的节点，next指向原来index位置的节点//index处的前一个节点指向新创建的节点
            preNode.next = new Node<>(element, preNode.next);
        }
        //元素的个数++
        size++;
    }

    @Override
    public E remove(int index) {
        //对下标进行索引范围的检查
        rangeCheck(index);
        Node<E> node = firstNode;
        if (index == 0) {
            firstNode = firstNode.next;
        } else {
            //获取添加索引处的前一个节点
            Node<E> preNode = indexNode(index - 1);
            //获取需要删除的节点
            node = preNode.next;
            preNode.next = node.next;
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
            builder.append(tempNode.element);
            tempNode = tempNode.next;
        }
        builder.append("]");
        return builder.toString();
    }

}
