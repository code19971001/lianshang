package com.it._07_set.impl;

import com.it._07_set.ISet;
import com.it._08_map.IMap;
import com.it._08_map.TreeMap;

/**
 * 使用TreeMap来存放TreeSet
 *
 * @author : code1997
 * @date : 2021/11/22 22:30
 */
public class TreeSet<K> implements ISet<K> {

    private TreeMap<K, Object> map = new TreeMap<>();

    private static final Object object = new Object();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(K element) {
        return map.containsKey(element);
    }

    @Override
    public void add(K element) {
        map.put(element, object);
    }

    @Override
    public void remove(K element) {
        map.remove(element);
    }

    @Override
    public void traversal(Visitor<K> visitor) {
        map.traversal(new IMap.Visitor<K, Object>() {
            @Override
            public boolean visit(K key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
