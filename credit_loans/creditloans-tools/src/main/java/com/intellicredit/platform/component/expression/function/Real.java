package com.intellicredit.platform.component.expression.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.component.expression.type.*;

public class Real
    extends PostfixMathCommand {
    public Real() {
        numberOfParameters = 1;
    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack
        Object param = inStack.pop();
        inStack.push(re(param)); //push the result on the inStack
        return;
    }

    public Number re(Object param) throws ParseException {
        if (param instanceof Number) {
            return ( (Number) param);
        }
        else if (param instanceof Complex) {
            return new Double( ( (Complex) param).re());
        }

        throw new ParseException("Invalid parameter type");
    }

}
