package com.it._02_union_find.union;

/**
 * QuickUnionByRankAndPathHalving：路径减半，使路径上每隔一个节点就只指向其祖父节点。
 *
 * @author : code1997
 * @date : 2021/4/6 21:57
 */
public class QuickUnionByRankAndPathHalving extends QuickUnionByRank {

    public QuickUnionByRankAndPathHalving(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}
