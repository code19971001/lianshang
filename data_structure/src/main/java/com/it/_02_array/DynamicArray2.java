package com.it._02_array;

public class DynamicArray2{
    /**
     * 记录元素的数量
     */
    private int size = 0;
    /**
     * 存放元素
     */
    private int[] elements;
    /**
     * 我们要求如果传入容量小于10，就默认创建长度为10的数组
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 查找的元素不存在的事后返回该常量
     */
    private static final int ELEMENT_NOT_FOUND = -1;

    public DynamicArray2(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;

        elements =new int[capacity];
    }

    public DynamicArray2() {
        //初始化参数
        this(DEFAULT_CAPACITY);
    }

    /**
     * 返回集合中元素的个数
     * @return ：元素的个数
     */
    public int size() {
        return size;
    }

    /**
     * 向动态数组中添加元素
     *
     * @param element ：待添加的元素
     */
    public void add(int element) {
        add(size, element);
    }

    public void add(int index, int element) {
       /* if (element==null){
            return;
        }*/
        //注意此处的参数判断：index和size的大小允许相等。
        rangeCheckAdd(index);
        /*for (int i = size; i >index; i--) {
            elements[i]=elements[i-1];
        }*/
        ensureCapacity(size + 1);
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        showInfo();
        size++;
    }

    /**
     * 保证要有capacity的容量足够
     *
     * @param capacity: 当前数组中元素的size+1
     */
    public void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        int[] newElements = new int[newCapacity];
        //elements=Arrays.copyOf(elements,newCapacity);
        //对数组进行复制.这里采用这种方式当数据量比较大的时候，可以采用系统级别的拷贝方式，例如System.arraycopy();
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取指定下标的元素
     *
     * @param index ：指定的下标
     * @return ：指定下标的元素
     */
    public int get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 删除指定位置处的元素
     *
     * @param index : 需要被删除的下标
     * @return ：     被删除指定位置的元素
     */
    public int remove(int index) {
        rangeCheck(index);
        int oldElement = elements[index];
        //将数组中的元素进行前移覆盖即可。
        for (int i = index + 1; i < size; i++) {
            //可以保证下标不会出现越界的情况
            elements[i - 1] = elements[i];
        }
        return oldElement;
    }

    /**
     * 设置指定位置的元素，并返回改位置的就元素
     * @param index   :指定下标
     * @param element ：替换的元素
     * @return ：被替换的元素
     */
    public int set(int index, int element) {
        rangeCheck(index);
        int old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 查看元素的索引
     *
     * @param element :查找的元素
     * @return ：返回第一次出现指定元素的下标
     */
    public int indexOf(int element) {
        for (int i = 0; i < size; i++) {
            if (element==elements[i]) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 是否包含某值
     *
     * @param element ：元素值
     * @return ：是否包含某元素
     */
    public boolean contains(int element) {
        return indexOf(element) == ELEMENT_NOT_FOUND;
    }

    /**
     * 清除集合
     */
    public void clear() {
        size = 0;
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index:" + index + ",size:" + size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            //抛出数组下标越界异常
            outOfBounds(index);
        }
    }

    private void rangeCheckAdd(int index) {
        if (index < 0 || index > size) {
            //抛出数组下标越界异常
            outOfBounds(index);
        }
    }

    public void showInfo() {
        System.out.println(elements.length);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",[");
        for (int i = 0; i < size - 1; i++) {
            builder.append(elements[i] + ",");
        }
        if (size>0){
            builder.append(elements[size - 1] + "]");
        }else {
            builder.append("]");
        }
        return builder.toString();
    }

    //对象死亡之后的回调函数
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("person - finalized");
    }
}


