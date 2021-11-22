package com.it._08_map;

/**
 * @author : code1997
 * @date : 2021/11/22 20:51
 */
public interface IMap<K, V> {

    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);

    void traversal(Visitor<K, V> visitor);

    abstract class Visitor<K, V> {

        boolean stop;

        public abstract boolean visit(K key, V value);
    }
}
