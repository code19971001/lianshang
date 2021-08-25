package com.it._03_linkedlist;

/**
 * @author code1997
 */
public interface List<E> {

    int ELEMENT_NOT_FOUND = -1;

    /**
     * 清空集合。
     */
    void clear();

    /**
     * 返回集合中元素的数量。
     * @return ：返回元素的数量
     */
    int size();

    /**
     * 判断集合是否为空
     * @return ：true为空，false为非空
     */
    boolean isEmpty();

    /**
     * 判断是否包含某个元素
     * @param element ：包含的元素
     * @return ：true为包含，false为不包含
     */
    boolean contains(E element);

    /**
     * 添加某个元素
     * @param element ：待添加的元素
     */
    void add(E element);

    /**
     * 获取指定的下标处的元素
     * @param index ：下标值
     * @return ：返回的元素
     */
    E get(int index);

    /**
     * 修改某索引处的元素
     * @param index ：指定索引值
     * @param element ：需要替换的元素
     * @return ：返回被替换前的值
     */
    E set(int index, E element);

    /**
     * 指定索引处添加元素
     * @param index :指定的索引处
     * @param element :需要添加的元素
     */
    void add(int index, E element);

    /**
     * 移除某索引位置的值
     * @param index ：索引值
     * @return ：被移除的元素
     */
    E remove(int index);

    /**
     * 获取某元素的索引值
     * @param element ：需要被查找的元素
     * @return ：索引值
     */
    int indexOf(E element);

}
