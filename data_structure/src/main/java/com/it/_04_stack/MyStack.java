package com.it._04_stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 栈的实现:
 *  1.继承ArrayList的方式，但是会存在多个stack不需要的接口。
 *  2.组合的方式:使stack持有list的引用。
 * @author : code1997
 * @date :2021-03-2021/3/9 22:00
 */
public class MyStack<E>{

    private List<E> list=new ArrayList<>();

    public void clear(){
        list.clear();
    }

    public int size(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void push(E element){
        list.add(element);
    }

    public E pop(){
        return list.remove(list.size()-1);
    }

    public E peek(){
        return list.get(list.size()-1);
    }
}
