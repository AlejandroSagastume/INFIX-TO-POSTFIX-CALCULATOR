package org.postfix;

// J Unit imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    private PostfixCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = PostfixCalculator.getInstance();
    }

    @Test
    public void testAddition() {
        assertEquals(7.0, calculator.calculate("3 4 +"));
    }

    @Test
    public void testSubtraction() {
        assertEquals(5.0, calculator.calculate("10 5 -"));
    }

    @Test
    public void testMultiplication() {
        assertEquals(24.0, calculator.calculate("6 4 *"));
    }

    @Test
    public void testDivision() {
        assertEquals(3.0, calculator.calculate("15 5 /"));
    }

    @Test
    public void testComplexExpression() {
        assertEquals(14.0, calculator.calculate("5 1 2 + 4 * + 3 -"));
    }

    @Test
    public void testDivisionByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calculate("10 0 /");
        });
        assertEquals("División entre cero", exception.getMessage());
    }

    @Test
    public void testInvalidCharacter() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate("2 a +");
        });
        assertEquals("Carácter inválido: a", exception.getMessage());
    }

    @Test
    public void testInsufficientOperands() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate("5 +");
        });
        assertEquals("Operandos insuficientes", exception.getMessage());
    }

    @Test
    public void testInvalidExpression() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate("2 3 + 4");
        });
        assertEquals("Expresión inválida", exception.getMessage());
    }
}

