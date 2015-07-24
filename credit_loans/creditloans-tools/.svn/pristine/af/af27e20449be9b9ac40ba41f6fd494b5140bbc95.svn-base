package com.intellicredit.platform.component.expression.function;

import java.lang.Math;
import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.component.expression.type.*;

public class Cosine
    extends PostfixMathCommand {
    public Cosine() {
        numberOfParameters = 1;
    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack
        Object param = inStack.pop();
        inStack.push(cos(param)); //push the result on the inStack
        return;
    }

    public Object cos(Object param) throws ParseException {
        if (param instanceof Number) {
            return new Double(Math.cos( ( (Number) param).doubleValue()));
        }
        else if (param instanceof Complex) {
            return ( (Complex) param).cos();
        }

        throw new ParseException("Invalid parameter type");
    }

}
