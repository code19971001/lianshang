package com.it._09_skip_list;

import java.util.Comparator;

/**
 * 跳表：
 *
 * @author : code1997
 * @date : 2021/4/28 19:49
 */
public class SkipList<K, V> {

    private int level;

    private static final int MAX_LEVEL = 32;
    private static final double P = 0.25;

    public Node<K, V> first;

    public SkipList() {
        this(null);
    }

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        this.first = new Node<>(null, null, MAX_LEVEL);
    }

    private void keyCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null!");
        }
    }

    private int compare(K k1, K k2) {
        return comparator != null ? comparator.compare(k1, k2) : ((Comparable<K>) k1).compareTo(k2);
    }

    private Comparator<K> comparator;

    public int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V put(K key, V value) {
        keyCheck(key);
        Node<K, V> node = first;
        Node<K, V>[] pres = new Node[level];
        //从顶层开始出发寻找
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            //一旦当前key大于当前节点的下一个，则继续遍历
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            //表明找到指定的元素,覆盖value
            if (cmp == 0) {
                V oldValue = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldValue;
            }
            //保存所有的前驱节点
            pres[i] = node;
        }
        //如果到了这里，表明没有找到，到了最后一层，应该插入的位置是node.nexts[0]
        int newLevel = randomLevel();
        Node<K, V> newNode = new Node<>(key, value, newLevel);
        //将该节点加入到跳表中去
        for (int i = 0; i < newLevel; i++) {
            if (i >= level) {
                //防止当前新的level比较大的情况的出现
                first.nexts[i] = newNode;
            } else {
                //表明找到指定的元素,覆盖value
                newNode.nexts[i] = pres[i].nexts[i];
                pres[i].nexts[i] = newNode;
            }
        }
        //更新first节点level
        level = Math.max(level, newLevel);
        size++;
        return value;
    }

    public V remove(K key) {
        keyCheck(key);
        Node<K, V> node = first;
        Node<K, V>[] pres = new Node[level];
        //从顶层开始出发寻找
        boolean exist = false;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            //一旦当前key大于当前节点的下一个，则继续遍历
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            //保存所有的前驱节点
            pres[i] = node;
            if (cmp == 0) {
                exist = true;
            }
        }
        if (!exist) return null;

        //待删除的节点存在
        Node<K, V> removeNode = node.nexts[0];
        //修改pres指向
        for (int i = 0; i < removeNode.nexts.length; i++) {
            /*if (oldLevel >= level) {
                first.nexts[i] = removeValue.nexts[i];
            } else {
                pres[i].nexts[i] = removeValue.nexts[i];
            }*/
            pres[i].nexts[i] = removeNode.nexts[i];
        }
        //更新level
        int newLevel = level;
        while (--newLevel >= 0 && first.nexts[newLevel] == null) {
            level = newLevel;
        }
        size--;
        return removeNode.value;
    }

    private int randomLevel() {
        int level = 1;
        while (level < MAX_LEVEL && Math.random() < P) {
            level++;
        }
        return level;
    }

    public V get(K key) {
        keyCheck(key);
        Node<K, V> node = first;
        //从顶层开始出发寻找
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            //一旦当前key大于当前节点的下一个，则继续遍历
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            //表明找到指定的元素
            if (cmp == 0) {
                return node.nexts[i].value;
            }
        }
        //如果到了这里，表明没有找到
        return null;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V>[] nexts;

        public Node() {
        }

        public Node(K k, V v) {
            key = k;
            value = v;
        }

        public Node(K k, V v, int level) {
            key = k;
            value = v;
            nexts = new Node[level];
        }

        @Override
        public String toString() {
            return key + ":" + value + "_" + nexts.length;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("一共" + level + "层").append("\n");
        for (int i = level - 1; i >= 0; i--) {
            Node<K, V> node = first;
            while (node.nexts[i] != null) {
                sb.append(node.nexts[i]);
                sb.append(" ");
                node = node.nexts[i];
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
