package com.intellicredit.platform.component.expression.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.component.expression.type.*;

public class NaturalLogarithm
    extends PostfixMathCommand {
    public NaturalLogarithm() {
        numberOfParameters = 1;

    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack
        Object param = inStack.pop();
        inStack.push(ln(param)); //push the result on the inStack
        return;
    }

    public Object ln(Object param) throws ParseException {
        if (param instanceof Number) {
            // TODO: think about only returning Complex if param is <0
            Complex temp = new Complex( ( (Number) param).doubleValue());
            return temp.log();
        }
        else if (param instanceof Complex) {
            return ( (Complex) param).log();
        }

        throw new ParseException("Invalid parameter type");
    }
}
