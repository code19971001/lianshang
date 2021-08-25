package com.it._04_recursion;

import java.util.Arrays;

/**
 * 八皇后问题：在8x8的国际象棋上摆放8个皇后，使其不能互相攻击，任意两个皇后不能处于同一行，同一列，同一斜线上。
 *
 * @author : code1997
 * @date : 2021/4/21 21:10
 */
public class EightQueens {
    public static void main(String[] args) {
        placeQueens(8);
    }

    static int[] cols;
    static int ways;

    public static void placeQueens(int n) {
        if (n < 1) return;
        cols = new int[n];
        place(0);
        System.out.println("总共存在" + ways + "种摆法");
    }

    public static void place(int row) {
        if (row == cols.length) {
            System.out.println(Arrays.toString(cols));
            ways++;
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                //在第row行，col列拜访皇后。
                cols[row] = col;
                place(row + 1);
            }
        }
    }

    public static boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (cols[i] == col) return false;
            //if ((Math.abs(cols[i] - col) / Math.abs(i - row)) == 1) return false;
            if (Math.abs(cols[i] - col) == Math.abs(i - row)) return false;
        }
        return true;
    }

}
