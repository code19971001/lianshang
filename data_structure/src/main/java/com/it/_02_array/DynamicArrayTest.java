package com.it._02_array;

import com.it._02_array.bean.Person;
import org.junit.Test;

/**
 * @author 龍
 */
public class DynamicArrayTest {

    @Test
    public void testDynamicArray() throws InterruptedException {
        DynamicArray<Person> dynamicArray = new DynamicArray<>();
        dynamicArray.add(new Person(12, "张三"));
        dynamicArray.add(null);
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.add(new Person(13, "张三"));
        dynamicArray.remove(10);
        dynamicArray.remove(9);
        dynamicArray.remove(8);
        System.gc();
        Thread.sleep(10000);
        System.out.println(dynamicArray.toString());
        System.out.println(dynamicArray.indexOf(null));
    }
}
