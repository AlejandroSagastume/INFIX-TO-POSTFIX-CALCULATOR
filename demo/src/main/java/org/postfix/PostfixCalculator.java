package org.postfix;

public class PostfixCalculator implements Calc {

    private static PostfixCalculator instance;

    private PostfixCalculator() {}

    /**
     * Patron Singleton para asegurar que solo haya una instancia de PostfixCalculator en toda la aplicación
     * @return La instancia única de PostfixCalculator
    */
    public static PostfixCalculator getInstance() {
        if (instance == null) {
            instance = new PostfixCalculator();
        }
        return instance;
    }
    /**
     * Calcula el resultado de una expresión en notación postfix. La expresión debe estar formada por números y operadores separados por espacios. El método utiliza una pila para evaluar la expresión, procesando cada token y aplicando las operaciones correspondientes.
     * @param input La expresión en notación postfix a evaluar
     * @return El resultado de la evaluación de la expresión
     * @throws IllegalArgumentException Si la expresión es inválida 
     */
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