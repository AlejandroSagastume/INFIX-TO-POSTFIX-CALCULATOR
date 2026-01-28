package org.postfix;

public interface Stack<T> {
    void push(T item);
    T pop();
    T peek();
}