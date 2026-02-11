package org.postfix;

/**
 * Clase para convertir expresiones aritméticas de notación INFIX a POSTFIX
 * 
 * Ejemplos:
 * - INFIX:   (10 + 20) * 9
 * - POSTFIX: 10 20 + 9 *
 * 
 * Algoritmo: Shunting Yard (Edsger Dijkstra)
 * 
 * @author Alejandro Sagastume, Jimena Vásquez
 * @version 2.0
 */
public class InfixToPostfixConverter {
    
    /**
     * Convierte una expresión INFIX a POSTFIX
     * 
     * @param infix Expresión en notación infix (ej: "(10+20)*9")
     * @return Expresión en notación postfix (ej: "10 20 + 9 *")
     * @throws IllegalArgumentException si la expresión es inválida
     */
    public String convert(String infix) {
        if (infix == null || infix.trim().isEmpty()) {
            throw new IllegalArgumentException("La expresión está vacía");
        }
        
        // Eliminar espacios en blanco
        infix = infix.replaceAll("\\s+", "");
        
        StringBuilder postfix = new StringBuilder();
        IStack<Character> operatorStack = StackFactory.createStack();
        
        int i = 0;
        while (i < infix.length()) {
            char c = infix.charAt(i);
            
            // 1. Si es un NÚMERO (puede ser multi-dígito)
            if (Character.isDigit(c)) {
                // Leer el número completo
                StringBuilder number = new StringBuilder();
                while (i < infix.length() && 
                       (Character.isDigit(infix.charAt(i)) || infix.charAt(i) == '.')) {
                    number.append(infix.charAt(i));
                    i++;
                }
                postfix.append(number).append(" ");
                continue; // Ya incrementamos i
            }
            
            // 2. Si es PARÉNTESIS IZQUIERDO '('
            else if (c == '(') {
                operatorStack.push(c);
            }
            
            // 3. Si es PARÉNTESIS DERECHO ')'
            else if (c == ')') {
                // Desapilar hasta encontrar '('
                boolean foundOpenParen = false;
                while (!isStackEmpty(operatorStack)) {
                    char top = operatorStack.pop();
                    if (top == '(') {
                        foundOpenParen = true;
                        break;
                    }
                    postfix.append(top).append(" ");
                }
                
                if (!foundOpenParen) {
                    throw new IllegalArgumentException(
                        "Paréntesis desbalanceados: ')' sin '(' correspondiente"
                    );
                }
            }
            
            // 4. Si es un OPERADOR (+, -, *, /)
            else if (isOperator(c)) {
                // Desapilar operadores con mayor o igual precedencia
                while (!isStackEmpty(operatorStack) && 
                       peekStack(operatorStack) != '(' &&
                       precedence(peekStack(operatorStack)) >= precedence(c)) {
                    postfix.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(c);
            }
            
            // 5. Carácter inválido
            else {
                throw new IllegalArgumentException(
                    "Carácter inválido en la expresión: '" + c + "'"
                );
            }
            
            i++;
        }
        
        // Vaciar la pila de operadores restantes
        while (!isStackEmpty(operatorStack)) {
            char top = operatorStack.pop();
            if (top == '(') {
                throw new IllegalArgumentException(
                    "Paréntesis desbalanceados: '(' sin ')' correspondiente"
                );
            }
            postfix.append(top).append(" ");
        }
        
        // Eliminar espacio final
        String result = postfix.toString().trim();
        
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Expresión inválida");
        }
        
        return result;
    }
    
    /**
     * Determina si un carácter es un operador válido
     * @param c Carácter a verificar
     * @return true si es +, -, *, /
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    /**
     * Retorna la precedencia de un operador
     * Mayor número = mayor precedencia
     * 
     * @param operator Operador (+, -, *, /)
     * @return Nivel de precedencia (1-2)
     */
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
    
    /**
     * Verifica si la pila está vacía
     * (Método helper para compatibilidad con diferentes implementaciones)
     */
    private boolean isStackEmpty(IStack<Character> stack) {
        try {
            stack.peek();
            return false;
        } catch (IllegalStateException e) {
            return true;
        }
    }
    
    /**
     * Obtiene el elemento en el tope de la pila sin eliminarlo
     * (Método helper para compatibilidad)
     */
    private char peekStack(IStack<Character> stack) {
        return stack.peek();
    }
    
    /**
     * Método de prueba rápida
     */
    public static void main(String[] args) {
        InfixToPostfixConverter converter = new InfixToPostfixConverter();
        
        // Casos de prueba
        String[] tests = {
            "(10+20)*9",           // Esperado: "10 20 + 9 *"
            "3+4*5",               // Esperado: "3 4 5 * +"
            "(3+4)*5",             // Esperado: "3 4 + 5 *"
            "10+20*30",            // Esperado: "10 20 30 * +"
            "2+3*4-5",             // Esperado: "2 
