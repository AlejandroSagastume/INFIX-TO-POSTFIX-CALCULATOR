package org.postfix;

// JUnit imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;



public class StackImplementationsTest {

    //StackArrayList
    @Test
    void testStackArrayListPushAndPop() {
        Stack<Integer> stack = new StackArrayList<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    //Stack Vector
    @Test
    void testStackVectorPushAndPop() {
        Stack<Integer> stack = new StackVector<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    //Stack LinkedList
    @Test
    void testStackLinkedListPushAndPop() {
        ListADT<Integer> stack = new SinglyLinkedList<>();
        Stack<Integer> stakk = new StackList<>(stack);
        stakk.push(1);
        stakk.push(2);
        assertEquals(2, stakk.pop());
        assertEquals(1, stakk.pop());
        assertTrue(stakk.isEmpty());
    }

    //Stack doubly linked list
    @Test
    void testStackDoublyLinkedListPushAndPop() {
        ListADT<Integer> stack = new DoublyLinkedList<>();
        Stack<Integer> stakk = new StackList<>(stack);
        stakk.push(1);
        stakk.push(2);
        assertEquals(2, stakk.pop());
        assertEquals(1, stakk.pop());
        assertTrue(stakk.isEmpty());
    }

    //Stack empty exception
    @Test
    void testPopEmptyStackThrows() {
        Stack<Integer> stack = new StackArrayList<>();
        assertThrows(IllegalStateException.class, stack::pop);
    }


    
}
