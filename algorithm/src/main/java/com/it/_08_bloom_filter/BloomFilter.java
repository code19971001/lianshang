package com.it._08_bloom_filter;

/**
 * @author : code1997
 * @date : 2021/4/27 23:22
 */
public class BloomFilter<V> {

    /**
     * 二进制向量的长度(多少个二进制位)
     */
    private int bitSize;
    /**
     * 二进制向量
     */
    private long[] bits;

    /**
     * hash函数的数量
     */
    private int hashSize;

    /**
     * @param n : 数据规模
     * @param p ： 误判率
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("传入参数有误");
        }
        double ln2 = Math.log(2);
        //求位长
        this.bitSize = (int) (-(n * Math.log(p)) / Math.pow(ln2, 2));
        //求hashSize
        this.hashSize = (int) (bitSize * ln2 / n);
        //this.bits= new long[bitSize%Long.SIZE==0?bitSize/Long.SIZE:bitSize/Long.SIZE+1];
        this.bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];

    }

    /**
     * 存元素到布隆过滤器。
     *
     * @param value : value
     * @return ： whether put success;
     */
    public boolean put(V value) {
        nullCheck(value);
        //根据hash值获取索引：
        int code1 = value.hashCode();
        int code2 = code1 >>> 16;
        boolean result = false;
        //利用多个hash函数生成索引
        for (int i = 1; i <= hashSize; i++) {
            int combinedHash = code1 + (i * code2);
            if (combinedHash < 0) {
                //保证正数
                combinedHash = ~combinedHash;
            }
            int index = combinedHash % bitSize;
            //设置index位置的二进制位
            if (setBit(index)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 设置index：：
     * long[] : 0->1->2->3->4
     * long : 4<-3<-2<-1<-0
     *
     * @param index ：设置index
     */
    private boolean setBit(int index) {
        //int w = (index + Long.SIZE - 1) / Long.SIZE;
        //bits[w - 1] = bits[w - 1] | 1L << (index - (w - 1) * Long.SIZE);
        int w = index / Long.SIZE;
        long value = bits[w];
        long bitValue = 1L << (index % Long.SIZE);
        bits[w] = value | bitValue;
        //==0代表改过
        return (value & bitValue) == 0;
    }

    /**
     * 判断布隆过滤器中是否存在某元素
     *
     * @param value :value
     * @return : whether contains value
     */
    public boolean contains(V value) {
        int code1 = value.hashCode();
        int code2 = code1 >>> 16;

        //利用多个hash函数生成索引
        for (int i = 1; i <= hashSize; i++) {
            int combinedHash = code1 + (i * code2);
            if (combinedHash < 0) {
                //保证正数
                combinedHash = ~combinedHash;
            }
            int index = combinedHash % bitSize;
            //查询index位置的二进制数是否为0、
            if (getBit(index) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 查看index的二进制值
     *
     * @param index ：设置index
     */
    private int getBit(int index) {
        /*int w = (index + Long.SIZE - 1) / Long.SIZE;
        long bit = bits[w - 1];
        return (int) ((bit >> (index - (w - 1) * Long.SIZE)) % 10);*/
        int w = index / Long.SIZE;
        return (bits[w] & (1L << (index % Long.SIZE))) == 0 ? 0 : 1;
    }

    private void nullCheck(V value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
    }
}
