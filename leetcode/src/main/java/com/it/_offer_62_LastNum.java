package com.it;

/**
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * <p>
 * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
 *
 * @author : code1997
 * @date : 2021/8/17 1:30
 */
public class _offer_62_LastNum {

    /**
     * 公式：f(n,m)=(f(n-1,m)+m)%m
     */
    public int lastRemaining(int n, int m) {
        //只有一个人那么编号肯定是0
        return n == 1 ? 0 : (lastRemaining(n - 1, m) + m) % n;
    }

    /**
     * 公式推导：如果编号是从其他的开始，比如1，那么我们只需要将最终结果加上该值即可。
     */
    public int lastRemaining2(int n, int m) {
        //只有一个人那么编号肯定是0
        int pre = 0;
        //i是数据规模
        for (int i = 2; i <= n; i++) {
            pre = (pre + m) % i;
        }
        return pre;
    }

    public static void main(String[] args) {
        System.out.println(new _offer_62_LastNum().lastRemaining(5, 3));
    }

}
