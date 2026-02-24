package org.postfix;

// JUnit imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InfixToPostfixTest {
    private PostfixCalculator calculator;
    private InfixToPostfix infixToPostfix;
    
    @BeforeEach
    public void setUp() {
        calculator = PostfixCalculator.getInstance();
        infixToPostfix = new InfixToPostfix();
    }
    @Test
    public void testConvertInfixToPostfix() {
        assertEquals("3 4 +", infixToPostfix.convert("3 + 4"));
        assertEquals("15 7 1 1 + - / 3 * 2 1 1 + + -", infixToPostfix.convert("15 / (7 - (1 + 1)) * 3 - (2 + (1 + 1))"));
    }

    @Test
    public void testCalculateInfixExpression() {
        assertEquals(7.0, calculator.calculate(infixToPostfix.convert("3 + 4")));
        assertEquals(5.0, calculator.calculate(infixToPostfix.convert("10 - 5")));
        assertEquals(24.0, calculator.calculate(infixToPostfix.convert("6 * 4")));
        assertEquals(3.0, calculator.calculate(infixToPostfix.convert("15 / 5")));
        assertEquals(14.0, calculator.calculate(infixToPostfix.convert("5 + ((1 + 2) * 4) - 3")));
    }

    @Test
    public void testEmptyExpression() {
        assertThrows(IllegalArgumentException.class, () -> infixToPostfix.convert(""));
    }

    @Test
    public void testUnbalancedParentheses() {
        assertThrows(IllegalArgumentException.class, () -> infixToPostfix.convert("(3 + 4"));
        assertThrows(IllegalArgumentException.class, () -> infixToPostfix.convert("3 + 4)"));
    }

    @Test
    public void testSingleNumber() {
        assertEquals("5", infixToPostfix.convert("5"));
    }

    @Test
    public void testDecimalNumbers() {
        assertEquals("3.5 4.2 +", infixToPostfix.convert("3.5 + 4.2"));
    }

    @Test
    public void testOperatorPrecedence() {
        assertEquals("3 4 + 2 *", infixToPostfix.convert("(3 + 4) * 2"));
        assertEquals("3 4 2 * +", infixToPostfix.convert("3 + 4 * 2"));
    }

    @Test
    public void testNullExpression() {
        assertThrows(IllegalArgumentException.class, () -> infixToPostfix.convert(null));
    }


}
