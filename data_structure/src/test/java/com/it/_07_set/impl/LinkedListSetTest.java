package com.it._07_set.impl;

import com.it._07_set.ISet;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author : code1997
 * @date : 2021/11/17 21:50
 */
public class LinkedListSetTest {

    @Test
    public void testLinkedListSet() {
        Integer[] data = {1, 2, 3, 4, 5,4,3,2,1};
        ISet<Integer> set = new LinkedListSet<>();
        Arrays.stream(data).forEach(set::add);
        set.traversal(new ISet.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

}