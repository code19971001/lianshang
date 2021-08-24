package com.it;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 计算最长不重复子串.
 * link:https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 遍历字符串，求以当前字符串结尾的最长字串的长度。
 *
 * @author : code1997
 * @date : 2021/8/16 23:41
 */
public class _3_LongestSubStrLength {
    public static void main(String[] args) {

    }

    /**
     * 求出最长不重复字串的长度
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equalsIgnoreCase(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int maxSubstrLen = 1;
        //当前字符上一次出现的位置
        Map<Character, Integer> preIndex = new HashMap<>();
        preIndex.put(chars[0], 0);
        //i-1位置的字符结尾的最长不重复字串的开始索引，也就是最左索引。
        int li = 0;
        for (int i = 1; i < chars.length; i++) {
            //获取该字符字符上一次出现的位置,如果key不存在就返回-1
            Integer pi = preIndex.getOrDefault(chars[i], -1);
            if (li <= pi) {
                //pi不等与-1并且li<=pi
                li = pi + 1;
            }
            maxSubstrLen = Math.max(i - li + 1, maxSubstrLen);
            //更新当前字符的index
            preIndex.put(chars[i], i);
        }
        return maxSubstrLen;
    }

    /**
     * 优化：假设传入的字符串只是小写英文字母，因此我们可以将hashmap转化为26长度的数组
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || "".equalsIgnoreCase(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int maxSubstrLen = 1;
        //当前字符上一次出现的位置
        int[] preIndex = new int[26];
        preIndex[chars[0] - 'a'] = 0;
        //i-1位置的字符结尾的最长不重复字串的开始索引，也就是最左索引。
        int li = 0;
        for (int i = 1; i < chars.length; i++) {
            //获取该字符字符上一次出现的位置,如果key不存在就返回-1
            int pi = preIndex[chars[i] - 'a'];
            if (li <= pi) {
                //pi不等与-1并且li<=pi
                li = pi + 1;
            }
            maxSubstrLen = Math.max(i - li + 1, maxSubstrLen);
            //更新当前字符的index
            preIndex[chars[i] - 'a'] = i;
        }
        return maxSubstrLen;
    }

    /**
     * 优化：假设字符串只是单字节字符，因此我们可以使用ascll码值来进行对比
     */
    public static int lengthOfLongestSubstring3(String s) {
        if (s == null || "".equalsIgnoreCase(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int maxSubstrLen = 1;
        //当前字符上一次出现的位置
        int[] preIndex = new int[128];
        Arrays.fill(preIndex, -1);
        preIndex[chars[0]] = 0;
        //i-1位置的字符结尾的最长不重复字串的开始索引，也就是最左索引。
        int li = 0;
        for (int i = 1; i < chars.length; i++) {
            //获取该字符字符上一次出现的位置,如果key不存在就返回-1
            int pi = preIndex[chars[i]];
            if (li <= pi) {
                //pi不等与-1并且li<=pi
                li = pi + 1;
            }
            maxSubstrLen = Math.max(i - li + 1, maxSubstrLen);
            //更新当前字符的index
            preIndex[chars[i]] = i;
        }
        return maxSubstrLen;
    }
}
