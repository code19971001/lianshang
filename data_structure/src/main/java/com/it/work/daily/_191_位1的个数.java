package com.it.work.daily;

/**
 * @author : code1997
 * @date :2021-03-2021/3/22 22:23
 */
public class _191_位1的个数 {
    public static void main(String[] args) {
        hammingWeight(00000000000000000000000000001011);
    }

    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        int count=0;
        for (int i = 0; i < 32; i++) {
            if ((n&(1<<i))!=0){
                count++;
            }
        }
        return count;
    }

}
