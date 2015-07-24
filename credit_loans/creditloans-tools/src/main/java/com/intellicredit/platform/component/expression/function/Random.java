package com.intellicredit.platform.component.expression.function;

import java.lang.Math;
import java.util.*;
import com.intellicredit.platform.component.expression.*;

/**
 * Encapsulates the Math.random() function.
 */
public class Random
    extends PostfixMathCommand {
    public Random() {
        numberOfParameters = 0;

    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack
        inStack.push(new Double(Math.random()));
        return;
    }
}
