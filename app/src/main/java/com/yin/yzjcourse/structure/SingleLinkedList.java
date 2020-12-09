package com.yin.yzjcourse.structure;

public class SingleLinkedList<E> {
    transient int size = 0;
    transient Node<E> first;

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (first == null) {
            first = newNode;
            size++;
            return;
        }
        newNode.next = first;
        first = newNode;
        size++;
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (first == null) {
            first = newNode;
            size++;
            return;
        }
        Node<E> curNode = first;
        while (curNode.next != null) {
            curNode = curNode.next;
        }
        curNode.next = newNode;
        size++;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<E> newNode = new Node<>(element, null);
            Node<E> curNode = first;
            Node<E> preNode = null;
            for (int i = 1; i <= index; i++) {
                preNode = curNode;
                curNode = curNode.next;
                if (i == index) {
                    preNode.next = newNode;
                    newNode.next = curNode;
                    size++;
                    return;
                }
            }
        }
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public E remove(int index) {
        checkElementIndex(index);
        Node<E> curNode = first;
        Node<E> preNode = null;
        for (int i = 1; i <= index; i++) {
            preNode = curNode;
            curNode = curNode.next;
        }
        if (preNode == null) {
            E result = first.item;
            first = first.next;
            size--;
            return result;
        }else {
            E result = curNode.item;
            preNode.next = curNode.next;
            curNode.next = null;
            size--;
            return result;
        }
    }


    public boolean remove(Object o) {
        if (size == 0) {
            return false;
        }
        Node<E> curNode = first;
        Node<E> preNode = null;
        if (first.item.equals(o)) {
            Node<E> nodeFirst = first;
            first = first.next;
            nodeFirst.next = null;
            size--;
            return true;
        }else {
            for (int i = 1; i < size; i++) {
                preNode = curNode;
                curNode = curNode.next;
                if (curNode.item.equals(o)) {
                    preNode.next = curNode.next;
                    curNode.next = null;
                    size--;
                    return true;
                }
            }
        }
        return false;
    }
    public E set(int index, E element) {
        Node<E> node = getNode(index);
        E result = node.item;
        node.item = element;
        return result;
    }

    public E get(int index) {
        checkElementIndex(index);
        Node<E> node = first;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> node = first;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }
        return node;
    }

    public int size() {
        return size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index--: " + index + ", Size--: " + size;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
