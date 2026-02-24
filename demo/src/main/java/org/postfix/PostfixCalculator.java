package org.postfix;

public class PostfixCalculator implements Calc {

    private static PostfixCalculator instance;

    private PostfixCalculator() {}

    public static PostfixCalculator getInstance() {
        if (instance == null) {
            instance = new PostfixCalculator();
        }
        return instance;
    }

    @Override
    public double calculate(String input) {

        Stack<Double> stack =
            StackFactory.create(StackFactory.StackType.ARRAYLIST,ListFactory.ListType.SINGLY);

        String[] tokens = input.trim().split("\\s+");

        for (String token : tokens) {

            if (token.matches("[+\\-*/]")) {

                if (stack.size() < 2)
                    throw new IllegalArgumentException("Operandos insuficientes");

                double b = stack.pop();
                double a = stack.pop();

                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/":
                        if (b == 0)
                            throw new ArithmeticException("División entre cero");
                        stack.push(a / b);
                        break;
                }

            } else {
                stack.push(Double.parseDouble(token));
            }
        }

        if (stack.size() != 1)
            throw new IllegalArgumentException("Expresión inválida");

        return stack.pop();
    }
}