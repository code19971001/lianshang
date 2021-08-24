package com.it;

import java.util.ArrayList;
import java.util.List;

/**
 * 对于排列组合相关的题使用DFS的方式来解决
 * @author : code1997
 * @date : 2021/8/24 22:03
 */
public class _17_LetterCombinations {

    public List<String> letterCombinations(String digits) {
        if (digits == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        if (digits.length() == 0){
            return list;
        }
        char[] chars = digits.toCharArray();



        return null;

    }
}
