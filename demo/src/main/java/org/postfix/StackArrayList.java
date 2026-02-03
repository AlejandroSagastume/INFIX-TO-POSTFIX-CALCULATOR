package org.postfix;

import java.util.ArrayList;

public class StackArrayList<T> implements Stack<T> {
    private ArrayList<T> items;
    private int top = 0;
    
    public StackArrayList() {
        this.items = new ArrayList<T>();
    }
/**
* Method to push an item onto the stack.
* @param    item The item to be pushed onto the stack.
* @return   void
* @throws   None
*/
    @Override
    public void push(T item) {
        items.add(item);
        top++;
    }
/**
* Method to remove and return the top item from the stack.
* @param    None
* @return   The item removed from the top of the stack.
* @throws   IllegalStateException If the stack is empty.
*/
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        top--;
        T item = items.get(top);
        items.remove(top);
        return item;
    }
/**
    Method to return the top item from the stack without removing it.
    @param None
    @return The item at the top of the stack.
    @throws IllegalStateException If the stack is empty.
*/
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return items.get(top - 1);
    }

/**
* Method to check if the stack is empty.
* @param    None
* @return   true if the stack is empty, false otherwise.
* @throws   None
*/
    public boolean isEmpty() {
        return top == 0;
    }
/**
* Method to return the number of items in the stack.
* @param    None
* @return   The number of items in the stack.
* @throws   None
*/
    public int size() {
        return top;
    }
}