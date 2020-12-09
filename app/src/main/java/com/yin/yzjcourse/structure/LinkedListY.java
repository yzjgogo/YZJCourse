package com.yin.yzjcourse.structure;

public class LinkedListY<E> {
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;
    public LinkedListY() {
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
