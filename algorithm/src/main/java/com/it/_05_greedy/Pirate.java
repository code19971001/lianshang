package com.it._05_greedy;

import java.util.Arrays;

/**
 * 加勒比海盗
 * @author : code1997
 * @date : 2021/4/22 22:05
 */
public class Pirate {
    public static void main(String[] args) {
        int[] weight = {35,4,10,7,14,2,11};
        Arrays.sort(weight);
        int load = 30;
        for (int i = 0; i < weight.length; i++) {
            load-=weight[i];
            if (load >=0){
                System.out.println("将货物："+weight[i]+"装上海盗船！");
            }else {
                break;
            }
        }
    }
}
