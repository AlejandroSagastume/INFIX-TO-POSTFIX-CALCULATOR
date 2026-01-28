package org.postfix;

public class PostfixCalculator implements Calc, Stack<Integer> {

    @Override
    public double calculate(String input) {
        return 0; 
    }

    @Override
    public void push(Integer item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer pop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer peek() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}