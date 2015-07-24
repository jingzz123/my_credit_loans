package com.intellicredit.platform.component.expression.function;

import java.lang.Math;
import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.component.expression.type.*;

public class ArcSine
    extends PostfixMathCommand {
    public ArcSine() {
        numberOfParameters = 1;

    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack
        Object param = inStack.pop();
        inStack.push(asin(param)); //push the result on the inStack
        return;
    }

    public Object asin(Object param) throws ParseException {
        if (param instanceof Number) {
            return new Double(Math.asin( ( (Number) param).doubleValue()));
        }
        else if (param instanceof Complex) {
            return ( (Complex) param).asin();
        }

        throw new ParseException("Invalid parameter type");
    }

}
