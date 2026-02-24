package org.postfix;

public abstract class AbstractListADT<T> implements ListADT<T> {

    protected int count = 0;

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }
}