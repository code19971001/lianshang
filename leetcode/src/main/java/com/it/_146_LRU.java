package com.it;

import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 要求：get 和 put的时间复杂度都为-1.
 * <p>
 * hash表 + 双端队列
 *
 * @author : code1997
 * @date : 2021/8/31 20:43
 */
public class _146_LRU {

    private Map<Integer, Node> lruCache;
    private int capacity;
    private Node firstNode;
    private Node lastNode;

    public _146_LRU(int capacity) {
        this.capacity = capacity;
        lruCache = new HashMap<>(capacity);
        firstNode = new Node();
        lastNode = new Node();
        firstNode.next = lastNode;
        lastNode.prev = firstNode;
    }

    /**
     * 如果存在：返回value
     * 如果不存在：返回-1
     */
    public int get(int key) {
        Node node = lruCache.get(key);
        if (node == null) {
            return -1;

        }
        removeNode(node);
        addAfterFirst(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = lruCache.get(key);
        if (node != null) {
            node.value = value;
            removeNode(node);
        } else {
            if (capacity == lruCache.size()) {
                //满了，需要淘汰最近最近最少使用的节点:移除map以及lastnode
                removeNode(lruCache.remove(lastNode.prev.key));
            }
            node = new Node(key, value);
            lruCache.put(key, node);
        }
        addAfterFirst(node);
    }

    /**
     * 将node节点插入到first节点的后面
     */
    private void addAfterFirst(Node node) {
        node.next = firstNode.next;
        node.next.prev = node;
        firstNode.next = node;
        node.prev = firstNode;
    }

    /**
     * 双向链表中删除node节点
     */
    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    public static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {

        }
    }


}
