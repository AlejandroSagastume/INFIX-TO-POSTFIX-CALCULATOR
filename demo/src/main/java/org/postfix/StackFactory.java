package org.postfix;

public class StackFactory {

    public enum StackType {
        ARRAYLIST,
        VECTOR,
        LIST
    }

    public static <T> Stack<T> create(StackType type, ListFactory.ListType listType) {

        switch (type) {

            case ARRAYLIST:
                return new StackArrayList<>();

            case VECTOR:
                return new StackVector<>();

            case LIST:
                if (listType == null) {
                    throw new IllegalArgumentException("Debe especificar tipo de lista");
                }
                ListADT<T> list = ListFactory.create(listType);
                return new StackList<>(list);

            default:
                throw new IllegalArgumentException("Tipo de pila no soportado");
        }
    }
}