package com.it._11_trie;

import com.it.tools.Asserts;
import org.junit.Test;

/**
 * @author : code1997
 * @date : 2021/11/29 21:46
 */
public class TrieTest {

    @Test
    public void testAdd() {
        Trie<Integer> trie = new Trie();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("小码哥", 5);
        Asserts.test(trie.size() == 5);
        Asserts.test(trie.get("dog") == 2);
        Asserts.test(trie.startsWith("c"));
        Asserts.test(trie.startsWith("ca"));
        Asserts.test(trie.startsWith("cat"));
        Asserts.test(trie.startsWith("cata"));
        Asserts.test(!trie.startsWith("hehe"));
        Asserts.test(trie.get("小码哥") == 5);
        Asserts.test(trie.remove("cat") == 1);
        Asserts.test(trie.remove("catalog") == 3);
        Asserts.test(trie.remove("cast") == 4);
        Asserts.test(trie.size() == 2);
        Asserts.test(trie.startsWith("do"));
        Asserts.test(!trie.startsWith("c"));

    }

}