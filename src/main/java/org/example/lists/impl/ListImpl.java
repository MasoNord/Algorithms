package org.example.lists.impl;

import java.util.NoSuchElementException;

public class ListImpl<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private Node<E> curr;
    private int size;

    class Node<E> {
        private E value;
        private Node<E> next;

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

        Node(Node<E> next) {
            this.next = next;
        }

        E getElement() {
            return value;
        }

        E setElement(E value) {
            return this.value = value;
        }

        Node<E> getNext() {
            return next;
        }

        Node<E> setNext(Node<E> next) {
            return this.next = next;
        }
    }

    ListImpl(int size) { }

    ListImpl() { clear(); }

    public void clear() {
        curr = tail = new Node(null);
        head = new Node(tail);
        size = 0;
    }


    public boolean insert(E object) {
        curr.setNext(new Node(curr.getElement(), curr.getNext()));
        curr.setElement(object);
        if (tail == curr) {
            tail = curr.getNext();
        }
        size++;
        return true;
    }

    @Override
    public boolean append(E object) {
        tail.setNext(new Node<E>(null));
        tail.setElement(object);
        tail = tail.getNext();
        size++;
        return true;
    }

    @Override
    public E remove() throws NoSuchElementException{
        if (curr == tail) {
            throw new NoSuchElementException("Nothing to remove");
        }

        E currElement = curr.getElement();
        curr.setElement(curr.getNext().getElement());
        if (curr.getNext() == tail) {
            tail = curr;
        }

        curr.setNext(curr.getNext().getNext());
        size--;
        return currElement;
    }

    @Override
    public void moveToStart() {
        curr = head.getNext();
    }

    @Override
    public void moveToEnd() {
        curr = tail;
    }

    @Override
    public void prev() {
        if (head.getNext() == curr) {
            return;
        }

        Node<E> temp = head;
        while (temp.getNext() != curr) {
            temp = temp.getNext();
        }

        curr = temp;
    }

    @Override
    public void next() {
        if (curr != tail) {
            curr = curr.getNext();
        }
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public int currPost() {
        Node<E> temp = head.getNext();

        int result;
        for (result = 0; curr != temp; result++) {
            temp = temp.getNext();
        }

        return result;
    }

    @Override
    public boolean moveToPost(int index) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("Wrong index was given");
        }
        curr = head.getNext();
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }

        return true;
    }

    @Override
    public boolean isAtEnd() {
        return curr == tail;
    }

    @Override
    public E getValue() throws NoSuchElementException {
        if (curr == tail) {
            throw new NoSuchElementException("There is no valid elements");
        }
        return curr.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}