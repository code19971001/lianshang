package com.it._07_set.impl;

import com.it._07_set.ISet;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author : code1997
 * @date : 2021/11/17 22:15
 */
public class RBTSetTest {

    @Test
    public void testRBTSet() {
        Integer[] data = {1, 2, 3, 4, 5, 4, 3, 2, 1};
        RBTSet<Integer> rbtSet = new RBTSet<>();
        Arrays.stream(data).forEach(rbtSet::add);
        rbtSet.traversal(new ISet.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

}