package com.it;

import java.util.Random;

/**
 * @author : code1997
 * @date : 2021/9/5 14:58
 */
public class _470_Rand10 {
    public int rand10() {
        int a = rand7();
        int b = rand7();
        if (a > 4 && b < 4) {
            return rand10();
        } else {
            return (a + b) % 10 + 1;
        }
    }

    private int rand7() {
        //求[1,7]的等概函数
        return new Random().nextInt(7) + 1;
    }
}
