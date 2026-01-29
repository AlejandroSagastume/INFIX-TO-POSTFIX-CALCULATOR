package org.postfix;

// J Unit imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackOperation {
    private StackVector<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new StackVector<>();
    }

    @Test
    public void testPushAndPop() {
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    public void testPeek() {
        stack.push(30);
        assertEquals(30, stack.peek());
        assertEquals(30, stack.pop());
    }
    
    @Test
    public void testPopEmptyStack() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            stack.pop();
        });
        assertEquals("Stack is empty", exception.getMessage());
    }

    @Test
    public void testPeekEmptyStack() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            stack.peek();
        });
        assertEquals("Stack is empty", exception.getMessage());
    }
}