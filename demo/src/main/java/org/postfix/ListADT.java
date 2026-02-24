package org.postfix;

public interface ListADT<T> {

    void add(T item);
    T removeLast();
    T getLast();
    boolean isEmpty();
    int size();
}