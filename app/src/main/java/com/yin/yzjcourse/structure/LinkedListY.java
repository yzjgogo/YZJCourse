package com.yin.yzjcourse.structure;

public class LinkedListY<E> {
    protected transient int modCount = 0;
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;
    public LinkedListY() {
    }

    public int size() {
        return size;
    }

    /**
     * 添加一个元素
     * @param e
     * @return
     */
    public boolean add(E e) {
        //添加元素其实就是往后面追加元素
        linkLast(e);
        return true;
    }

    /**
     * 在指定位置加入元素，可以在末尾，也可以在任意位置，原位置的元素需要后羿
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        checkPositionIndex(index);

        //在末尾添加元素
        if (index == size)
            linkLast(element);
        else
            //在指定位置添加元素
            linkBefore(element, node(index));
    }

    /**
     * 移除指定位置的节点
     * @param index
     * @return
     */
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    /**
     * 根据元素移除节点
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 修改指定节点位置的元素
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    /**
     * 画图知意-指针指向改变而已
     * 移除一个节点
     * @param x
     * @return
     */
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * 画图知意-指针指向改变而已
     * 在指定位置添加元素
     * @param e 要添加的元素值
     * @param succ 原位置的节点
     */
    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        //新添加的节点的prev是原位置的节点succ的prev,原位置的节点succ成为新节点newNode的next
        final Node<E> newNode = new Node<>(pred, e, succ);
        //原位置的节点的prev指向新节点newNode
        succ.prev = newNode;
        //如果原位置的节点的原prev节点是空的，说明原节点是first，则新增的newNode就成为新的first
        if (pred == null)
            first = newNode;
        else
            //否则，原位置的节点的原prev节点的next指向新节点newNode
            pred.next = newNode;
        size++;
        modCount++;
    }

    /**
     * 获取指定位置的元素
     * @param index
     * @return
     */
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * 取size的一般分前后两头遍历，提高效率
     * @param index 从0开始
     * @return
     */
    Node<E> node(int index) {
        //遍历前半部分
        if (index < (size >> 1)) {
            //链表的查找智能从第一个节点或最后一个节点一个个查找，直到找到需要的，可见查找效率很低
            Node<E> x = first;//第0次找到first，从first开始逐个找
            //每for一次就找一个节点，i=0,找到第1个(for之前找到第0个了)，i=1找到第2个，i=(index-1)找到第index个！
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            //遍历后半部分，从最后一个开始找用prev往前找，找到第index个即找到
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 画图知意-指针指向改变而已
     * @param e
     */
    void linkLast(E e) {
        final Node<E> l = last;
        //创建新增的节点，该节点的上一个节点就是原来的last节点，下一个节点目前是null，因为它将会成为新的last节点
        final Node<E> newNode = new Node<>(l, e, null);
        //newNode成为新的last节点
        last = newNode;
        //原来没有last，说明原来没有节点，则将newNode作为first节点，又因为上一行把newNode赋值给了last
        // 所以，newNode此时既是first节点，又是last节点，可见newNode的prev也是null,next也是null
        if (l == null)
            first = newNode;
        else
            //如果原来有last节点，则把原来的last节点的next指向新的last,newNode
            l.next = newNode;
        size++;
        modCount++;
    }
















    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
