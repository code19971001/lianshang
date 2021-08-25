package com.it._03_linkedlist.linkedlist;


import com.it._03_linkedlist.AbstractList;

/**
 * 单向链表的实现
 * 对于get和set方法来说，外部关心的是node.element,而不是node节点，因此我们需要对封装一个方法来返回指定索引处的node节点，但是权限要设置为private
 *
 * @author code1997
 */
public class SingleLinkedList1<E> extends AbstractList<E> {

    /**
     * 头节点
     */
    private Node<E> firstNode;

    /**
     * 定义为静态内部类，对于链表来说，元素存储在node节点中
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
        //直接将头节点的的first指向null就可以，gc会自动清理无用的哪些链表信息.
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
            //对于第一个node节点来说，需要特殊处理，因为是第一个元素
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
