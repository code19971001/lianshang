package com.it._01_sort;

import com.it.bean.Student;
import com.it.compare.ShellSort;
import com.it.sort.CountingSort;
import com.it.sort.RadixSort;

import java.text.DecimalFormat;

/**
 * @author : code1997
 * @date :2021-03-2021/3/27 19:43
 */
public abstract class Sort<E extends Comparable<E>> implements Comparable<Sort<E>> {

    protected E[] array;
    private int cmpCount;
    private int swapCount;
    private long time;
    private final DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(E[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        this.array = array;
        long begin = System.currentTimeMillis();
        sort();
        long end = System.currentTimeMillis();
        this.time = end - begin;
    }

    /**
     * 排序的具体逻辑
     */
    protected abstract void sort();


    /**
     * 返回0，代表i1 == i2
     * 返回正数，代表i1 > i2
     * 返回负数，代表i1 < i2
     */
    protected int cmp(int i1, int i2) {
        cmpCount++;
        return array[i1].compareTo(array[i2]);
    }

    protected int cmpElement(E e1, E e2) {
        cmpCount++;
        return e1.compareTo(e2);
    }

    protected void swap(int i1, int i2) {
        swapCount++;
        E temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    private String numberString(int number) {
        if (number < 10000) {
            return "" + number;
        }
        if (number < 100000000) {
            return fmt.format(number / 10000.0) + "万";
        }
        return fmt.format(number / 100000000.0) + "亿";
    }

    /**
     * 判断排序算法是否是稳定的。
     */
    private boolean isStable() {
        if (this instanceof CountingSort) {
            return true;
        }
        if (this instanceof RadixSort) {
            return true;
        }
        if (this instanceof ShellSort) {
            return false;
        }
        Student[] students = new Student[20];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(i * 10, 10);
        }
        sort((E[]) students);
        //如果算法是稳定的，那么student的学生顺序是不会发生变化的。
        for (int i = 1; i < students.length; i++) {
            if ((students[i].getScore() != students[i - 1].getScore() + 10)) {
                return false;
            }

        }
        return true;
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
        String stableStr = "稳定性：" + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
                + stableStr + "\t"
                + timeStr + " \t"
                + compareCountStr + " \t"
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";
    }

    @Override
    public int compareTo(Sort o) {
        int result = (int) (this.time - o.time);
        if (result != 0) {
            return result;
        }
        result = this.cmpCount - o.cmpCount;
        if (result != 0) {
            return result;
        }
        return this.swapCount - o.swapCount;
    }

}
