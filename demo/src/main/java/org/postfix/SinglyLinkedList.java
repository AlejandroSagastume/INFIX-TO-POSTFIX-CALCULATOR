package org.postfix;

/**
 * Clase que implementa la lista encadenada simple
 */
public class SinglyLinkedList<T> extends AbstractListADT<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;

    @Override
    public void add(T item) {
        Node newNode = new Node(item);
        newNode.next = head;
        head = newNode;
        count++;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        T value = head.data;
        head = head.next;
        count--;
        return value;
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return head.data;
    }
}