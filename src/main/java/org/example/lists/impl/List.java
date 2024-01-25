package org.example.lists.impl;

import java.util.NoSuchElementException;

public interface List<E> {

    void clear();

    boolean insert(E object);

    boolean append(E object);

    E remove();

    void moveToStart();

    void moveToEnd();

    void prev();

    void next();

    int length();

    int currPost();

    boolean moveToPost(int index);

    boolean isAtEnd();

    E getValue();

    boolean isEmpty();
}
