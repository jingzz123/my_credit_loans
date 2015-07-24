package com.intellicredit.platform.component.expression.function;

import java.lang.Math;
import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.service.rule.forward2.RuleUtil;

/**
 * This class serves mainly as an example of a function that accepts any
 * number of parameters. Note that the numberOfParameters is initialized to
 * -1.
 */
public class Min
    extends PostfixMathCommand {
    /**
     * Constructor.
     */
    public Min() {
        // Use a variable number of arguments
        numberOfParameters = -1;
    }

    /**
     * Calculates the result of summing up all parameters, which are assumed
     * to be of the Double type.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void run(Stack<Object> stack) throws ParseException {

        // Check if stack is null
        if (null == stack || curNumberOfParameters == 0) {
            throw new ParseException("Stack argument null");
        }

        Object param = null;
        double result = Double.MAX_VALUE;
        Comparable<?> compResult = null;
        int i = 0;
        int count = 0;

        // repeat summation for each one of the current parameters
        while (i < curNumberOfParameters) {
            // get the parameter from the stack
            param = stack.pop();
            if (compResult == null && param instanceof Number) {
                // calculate the result
                result = Math.min(result, ( (Number) param).doubleValue());
                count++;
            }
            else if (compResult == null && param instanceof int[]) {
                int[] values = (int[]) param;
                for (int j = 0; j < values.length; j++) {
                    result = Math.min(result, values[j]);
                }
                count += values.length;
            }
            else if (compResult == null && param instanceof float[]) {
                float[] values = (float[]) param;
                for (int j = 0; j < values.length; j++) {
                    result = Math.min(result, values[j]);
                }
                count += values.length;
            }
            else if (compResult == null && param instanceof long[]) {
                long[] values = (long[]) param;
                for (int j = 0; j < values.length; j++) {
                    result = Math.min(result, values[j]);
                }
                count += values.length;
            }
            else if (compResult == null && param instanceof double[]) {
                double[] values = (double[]) param;
                for (int j = 0; j < values.length; j++) {
                    result = Math.min(result, values[j]);
                }
                count += values.length;
            }
            else if (result == Double.MAX_VALUE && param instanceof Comparable) {
                Comparable comp = (Comparable<?>) param;
                if (compResult == null) {
                    compResult = comp;
                }
                else {
                    compResult = comp.compareTo(compResult) < 0 ? comp : compResult;
                }
                count++;
            }
            else if (result == Double.MAX_VALUE && param instanceof Comparable[]) {
                Comparable[] comps = (Comparable[]) param;
                for (int j = 0; j < comps.length; j++) {
                    if (compResult == null) {
                        compResult = comps[j];
                    }
                    else {
                        compResult = comps[j].compareTo(compResult) < 0 ? comps[j] : compResult;
                    }
                }
                count += comps.length;
            }
            else {
                throw new ParseException("Invalid parameter type");
            }

            i++;
        }
        if (count == 0 && param != null) {
            if (param instanceof Date || param instanceof Date[]) {
                stack.push(RuleUtil.getMinDate());
            }
            else if (param instanceof String || param instanceof String[]) {
                stack.push("!!!!!!!!!!");
            }
            else {
                stack.push(new Double(Double.MIN_VALUE));
            }
            return;
        }
        // push the result on the inStack
        stack.push(compResult != null ? compResult : new Double(result));
    }
}
