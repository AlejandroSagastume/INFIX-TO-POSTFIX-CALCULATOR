package org.postfix;

/*
 * This class provides a base implementation for Stack
*/

public abstract class AbstractStack<T> implements Stack<T> {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}