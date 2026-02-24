package org.postfix;

public class ListFactory {

    public enum ListType {
        SINGLY,
        DOUBLY
    }

    public static <T> ListADT<T> create(ListType type) {
        switch (type) {
            case SINGLY:
                return new SinglyLinkedList<>();
            case DOUBLY:
                return new DoublyLinkedList<>();
            default:
                throw new IllegalArgumentException("Tipo de lista no soportado");
        }
    }
}