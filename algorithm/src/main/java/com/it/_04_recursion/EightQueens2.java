package com.it._04_recursion;

import java.util.Arrays;

/**
 * 八皇后问题：在8x8的国际象棋上摆放8个皇后，使其不能互相攻击，任意两个皇后不能处于同一行，同一列，同一斜线上。
 *
 * @author : code1997
 * @date : 2021/4/21 21:10
 */
public class EightQueens2 {
    public static void main(String[] args) {
        placeQueens(8);
    }

    static int[] queens;
    static boolean[] cols;
    static boolean[] leftTop;
    static boolean[] rightTop;
    static int ways;

    public static void placeQueens(int n) {
        if (n < 1) return;
        queens = new int[n];
        cols = new boolean[n];
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];
        place(0);
        System.out.println("总共存在" + ways + "种摆法");
    }

    public static void place(int row) {
        if (row == cols.length) {
            System.out.println(Arrays.toString(queens));
            ways++;
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (cols[col]) continue;
            if (leftTop[ltIndex(row, col)]) continue;
            if (rightTop[rtIndex(row, col)]) continue;
            queens[row] = col;
            cols[col] = true;
            //更新斜线
            leftTop[ltIndex(row, col)] = true;
            rightTop[rtIndex(row, col)] = true;
            //注意重置
            place(row + 1);
            cols[col] = false;
            leftTop[ltIndex(row, col)] = false;
            rightTop[rtIndex(row, col)] = false;
        }
    }

    private static int ltIndex(int row, int col) {
        return row - col + cols.length - 1;
    }

    private static int rtIndex(int row, int col) {
        return row + col;
    }

}
