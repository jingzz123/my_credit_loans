package com.intellicredit.platform.component.expression.function;

import java.lang.Math;
import java.util.*;
import com.intellicredit.platform.component.expression.*;

public class Angle
    extends PostfixMathCommand {
    public Angle() {
        numberOfParameters = 2;
    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack
        Object param2 = inStack.pop();
        Object param1 = inStack.pop();

        if ( (param1 instanceof Number) && (param2 instanceof Number)) {
            double x = ( (Number) param1).doubleValue();
            double y = ( (Number) param2).doubleValue();
            inStack.push(new Double(Math.atan2(x, y))); //push the result on the inStack
        }
        else {
            throw new ParseException("Invalid parameter type");
        }
        return;
    }
}
