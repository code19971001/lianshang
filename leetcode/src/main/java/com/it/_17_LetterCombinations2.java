package com.it;

import java.util.ArrayList;
import java.util.List;

/**
 * 对于排列组合相关的题使用DFS的方式来解决
 * 将一些成员变量转化为局部变量
 *
 * @author : code1997
 * @date : 2021/8/24 22:03
 */
public class _17_LetterCombinations2 {

    char[][] lettersArray = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};

    /**
     * 使用递归来解决。
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return list;
        }
        char[] chars = digits.toCharArray();
        char[] selectedLetter = new char[chars.length];
        dfs(0, chars, selectedLetter, list);
        return list;
    }

    /**
     * 搜索第idx层
     * 一般开头写终止条件的判断
     */
    private void dfs(int idx, char[] chars, char[] selectedLetter, List<String> list) {
        if (idx == chars.length) {
            //已经进入到了最后一层，得到了一个正确的解
            list.add(new String(selectedLetter));
            return;
        }
        char digit = chars[idx];
        //该层可以做的选择
        char[] letters = lettersArray[digit - '2'];
        //遍历进入下层
        for (char letter : letters) {
            //将进入下一层之前，先将值存储起来
            selectedLetter[idx] = letter;
            dfs(idx + 1, chars, selectedLetter, list);
        }
    }
}
