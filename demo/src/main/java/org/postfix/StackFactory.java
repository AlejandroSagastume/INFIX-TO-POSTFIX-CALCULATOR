package org.postfix;

/**
 * Utility class to create Stack instances
 * Allows easy switching between different Stack implementations
 * Similar to Collections, Arrays, Objects in Java
 */
public class StackFactory {
    // Singleton pattern is not necessary here since we want to create multiple stacks, but we can control the default implementation type.
    public StackType chooseStackType(int option) {
        switch (option) {
            case 1:
                return StackType.ARRAYLIST;
            case 2:
                return StackType.VECTOR;
            default:
                throw new IllegalArgumentException("Opción no válida");
        }
    }
    /**
     * Enum to define available stack implementations
     */
    public enum StackType {
        ARRAYLIST,  // Implementation using ArrayList
        VECTOR      // Implementation using Vector
    }
    
    // Default stack type - CHANGE THIS TO SWITCH IMPLEMENTATIONS
    private static StackType defaultType = StackType.VECTOR;
    
    /**
     * Creates a stack using the default implementation
     * @param <T> The type of elements in the stack
     * @return A new Stack instance
     */
    public static <T> Stack<T> create() {
        return create(defaultType);
    }
    
    /**
     * Creates a stack of the specified type
     * @param <T> The type of elements in the stack
     * @param type The type of stack implementation to create
     * @return A new Stack instance
     */
    public static <T> Stack<T> create(StackType type) {
        switch (type) {
            case ARRAYLIST:
                System.out.println("Usando implementación: StackArrayList");
                return new StackArrayList<T>();
            case VECTOR:
                System.out.println("Usando implementación: StackVector");
                return new StackVector<T>();
            default:
                throw new IllegalArgumentException("Tipo de pila no soportado");
        }
    }
    
    /**
     * Sets the default stack implementation type
     * @param type The type to set as default
     */
    public static void setDefaultType(StackType type) {
        defaultType = type;
    }
    
    /**
     * Gets the current default stack type
     * @return The current default StackType
     */
    public static StackType getDefaultType() {
        return defaultType;
    }
}