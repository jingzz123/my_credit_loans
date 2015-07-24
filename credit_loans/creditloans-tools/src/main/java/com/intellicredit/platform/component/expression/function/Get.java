package com.intellicredit.platform.component.expression.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;

public class Get
    extends PostfixMathCommand {
    /**
     * Constructor.
     */
    public Get() {
        // Use a variable number of arguments
        numberOfParameters = 2;
    }

    /**
     * Calculates the result of summing up all parameters, which are assumed
     * to be of the Double type.
     */
    public void run(Stack<Object> stack) throws ParseException {

        // Check if stack is null
        if (null == stack) {
            throw new ParseException("Stack argument null");
        }

        Object param = null;
        double result = 0;
        int index = 0;

        // get the parameter from the stack
        param = stack.pop();
        if (param instanceof Number) {
            index = ( (Number) param).intValue();
        }
        else {
            throw new ParseException("Invalid parameter type");
        }

        param = stack.pop();
        if (param instanceof int[]) {
            int[] values = (int[]) param;
            if (index < 0 || index >= values.length) {
                throw new ParseException("parameter out of boundary");
            }
            result = values[index];
        }
        else if (param instanceof float[]) {
            float[] values = (float[]) param;
            if (index < 0 || index >= values.length) {
                throw new ParseException("parameter out of boundary");
            }
            result = values[index];
        }
        else if (param instanceof long[]) {
            long[] values = (long[]) param;
            if (index < 0 || index >= values.length) {
                throw new ParseException("parameter out of boundary");
            }
            result = values[index];
        }
        else if (param instanceof double[]) {
            double[] values = (double[]) param;
            if (index < 0 || index >= values.length) {
                throw new ParseException("parameter out of boundary");
            }
            result = values[index];
        }
        else if (param instanceof Object[]) {
            Object[] values = (Object[]) param;
            if (index < 0 || index >= values.length) {
                throw new ParseException("parameter out of boundary");
            }
            stack.push(values[index]);
            return;
        }
        else {
            throw new ParseException("Invalid parameter type");
        }

        // push the result on the inStack
        stack.push(new Double(result));
    }
}
