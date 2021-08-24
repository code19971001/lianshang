package com.it;

/**
 * 给定一个字符串，逐个翻转字符串中的每一个单词。
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 * 实现步骤：
 * 1、消除字符串中的多余空格。
 * 2、将去除空格的字符串整体进行反转。
 * 3、对每一个单词进行反转。
 *
 * @author : code1997
 * @date :2021-03-2021/3/25 21:54
 */
public class _151_FlipWord {
    public static void main(String[] args) {
        String word = "are you ok ";
        System.out.println(reverseWords(word));
    }

    public static String reverseWords(String s) {
        if (s == null) {
            return s;
        }
        //1.消除字符串中的空格
        char[] chars = s.toCharArray();
        //当前待存放字符的index
        int cur = 0;
        //标注前一个字符是否是空格字符
        boolean space = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                //分空格字符
                chars[cur++] = chars[i];
                space = false;
            } else if (!space) {
                //当前字符是空格，但是前一个字符是非空格
                chars[cur++] = chars[i];
                space = true;
            }
        }
        //如果前一个字符为空格，则cur的长度等于当前长度-1，否则，cur长度等于当前长度
        int len = space ? cur - 1 : cur;
        //如果字符串全是空的，则没有必要进行反转
        if (len <= 0) {
            return "";
        }
        //2.对整个字符串进行反转操作,[left,right)
        reverse(chars, 0, len);
        //3.对于每一个单词进行逆序:也可以称之为是一个哨兵。
        int prevSpaceIndex = -1;
        for (int i = 0; i < len; i++) {
            if (chars[i] == ' ') {
                //如果当前字符是空格，需要[preSpaceIndex+1,curIndex)进行反转
                reverse(chars, prevSpaceIndex + 1, i);
                prevSpaceIndex = i;
            }
        }
        //还存在最后一个单词需要进行反转
        reverse(chars, prevSpaceIndex + 1, len);
        return new String(chars, 0, len);
    }

    /**
     * 对传入的字符数组[left,right)范围内的数据进行反转
     */
    private static void reverse(char[] chars, int left, int right) {
        right--;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

}
