package com.intellicredit.platform.component.expression.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;

/**
 * All function classes must implement this interface to ensure that the run()
 * method is implemented.
 */
public interface PostfixMathCommandI {
    /**
     * Run the function on the stack. Pops the arguments from the stack, and
     * pushes the result on the top of the stack.
     */
    public void run(Stack<Object> aStack) throws ParseException;

    /**
     * Returns the number of required parameters, or -1 if any number of
     * parameters is allowed.
     */
    public int getNumberOfParameters();

    /**
     * Sets the number of current number of parameters used in the next call
     * of run(). This method is only called when the reqNumberOfParameters is
     * -1.
     */
    public void setCurNumberOfParameters(int n);
}
