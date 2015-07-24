package com.intellicredit.platform.component.expression.function;

import java.lang.Math;
import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.component.expression.type.*;

public class TanH
    extends PostfixMathCommand {
    public TanH() {
        numberOfParameters = 1;
    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack
        Object param = inStack.pop();
        inStack.push(tanh(param)); //push the result on the inStack
        return;
    }

    public Object tanh(Object param) throws ParseException {
        if (param instanceof Number) {
            double value = ( (Number) param).doubleValue();
            return new Double( (Math.exp(value) - Math.exp( -value)) / (Math.pow(Math.E, value) + Math.pow(Math.E, -value)));
        }
        else if (param instanceof Complex) {
            return ( (Complex) param).tanh();
        }

        throw new ParseException("Invalid parameter type");
    }

}
