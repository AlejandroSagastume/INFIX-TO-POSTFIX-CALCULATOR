package org.postfix;

public interface Stack<T> {
    // Abstract methods
    void push(T item);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
}