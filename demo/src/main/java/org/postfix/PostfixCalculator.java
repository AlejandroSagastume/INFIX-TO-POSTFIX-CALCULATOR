package org.postfix;

public class PostfixCalculator implements Calc {
    private Stack<Double> stack;

    public PostfixCalculator() {
        this.stack = new StackVector<>();
    }

    @Override
    public double calculate(String input) {
        stack = new StackVector<>(); // clear stack
        String[] tokens = input.trim().split("\\s+");
        for (String token : tokens) {
            if (token.matches("[+\\-*/]")) {
                if (((StackVector<Double>) stack).size() < 2) {
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

        if (((StackVector<Double>) stack).size() != 1) {
            throw new IllegalArgumentException("Expresión inválida");
        }

        return stack.pop();
    }
}