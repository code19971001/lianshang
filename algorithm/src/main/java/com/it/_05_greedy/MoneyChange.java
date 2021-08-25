package com.it._05_greedy;

/**
 * @author : code1997
 * @date : 2021/4/22 22:14
 */
public class MoneyChange {
    public static void main(String[] args) {
        int[] money = {25, 10, 5, 1};
        int total = 41;
        int index = 0;
        int count = 0;
        while (total != 0) {
            total -= money[index];
            if (total >= 0) {
                count++;
                System.out.println("找零钱：" + money[index]);
            } else {
                total += money[index];
                index++;
            }
        }
        System.out.println("共找零硬币：" + count + "枚！");
    }
}
