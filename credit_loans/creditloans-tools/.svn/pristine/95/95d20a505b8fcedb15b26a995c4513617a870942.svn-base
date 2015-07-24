package com.intellicredit.platform.component.expression.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.component.expression.type.*;

public class UMinus
    extends PostfixMathCommand {
    public UMinus() {
        numberOfParameters = 1;
    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack

        Object param = inStack.pop();

        inStack.push(umin(param));
        return;
    }

    public Object umin(Object param) throws ParseException {
        if (param instanceof Number) {
            return new Double( - ( (Number) param).doubleValue());
        }
        else if (param instanceof Complex) {
            return ( (Complex) param).neg();
        }

        throw new ParseException("Invalid parameter type");
    }
}
