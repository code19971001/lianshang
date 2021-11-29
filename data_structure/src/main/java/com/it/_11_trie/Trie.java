package com.it._11_trie;

import java.util.HashMap;

/**
 * @author : code1997
 * @date : 2021/11/29 21:32
 */
public class Trie<V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private final Node<V> root = new Node<>();

    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root.getChildren().clear();
    }

    /**
     * 找到完整的单词
     */
    private Node<V> node(String key) {
        keyCheck(key);
        Node<V> node = root;
        for (char c : key.toCharArray()) {
            node = node.getChildren().get(c);
            if (node == null) {
                return null;
            }
        }
        System.out.println(node);
        //color为红色则代表是单词的结尾
        return node.color ? node : null;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key must not be null or blank!");
        }
    }


    public V get(String key) {
        Node<V> node = node(key);
        return node == null ? null : node.value;
    }


    public boolean contains(String key) {
        return node(key) != null;
    }

    public V add(String key, V value) {
        keyCheck(key);
        Node<V> node = root;
        for (char c : key.toCharArray()) {
            Node<V> childNode = node.getChildren().get(c);
            if (childNode == null) {
                childNode = new Node<>();
                node.getChildren().put(c, childNode);
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

    public V remove(String key) {
        return null;
    }

    public boolean startsWith(String prefix) {
        return false;
    }

    private static class Node<V> {
        HashMap<Character, Node<V>> children;
        V value;
        //颜色来代表是不是单词的末尾，如果为红色，代表是一个单词的结尾.
        boolean color = BLACK;

        public HashMap<Character, Node<V>> getChildren() {
            return children == null ? (children=new HashMap<>()) : children;
        }
    }

}
