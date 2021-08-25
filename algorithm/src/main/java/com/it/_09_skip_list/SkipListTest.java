package com.it._09_skip_list;

import com.it.tools.Asserts;
import com.it.tools.Times;
import org.junit.Test;

import java.util.TreeMap;

/**
 * @author : code1997
 * @date : 2021/4/29 0:09
 */
public class SkipListTest {

    @Test
    public void testSkipList() {
        SkipList<Integer, Integer> skipList = new SkipList<>();
        test(skipList, 20, 10);
        test(skipList, 20, 5);
    }

    @Test
    public void testSkipListTime() {
        SkipList<Integer, Integer> skipList = new SkipList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int count = 100_0000;
        int delta = 10;


        Times.test("SkipList", () -> test(skipList, count, delta));

        Times.test("TreeMap", () -> test(map, count, delta));
    }

    public static void main(String[] args) {

    }

    private static void test(SkipList<Integer, Integer> list, int count, int delta) {
        for (int i = 0; i < count; i++) {
            list.put(i, i + delta);
        }
        System.out.println(list);
        for (int i = 0; i < count; i++) {
            Asserts.test(list.get(i) == i + delta);
        }
        Asserts.test(list.size == count);
        for (int i = 0; i < count; i++) {
            Asserts.test(list.remove(i) == i + delta);
        }
        Asserts.test(list.size == 0);
    }

    private static void test(TreeMap<Integer, Integer> map, int count, int delta) {
        for (int i = 0; i < count; i++) {
            map.put(i, i + delta);
        }
        for (int i = 0; i < count; i++) {
            Asserts.test(map.get(i) == i + delta);
        }
        Asserts.test(map.size() == count);
        for (int i = 0; i < count; i++) {
            Asserts.test(map.remove(i) == i + delta);
        }
        Asserts.test(map.size() == 0);
    }

}
