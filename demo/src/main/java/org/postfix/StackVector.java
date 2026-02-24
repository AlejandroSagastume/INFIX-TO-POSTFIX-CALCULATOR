package org.postfix;

import java.util.Vector;

public class StackVector<T> extends AbstractStack<T> {

    private Vector<T> items = new Vector<>();

    @Override
    public void push(T item) {
        items.add(item);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return items.remove(items.size() - 1);
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return items.get(items.size() - 1);
    }

    @Override
    public int size() {
        return items.size();
    }
}