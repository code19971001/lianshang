package com.it._08_bloom_filter;

/**
 * @author : code1997
 * @date : 2021/4/27 23:40
 */
public class BloomFilterTest {
    public static void main(String[] args) {
        BloomFilter<Integer> filter = new BloomFilter<>(1000000, 0.01);
        for (int i = 1; i <= 1000000; i++) {
            filter.put(i);
        }
        int count = 0;
        for (int i = 1; i <= 2000000; i++) {
            if (filter.contains(i)) {
                count++;
            }
        }
        System.out.println("误判的个数：" + count);
    }
}
