package com.it;


/**
 * @author : code1997
 * @date : 2021/8/17 23:46
 */
public class _5_LongestPalindromicSubstr {


    /**
     * 暴力法:时间复杂法为O(n^2)
     */
    public String longestPalindrome(String s) {

        return null;
    }

    private boolean isPalindromicSubstr(String str, int left, int right) {


        return false;
    }

    /**
     * dp的方式：dp[i][j]代表s[i,j]是否为回文串，存储true,false.
     * >如果s[i,j]的长度是(j-i+1)<=2时候，如果s[i]==s[j]的时候那么他们是回文串。
     * >如果s[i,j]的长度是(j-i+1)>2时候，如果s[i+1,j-1]是回文串(代表内层)，并且s[i]==s[j],那么s[i,j]是回文串
     * 时间复杂度为O(n^2)。
     * 空间复杂度也是O(n^2)，我们也可以使用一维数组来对其进行优化。
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        char[] chars = s.toCharArray();
        int beginIndex = 0;
        //用于记录最长回文子串的长度
        int maxLength = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            for (int j = i; j < chars.length; j++) {
                /*if (j - i + 1 <= 2) {
                    dp[i][j] = chars[i] == chars[j];
                } else {
                    dp[i][j] = chars[i] == chars[j] && dp[i + 1][j - 1];
                }*/
                int curLength = j - i + 1;
                //注意判断条件的顺序
                dp[i][j] = chars[i] == chars[j] && (curLength <= 2 || dp[i + 1][j - 1]);
                //如果长度相同，则选取第一个回文串，不能进行层层覆盖
                if (dp[i][j] && maxLength < curLength) {
                    beginIndex = i;
                    maxLength = curLength;
                }
            }
        }
        return s.substring(beginIndex, beginIndex + maxLength);
    }

    /**
     * 扩展中心法：将每个字符，每个间隙向两边进行扩展。
     * 扩展中心：2n-1
     * 时间复杂度：O(n*(2n-1))，时间复杂度依旧是O(n^2)，相对与dp的优势是空间复杂度降低，为O(1)
     */
    public static String longestPalindrome3(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        //注意：toCharArray也会带来空间复杂度
        char[] chars = s.toCharArray();
        int beginIndex = 0;
        int maxLength = 1;
        //实际上最后一个字符和第一个字符就没有必要进行扩展，
        for (int i = chars.length - 2; i > 0; i--) {
            //求以某个字符或者间隙为中心的最长回文串的长度是多少。
            //以字符为中心向左右进行扩展
            int len1 = longestPalindromeLength(chars, i - 1, i + 1);
            //以间隙开始向左右开始进行扩展。
            int len2 = longestPalindromeLength(chars, i, i + 1);
            len1 = Math.max(len1, len2);
            if (len1 > maxLength) {
                //计算开始索引：注意这里对于len的处理。
                beginIndex = i - ((len1 - 1) >> 1);
                maxLength = len1;
            }

        }
        //处理0号字符右边的间隙，最大长度为2
        if (maxLength < 2 && chars[0] == chars[1]) {
            beginIndex = 0;
            maxLength = 2;
        }
        return new String(chars, beginIndex, maxLength);
    }

    private static int longestPalindromeLength(char[] chars, int leftIndex, int rightIndex) {
        while (leftIndex >= 0 && rightIndex <= chars.length - 1 && chars[leftIndex] == chars[rightIndex]) {
            leftIndex--;
            rightIndex++;
        }
        //跳出循环的时候，长度多了两位。
        return rightIndex - leftIndex - 1;
    }


    /**
     * 扩展中心法优化：算法的核心是将扩展由一个字符，间隙转化为一坨字符为核心。
     * 1）找到右边第一个不等于s[i]的字符，位置记录为r，i左边的位置记录为1
     * 2）由l开始向左，r开始向右扩展，找到最长的回文子串。
     */
    public static String longestPalindrome4(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        //注意：toCharArray也会带来空间复杂度
        char[] chars = s.toCharArray();
        int beginIndex = 0;
        int maxLength = 1;
        int i = 0;
        while (i < chars.length) {
            int r = i;
            //找到第一个不等s[i]位置的元素
            while (++r < chars.length && chars[r] == chars[i]) {

            }
            //开始向左向右扩展
            int l = i - 1;
            //r成为新的i
            i = r;
            while (l >= 0 && r <= chars.length - 1 && chars[r] == chars[l]) {
                l--;
                r++;
            }
            //代表l或者r越界或者chars[r] != chars[l],开始获取最长的回文串。注意这里的++l操作
            int curLength = r - ++l;
            if (maxLength < curLength) {
                maxLength = curLength;
                beginIndex = l;
            }

        }
        return new String(chars, beginIndex, maxLength);
    }

    /**
     * 马拉车算法
     * 头部，尾部，字符中间添加特殊字符，必须是原字符串中不包含的字符。
     * m[i]的含义：
     * 1)以cs[i]为扩展中心的最大回文子串的长度(不包含#字符)->最大回文子串在原字串中的开始索引：(i-m[i]) >> 1
     * 2)是以cs[i]为扩展中心的最大回文子串的左半部分或者右半部分的长度(包含#字符)。
     * 实际上就是需要我们求出m[]
     */
    public static String manacher(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        //对字符串进行预处理
        char[] oldCs = s.toCharArray();
        char[] cs = preprocess(oldCs);
        int[] m = new int[cs.length];
        //因为头的特殊性(前两个字符的值为^#)，实际上可以去除掉，index = 2的点根据对称性，必然为1.
        int c = 1, r = 1, lastIndex = m.length - 2;
        int maxLength = 0, index = 0;
        for (int i = 2; i < lastIndex; i++) {
            //如果i=r的时候实际上需要以i为中心来进行中心扩展。
            if (r > i) {
                //li和i以c位置中心对称
                int li = (c << 1) - i;
                if (i + m[li] <= r) {
                    //一旦i + m[i] <= r 意味着对称范围在r里面,那么我们可以直接确定m[i]的值
                    m[i] = m[li];
                } else {
                    //当i + m[i] > r 的时候，m[i]的值至少为r-i,相当于对m[i]进行初始化。
                    m[i] = r - i;
                }
            }
            //需要以i为中心进行扩展.
            while (cs[i + m[i] + 1] == cs[i - m[i] - 1]) {
                m[i]++;
            }
            //更新c,r
            if (i + m[i] > r) {
                c = i;
                r = i + m[i];
            }
            //找出最大的回文字串的值.
            if (m[i] > maxLength) {
                maxLength = m[i];
                index = i;
            }
        }
        int beginIndex = (index - maxLength) >> 1;
        return new String(oldCs, beginIndex, maxLength);
    }

    /**
     * 对马拉松算法的字符串进行前置处理
     */
    private static char[] preprocess(char[] chars) {
        //需要注意进位的优先级
        char[] cs = new char[(chars.length << 1) + 3];
        cs[0] = '^';
        cs[1] = '#';
        cs[cs.length - 1] = '$';
        for (int i = 0; i < chars.length; i++) {
            int idx = (i + 1) << 1;
            cs[idx] = chars[i];
            cs[idx + 1] = '#';
        }
        return cs;
    }

    public static void main(String[] args) {
        System.out.println(manacher("babad"));
    }


}
