package org.postfix;

public class PostfixCalculator implements Calc {
    // Attribute
    private Stack<Double> stack;
    
    // Constructor
    public PostfixCalculator() {
        this.stack = StackFactory.create();
    }

    /**
     * Calculates the result of a postfix expression.
     * @param input The postfix expression to be calculated.
     * @return The result of the calculation.
     * @throws IllegalArgumentException If the expression is invalid or cannot be calculated
     * @throws ArithmeticException If division by zero occurs
     */
    @Override
    public double calculate(String input) {
        stack = StackFactory.create();
        String[] tokens = input.trim().split("\\s+");
        
        for (String token : tokens) {
            if (token.matches("[+\\-*/]")) {
                if (getStackSize() < 2) {
                    throw new IllegalArgumentException("Operandos insuficientes");
                }
                double b = stack.pop();
                double a = stack.pop();
                double resultado;
                
                switch (token) {
                    case "+":
                        resultado = a + b;
                        break;
                    case "-":
                        resultado = a - b;
                        break;
                    case "*":
                        resultado = a * b;
                        break;
                    case "/":
                        if (b == 0) throw new ArithmeticException("División entre cero");
                        resultado = a / b;
                        break;
                    default:
                        throw new IllegalArgumentException("Operador desconocido");
                }
                stack.push(resultado);
            } else {
                try {
                    stack.push(Double.parseDouble(token));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Carácter inválido: " + token);
                }
            }
        }

        if (getStackSize() != 1) {
            throw new IllegalArgumentException("Expresión inválida");
        }

        return stack.pop();
    }
    
    /**
     * Helper method to get stack size safely
     * @return The size of the stack
     */
    private int getStackSize() {
        if (stack instanceof StackArrayList) {
            return ((StackArrayList<Double>) stack).size();
        } else if (stack instanceof StackVector) {
            return ((StackVector<Double>) stack).size();
        }
        return 0;
    }
}