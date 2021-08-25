package com.it._02_union_find.union;

/**
 * QuickUnionByRankAndPathCompression：基于rank的优化,用来降低树的高度，但是find实现的成本比较高，
 * 使用到递归，直接将树平铺起来。
 *
 * @author : code1997
 * @date : 2021/4/6 21:57
 */
public class QuickUnionByRankAndPathCompression extends QuickUnionByRank {

    public QuickUnionByRankAndPathCompression(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        if (v != parents[v]) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
