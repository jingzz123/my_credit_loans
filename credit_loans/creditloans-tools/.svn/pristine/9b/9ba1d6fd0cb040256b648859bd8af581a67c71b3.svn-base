package com.intellicredit.platform.component.expression.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.component.expression.type.*;

public class ArcSineH
    extends PostfixMathCommand {
    public ArcSineH() {
        numberOfParameters = 1;
    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack
        Object param = inStack.pop();
        inStack.push(asinh(param)); //push the result on the inStack
        return;
    }

    public Object asinh(Object param) throws ParseException {
        if (param instanceof Number) {
            Complex temp = new Complex( ( (Number) param).doubleValue(), 0.0);

            return temp.asinh();
        }
        else if (param instanceof Complex) {
            return ( (Complex) param).asinh();
        }

        throw new ParseException("Invalid parameter type");
    }
}
