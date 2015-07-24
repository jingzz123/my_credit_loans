package com.intellicredit.platform.component.expression.function;

import java.lang.Math;
import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.component.expression.type.*;

public class ArcCosine
    extends PostfixMathCommand {
    public ArcCosine() {
        numberOfParameters = 1;

    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack
        Object param = inStack.pop();
        inStack.push(acos(param)); //push the result on the inStack
        return;
    }

    public Object acos(Object param) throws ParseException {
        if (param instanceof Number) {
            return new Double(Math.acos( ( (Number) param).doubleValue()));
        }
        else if (param instanceof Complex) {
            return ( (Complex) param).acos();
        }

        throw new ParseException("Invalid parameter type");
    }

}
