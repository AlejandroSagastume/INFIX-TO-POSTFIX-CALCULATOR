package org.postfix;
/*
 * This class provides a base implementation for ListADT
*/


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