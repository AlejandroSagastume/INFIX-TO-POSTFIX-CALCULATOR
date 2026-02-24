package org.postfix;

public class StackList<T> extends AbstractStack<T> {

    private ListADT<T> list;

    public StackList(ListADT<T> list) {
        this.list = list;
    }

    @Override
    public void push(T item) {
        list.add(item);
    }

    @Override
    public T pop() {
        return list.removeLast();
    }

    @Override
    public T peek() {
        return list.getLast();
    }

    @Override
    public int size() {
        return list.size();
    }
}