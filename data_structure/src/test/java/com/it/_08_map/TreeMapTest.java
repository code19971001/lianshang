package com.it._08_map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : code1997
 * @date : 2021/11/22 22:00
 */
public class TreeMapTest {

    private TreeMap<String, Integer> treeMap = new TreeMap<>();


    @Before
    public void init() {
        treeMap.put("ccc", 16);
        treeMap.put("aaa", 18);
        treeMap.put("bbb", 17);
        treeMap.put("ddd", 15);
        treeMap.put("ddd", 99);

    }


    @Test
    public void get() {
        Assert.assertEquals(99L, treeMap.get("ddd").longValue());
    }

    @Test
    public void remove() {
        Assert.assertEquals(18L, treeMap.remove("aaa").longValue());

    }

    @After
    public void traversal() {
        treeMap.traversal(new IMap.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println("[ key = " + key + ",value = " + value + " ]");
                return false;
            }
        });
    }

}