package com.intellicredit.platform.component.expression.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;

public class CurTime
    extends PostfixMathCommand {
    /**
     * Constructor.
     */
    private Date curDate = new Date();
    public CurTime() {
        // Use a variable number of arguments
        numberOfParameters = 0;
    }

    /**
     * Calculates the result of summing up all parameters, which are assumed
     * to be of the Double type.
     */
    public void run(Stack<Object> stack) throws ParseException {

        // push the result on the inStack
        curDate.setTime(System.currentTimeMillis());
        stack.push(curDate);
    }
}
