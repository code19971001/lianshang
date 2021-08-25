package com.it._02_union_find.union;

import java.util.Arrays;

/**
 * 基于size的优化：根据树的子节点的数量来衡量。
 * @author : code1997
 * @date : 2021/4/6 21:57
 */
public class QuickUnionBySize extends UnionFind {

    private int[] sizes;

    public QuickUnionBySize(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        Arrays.fill(sizes, 1);
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
     * v1和v2的parent的size小的嫁接到大的上面。
     */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return;
        }
        if (sizes[p1] <= sizes[p2]) {
            parents[p1] = p2;
            sizes[p2] += sizes[p1];
        } else {
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        }


    }

}
