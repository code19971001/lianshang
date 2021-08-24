package com.it;

/**
 * @author : code1997
 * @date : 2021/8/19 21:17
 */
public class _345_ReverseVowelsOfStr {
    public static void main(String[] args) {
        System.out.println(reverseVowels("hello"));
    }

    public static String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            while (l <= s.length() - 1 && !isVowel(chars[l])) {
                l++;
            }
            while (r >= 0 && !isVowel(chars[r])) {
                r--;
            }
            //再次继续检查
            if (l < r) {//交换l，r
                char temp = chars[l];
                chars[l] = chars[r];
                chars[r] = temp;
                l++;
                r--;
            }

        }
        return new String(chars);
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
