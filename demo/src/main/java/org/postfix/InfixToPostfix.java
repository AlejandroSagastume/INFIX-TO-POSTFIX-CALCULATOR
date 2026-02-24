package org.postfix;

public class InfixToPostfix {
    /**
     * Convierte una expresión infix a postfix utilizando el algoritmo de Shunting Yard. El método procesa cada carácter de la expresión infix, manejando números, operadores y paréntesis según las reglas de precedencia y asociación. Si la expresión es inválida, se lanzará una excepción con un mensaje descriptivo del error.
     * @param infix La expresión infix a convertir
     * @return La expresión en notación postfix equivalente a la expresión infix dada
     * @throws IllegalArgumentException Si la expresión infix es inválida (ej. paréntesis desbalanceados, caracteres no válidos, etc.)
     */
    public String convert(String infix) {
        if (infix == null || infix.trim().isEmpty()) {
            throw new IllegalArgumentException("La expresión está vacía");
        }
        infix = infix.replaceAll("\\s+", "");
        Stack<Character> operatorStack =
                StackFactory.create(StackFactory.StackType.ARRAYLIST,ListFactory.ListType.SINGLY);

        StringBuilder postfix = new StringBuilder();
        int i = 0;
        while (i < infix.length()) {
            char ch = infix.charAt(i);
            if (Character.isDigit(ch)) {
                StringBuilder number = new StringBuilder();
                while (i < infix.length() &&
                        (Character.isDigit(infix.charAt(i)) || infix.charAt(i) == '.')) {
                    number.append(infix.charAt(i));
                    i++;
                }
                postfix.append(number).append(" ");
                continue;
            }
            if (ch == '(') {
                operatorStack.push(ch);
            }

            else if (ch == ')') {
                while (!operatorStack.isEmpty() &&
                        operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop()).append(" ");
                }
                if (operatorStack.isEmpty()) {
                    throw new IllegalArgumentException("Paréntesis desbalanceados");
                }
                operatorStack.pop();
            }

            else if (isOperator(ch)) {
                while (!operatorStack.isEmpty() &&
                        precedence(operatorStack.peek()) >= precedence(ch)) {
                    postfix.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(ch);
            }
            // Signos seguidos de espacios o caracteres no válidos (ej. "3 + - 4")
            else if (ch == '-' && (i == 0 || infix.charAt(i - 1) == '(' || isOperator(infix.charAt(i - 1)))) {
                // Si es un signo negativo, lo tratamos como un número negativo
                StringBuilder number = new StringBuilder();
                number.append('-');
                i++;
                while (i < infix.length() &&
                        (Character.isDigit(infix.charAt(i)) || infix.charAt(i) == '.')) {
                    number.append(infix.charAt(i));
                    i++;
                }
                postfix.append(number).append(" ");
            }
            else {
                throw new IllegalArgumentException("Carácter inválido: " + ch);
            }
            i++;
        }

        while (!operatorStack.isEmpty()) {
            char top = operatorStack.pop();
            if (top == '(') {
                throw new IllegalArgumentException("Paréntesis desbalanceados");
            }
            postfix.append(top).append(" ");
        }
        return postfix.toString().trim();
    }


    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            default:
                return 0;
        }
    }
}