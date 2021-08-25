package com.it._02_union_find.union;

import com.it.tools.Times;
import org.junit.Test;


/**
 * @author : code1997
 * @date : 2021/4/6 21:38
 */
public class UnionFindTest {
    private static final int max = 100000;

    @Test
    public void testQuickFind() {
        QuickFind quickFind = new QuickFind(12);
        testTrue(quickFind);
    }

    @Test
    public void testQuickUnion() {
        UnionFind quickUnion = new QuickUnion(12);
        testTrue(quickUnion);
    }

    @Test
    public void testQuickUnionBySize() {
        QuickUnionBySize quickUnionBySize = new QuickUnionBySize(12);
        testTrue(quickUnionBySize);
    }

    @Test
    public void testAllTime() {
        testTime(new QuickFind(max));
        testTime(new QuickUnion(max));
        testTime(new QuickUnionBySize(max));
        testTime(new QuickUnionByRank(max));
        testTime(new QuickUnionByRankAndPathCompression(max));
        testTime(new QuickUnionByRankAndPathSpliting(max));
        testTime(new QuickUnionByRankAndPathHalving(max));
    }


    void testTrue(UnionFind unionFind) {
        unionFind.union(0, 1);
        unionFind.union(0, 3);
        unionFind.union(0, 4);
        unionFind.union(2, 3);
        unionFind.union(2, 5);

        unionFind.union(6, 7);

        unionFind.union(8, 9);
        unionFind.union(8, 10);
        unionFind.union(9, 10);
        unionFind.union(9, 11);

        System.out.println(unionFind.isSame(0, 6));
        System.out.println(unionFind.isSame(0, 5));
        //合并4-6
        unionFind.union(4, 6);
        System.out.println(unionFind.isSame(2, 7));
    }

    static void testTime(UnionFind unionFind) {
        Times.test(unionFind.getClass().getSimpleName(), () -> {
            for (int i = 0; i < unionFind.parents.length; i++) {
                unionFind.union((int) (Math.random() * max), (int) (Math.random() * max));
            }
            for (int i = 0; i < unionFind.parents.length; i++) {
                unionFind.isSame((int) (Math.random() * max), (int) (Math.random() * max));
            }
        });


    }
}
