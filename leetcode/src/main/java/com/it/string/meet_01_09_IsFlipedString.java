package com.it.string;

/**
 * @author : code1997
 * @date : 2021/9/9 0:06
 */
public class meet_01_09_IsFlipedString {

    /**
     * s1+s1相加,然后求是否包含s2.
     */
    public boolean isFlipedString(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        s1 += s1;
        return s1.contains(s2);
    }
}
