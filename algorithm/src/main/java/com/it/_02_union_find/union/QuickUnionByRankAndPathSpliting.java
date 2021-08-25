package com.it._02_union_find.union;

/**
 * QuickUnionByRankAndPathSpliting：路径分裂，使路径上的每个节点都指向其祖父节点。
 *
 * @author : code1997
 * @date : 2021/4/6 21:57
 */
public class QuickUnionByRankAndPathSpliting extends QuickUnionByRank {

    public QuickUnionByRankAndPathSpliting(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            int p = parents[v];
            parents[v] = parents[parents[v]];
            v = p;
        }
        return v;
    }
}
