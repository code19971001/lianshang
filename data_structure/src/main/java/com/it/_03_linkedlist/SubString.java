package com.it._03_linkedlist;

/**
 * @author : code1997
 * @date :2020-12-2020/12/1 16:48
 */
public class SubString {
    public static void main(String[] args) {
        String str="NFV-RP-HZZZ-01A-PIM-PIM-ER-01-PIM-ER-R";
        String s = subStr(str);
        System.out.println(str);
        System.out.println(s);
    }

    public static String subStr(String str){
        while (str.contains("PIM-ER")){
            str = str.replace("PIM-ER", "ER");
        }
        return str;
    }
}
