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

    /**
     * 移除指定位置的元素
     * @param index
     * @return
     */
    public E remove(int index) {
        //1，判断要移除的位置是否有效；
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        modCount++;
        //2，获取要移除的元素，用于最后return
        E oldValue = (E) elementData[index];

        //3，计算出数组copy时，一共要copy一个元素，至于size-index-1,自己用笔写写就知道了，
        int numMoved = size - index - 1;
        //4，如果有要copy的元素，则执行copy，从被移除的元素后一个元素开始，每一个元素都copy到前一个位置（所有的元素都向前移一个位置），
        //最终的结果element[size-1]仍然有值，就是最后一个元素的值，此时element[size-2] == element[size-1]
        if (numMoved > 0)//课件效率很低
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        //5，移除成功后ArrayListY的长度size减1，实际存放元素的数组elementData的对应位置要置空，因为第4步element[size-2] == element[size-1]
        elementData[--size] = null; // clear to let GC do its work

        //6，返回被删除的元素值
        return oldValue;
    }

    /**
     * 删除指定的元素
     * @param o 具体要删除的元素
     * @return 是否删除找到并删除成功
     */
    public boolean remove(Object o) {
        if (o == null) {
            //1，如果要删除的元素时null
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            //2，如果要删除的元素不是null
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    /**
     * 修改某个位置的元素
     * 课件效率很高
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        //1，判断位置index是有有效；
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        //2，获取该位置原来的元素，用于最后的return
        E oldValue = (E) elementData[index];
        //3，更新数组对应位置的元素
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 获取指定位置的元素
     * @param index
     * @return
     */
    public E get(int index) {
        //1，判断位置是否有效
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        //2，其实就是找到对应数组位置的元素，课件访问效率很高
        return (E) elementData[index];
    }

    /**
     * 将线性表作为数组返回
     * 1：返回的是新的数组，不是原数组，防止数据被修改；
     * 2：返回的数组长度，不应该是原数组的长度，而应该是线性表的实际长度size；
     * @return
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * @see #remove(int)
     * 只做删除操作，不返回删除的元素，与remove(int)一样
     * @param index
     */
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)//可见效率很低
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work
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
