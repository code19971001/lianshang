package com.it._02_union_find.union;

/**
 * @author : code1997
 * @date : 2021/4/6 21:45
 */
public abstract class UnionFind {


    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("args must be than 0");
        }
        parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    /**
     * 找出v所在的集合。
     */
    public abstract int find(int v);

    /**
     * 将v1所在的集合合并到v2所在的集合。
     */
    public abstract void union(int v1, int v2);

    /**
     * 判断v1,v2是否属于同一个集合。
     */
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    public void rangeCheck(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IllegalArgumentException("v out of bounds");
        }
    }


}
