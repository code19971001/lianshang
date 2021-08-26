package com.it;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 有效括号组合需满足：左括号必须以正确的顺序闭合。
 *
 * @author : code1997
 * @date : 2021/8/25 16:52
 */
public class _22_GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n < 0) {
            return list;
        }
        if (n == 0) {
            list.add("");
            return list;
        }
        dfs(0, n, n, new char[n << 1], list);
        return list;
    }

    /**
     * 当左右括号数量相等的时候，我们只可以选择左括号.
     * 1）什么时候可以选择左括号？只要左括号的数量大于0.
     * 2）什么时候可以选择右括号？右括号的数量大于0并且左右括号的数量不一样.
     * 3）每进入一层都需要知道左右括号剩下多少
     */
    private void dfs(int idx, int leftRemain, int rightRemain, char[] string, List<String> list) {
        if (string.length == idx) {
            //判断是否合法，如何合法就添加到list中去.
            list.add(new String(string));
            return;
        }
        //枚举这一层所有可能的选择(常规写法是直接for循环即可，但是这里选择性太小，没有必要进行for循环)，然后选择一个值，进入下一层.
        if (leftRemain > 0) {
            string[idx] = '(';
            dfs(idx + 1, leftRemain - 1, rightRemain, string, list);
        }
        if (rightRemain > 0 && leftRemain != rightRemain) {
            string[idx] = ')';
            dfs(idx + 1, leftRemain, rightRemain - 1, string, list);
        }
    }

}
