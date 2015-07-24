package com.intellicredit.platform.component.expression.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;

/**
 * This class serves mainly as an example of a function that accepts any
 * number of parameters. Note that the numberOfParameters is initialized to
 * -1.
 */
public class Sum
    extends PostfixMathCommand {
    /**
     * Constructor.
     */
    public Sum() {
        // Use a variable number of arguments
        numberOfParameters = -1;
    }

    /**
     * Calculates the result of summing up all parameters, which are assumed
     * to be of the Double type.
     */
    public void run(Stack<Object> stack) throws ParseException {

        // Check if stack is null
        if (null == stack || curNumberOfParameters == 0) {
            throw new ParseException("Stack argument null");
        }

        Object param = null;
        double result = 0;
        int i = 0;

        // repeat summation for each one of the current parameters
        while (i < curNumberOfParameters) {
            // get the parameter from the stack
            param = stack.pop();
            if (param instanceof Number) {
                // calculate the result
                result += ( (Number) param).doubleValue();
            }
            else if (param instanceof int[]) {
                int[] values = (int[]) param;
                for (int j = 0; j < values.length; j++) {
                    result += values[j];
                }
            }
            else if (param instanceof float[]) {
                float[] values = (float[]) param;
                for (int j = 0; j < values.length; j++) {
                    result += values[j];
                }
            }
            else if (param instanceof long[]) {
                long[] values = (long[]) param;
                for (int j = 0; j < values.length; j++) {
                    result += values[j];
                }
            }
            else if (param instanceof double[]) {
                double[] values = (double[]) param;
                for (int j = 0; j < values.length; j++) {
                    result += values[j];
                }
            }
            else {
                throw new ParseException("Invalid parameter type");
            }

            i++;
        }

        // push the result on the inStack
        stack.push(new Double(result));
    }
}
