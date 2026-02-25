package org.postfix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StackListTest {


    //Singly linked list
    @Test
    void testSinglyLinkedListAddRemove() {
        ListADT<Integer> list = new SinglyLinkedList<>();

        list.add(1);
        list.add(2);

        assertEquals(2, list.getLast());
        assertEquals(2, list.removeLast());
        assertEquals(1, list.removeLast());
        assertTrue(list.isEmpty());
    }

    //Doubly linked list
    @Test
    void testDoublyLinkedListAddRemove() {
        ListADT<Integer> list = new DoublyLinkedList<>();

        list.add(1);
        list.add(2);

        assertEquals(2, list.getLast());
        assertEquals(2, list.removeLast());
        assertEquals(1, list.removeLast());
        assertTrue(list.isEmpty());
    }

    //List empty exception
    @Test
    void testRemoveFromEmptyListThrows() {
        ListADT<Integer> list = new SinglyLinkedList<>();
        assertThrows(IllegalStateException.class, list::removeLast);
    }
}