package com.intellicredit.platform.component.expression.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;

public class Modulus
    extends PostfixMathCommand {
    public Modulus() {
        numberOfParameters = 2;
    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack
        Object param2 = inStack.pop();
        Object param1 = inStack.pop();

        if ( (param1 instanceof Number) && (param2 instanceof Number)) {
            double divisor = ( (Number) param2).doubleValue();
            double dividend = ( (Number) param1).doubleValue();

            double result = dividend % divisor;

            inStack.push(new Double(result));
        }
        else {
            throw new ParseException("Invalid parameter type");
        }
        return;
    }
}
