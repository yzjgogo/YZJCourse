package com.yin.yzjcourse.structure;

import java.util.Arrays;

public class ArrayListY<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    /**ArrayListY的元素就是存储在elementData数组里的*/
    transient Object[] elementData;
    /**是ArrayListY持有的元素数量，也是elementData数组的前面所有的非null元素的数量；不是elementData的数组的长度*/
    private int size;
    protected transient int modCount = 0;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public ArrayListY() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] getElementData() {
        return elementData;
    }

    /**
     * 向ArrayListY的尾部添加
     * @param e
     * @return
     */
    public boolean add(E e) {
        //1，先确保容量足够
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //2，添加一个元素
        //因为size++,是先用size，即先让elementData[size] = e，然后size = size+1，让ArrayListY的长度+1
        elementData[size++] = e;
        return true;
    }

    /**
     * 在指定索引位置插入元素
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        //1，判断位置要插入的位置是否有效；只能在已有位置处添加
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        //2，确保插入后存放元素的数组容量足够
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //3，从存放元素的数组elementData，从index开始都往后挪一步，这步操作之后elementData[index] == elementData[index+1]，由这步也可见插入效率很低，后面的所有元素位置都改变了
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        //4，解决第3步中elementData[index] == elementData[index+1]的问题，重新覆盖elementData[index]的值，也就实现了成功插入；
        elementData[index] = element;
        //5，插入成功后，长度增加
        size++;
    }
    public E remove(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        modCount++;
        E oldValue = (E) elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    /**
     * 添加元素时，确保容量充足
     * @param minCapacity
     */
    private void ensureCapacityInternal(int minCapacity) {
        //ArrayListY()里elementData就指向DEFAULTCAPACITY_EMPTY_ELEMENTDATA
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            //当minCapacity<DEFAULT_CAPACITY,这里其实就初始化了size为DEFAULT_CAPACITY，elementData默认长度是DEFAULT_CAPACITY
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    /**
     * 计算到底需要多大容量
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        //如果容量不够了
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    /**
     * 根据需要的容量去扩容
     * @param minCapacity 当前需要的容量
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);//临时打算扩到的容量
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;//如果还不够，以当前需要的容量为准
        if (newCapacity - MAX_ARRAY_SIZE > 0)//如果到极限了，就给一个最大的数组
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        //因为java中，数组一旦容量确定就不可更改了，所以通过copy实现一个新的容量更大的数组来替换原来的数组
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
}
