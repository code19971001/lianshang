package com.it._02_union_find.union;

/**
 * quick find:的实现，使用一个数组直接存储根节点，也就是集合。
 * 树的高度最高为2.
 *
 * @author : code1997
 * @date : 2021/4/6 21:26
 */
public class QuickFind extends UnionFind {

    public QuickFind(int capacity) {
        super(capacity);
    }


    @Override
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }


    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return;
        }
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }

}
