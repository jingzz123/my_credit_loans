package com.intellicredit.platform.component.expression.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.component.expression.type.*;

public class ArcTanH
    extends PostfixMathCommand {
    public ArcTanH() {
        numberOfParameters = 1;
    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack
        Object param = inStack.pop();
        inStack.push(atanh(param)); //push the result on the inStack
        return;
    }

    public Object atanh(Object param) throws ParseException {
        if (param instanceof Number) {
            Complex temp = new Complex( ( (Number) param).doubleValue(), 0.0);

            return temp.atanh();
        }
        else if (param instanceof Complex) {
            return ( (Complex) param).atanh();
        }

        throw new ParseException("Invalid parameter type");
    }

}
