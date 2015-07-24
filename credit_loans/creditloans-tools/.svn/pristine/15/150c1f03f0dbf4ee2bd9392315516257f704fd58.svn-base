package com.intellicredit.platform.component.expression.function;

import java.util.Stack;
import com.intellicredit.platform.component.expression.ParseException;

public class SubStr
    extends PostfixMathCommand {
    /**
     * Constructor.
     */
    public SubStr() {
        // Use a variable number of arguments
        numberOfParameters = 3;
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

        int endIndex = 0;
        // get the parameter from the stack
        param = stack.pop();
        if (param instanceof Number) {
            endIndex = ( (Number) param).intValue();
        }
        else {
            throw new ParseException("Invalid parameter type");
        }

        int beginIndex = 0;
        // get the parameter from the stack
        param = stack.pop();
        if (param instanceof Number) {
            beginIndex = ( (Number) param).intValue();
        }
        else {
            throw new ParseException("Invalid parameter type");
        }

        // get the parameter from the stack
        String result = null;
        param = stack.pop();
        /*
        if (param instanceof String) {
            result = (String) param;
        }
        else {
            throw new ParseException("Invalid parameter type");
        }*/

        try {
            result = String.valueOf(param);
            result = result.substring(beginIndex, endIndex);
        }
        catch (Exception e) {
            result = "";
        }
        // push the result on the inStack
        stack.push(result);
    }

}
