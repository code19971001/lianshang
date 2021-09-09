package com.it.string;

import java.util.Arrays;

/**
 * @author : code1997
 * @date : 2021/9/9 21:34
 */
public class _242_ValidAnagram {

    /**
     * 使用两个字符数组分别存储两个字符串
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] char1 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char1[s.charAt(i) - 'a'] += 1;
        }

        int[] char2 = new int[26];
        for (int i = 0; i < t.length(); i++) {
            char2[t.charAt(i) - 'a'] += 1;
        }
        return Arrays.equals(char1, char2);
    }


    /**
     * 精简版本
     */
    public boolean isAnagram2(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] char1 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char1[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--char1[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram3(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        char[] sChar = s.toCharArray();
        char[] tChars = t.toCharArray();
        int[] char1 = new int[26];
        for (char c : sChar) {
            char1[c - 'a'] += 1;
        }
        for (char tChar : tChars) {
            if (--char1[tChar - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
