package com.it._11_trie;

import java.util.HashMap;

/**
 * @author : code1997
 * @date : 2021/11/29 21:32
 */
public class Trie<V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private final Node<V> root = new Node<>(null);

    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root.children = null;
    }


    /**
     * 返回最后一个node节点
     */
    private Node<V> node(String key) {
        keyCheck(key);
        Node<V> node = root;
        for (char c : key.toCharArray()) {
            if (node == null || node.children == null || node.children.isEmpty()) {
                return null;
            }
            node = node.children.get(c);
        }
        return node;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key must not be null or blank!");
        }
    }


    public V get(String key) {
        Node<V> node = node(key);
        return node != null && (node.color) ? node.value : null;
    }


    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.color;
    }

    public V add(String key, V value) {
        keyCheck(key);
        Node<V> node = root;
        for (char c : key.toCharArray()) {
            boolean emptyChildren = (node.children == null);
            Node<V> childNode = emptyChildren ? null : node.children.get(c);
            if (childNode == null) {
                childNode = new Node<>(node);
                node.children = emptyChildren ? new HashMap<>() : node.children;
                node.children.put(c, childNode);
            }
            node = childNode;
        }
        if (node.color) {
            //值覆盖
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        //说明是新增单词
        node.color = RED;
        node.value = value;
        size++;
        return null;

    }

    /**
     * 单词存在删除
     * 1.如果有子节点，只需要将value清空，颜色改为黑色，size--
     * 2.没有子节点，上下网上删除。直到红色父节点。
     *
     * @param key
     * @return
     */
    public V remove(String key) {
        Node<V> node = node(key);
        if (node == null || (!node.color)) {
            //不需要做任何处理
            return null;
        }
        V oldValue = node.value;
        if (node.children != null) {
            //后面还有其他节点，变色即可。
            node.color = BLACK;
            size--;
        }
        //没有子节点，逐渐向上删除，直到第一个为红色的父节点或者没有父节点位置。
        for (int i = key.toCharArray().length - 1; i >= 0; i--) {
            if (node.parent == null || node.parent.color) {
                node.parent.children.remove(key.charAt(i));
                break;
            }
            node.parent.children.remove(key.charAt(i));
            node = node.parent;
        }
        return oldValue;
    }

    public boolean startsWith(String prefix) {
        return node(prefix) != null;
    }

    private static class Node<V> {

        V value;
        //颜色来代表是不是单词的末尾，如果为红色，代表是一个单词的结尾.
        boolean color = BLACK;
        Node<V> parent;
        HashMap<Character, Node<V>> children;

        public Node(Node<V> parent) {
            this.parent = parent;
        }
    }

}
