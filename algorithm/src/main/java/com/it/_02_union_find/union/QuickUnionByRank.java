package com.it._02_union_find.union;

import java.util.Arrays;

/**
 * Quick Union：基于rank的优化，rank的依据使节点的高度。
 *
 * @author : code1997
 * @date : 2021/4/6 21:57
 */
public class QuickUnionByRank extends UnionFind {

    private int[] ranks;

    public QuickUnionByRank(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        Arrays.fill(ranks, 1);
    }

    /**
     * 时间复杂度：O(logn)，实际上为树的高度。
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }

    /**
     * v1和v2的parent的rank小的嫁接到大的上面。
     */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return;
        }
        if (ranks[p1] < ranks[p2]) {
            parents[p1] = p2;
        } else if (ranks[p1] > ranks[p2]) {
            parents[p2] = p1;
        } else {
            //左边嫁接到右边
            parents[p1] = p2;
            ranks[p2] += 1;
        }


    }

}
