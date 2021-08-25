package com.it._02_array;

public class Assert {
    public static void test(boolean value){
        try {
            if (!value){
                throw new Exception("参数非法,测试失败！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
