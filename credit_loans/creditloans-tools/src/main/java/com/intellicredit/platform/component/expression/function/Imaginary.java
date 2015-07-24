package com.intellicredit.platform.component.expression.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.component.expression.type.*;

public class Imaginary
    extends PostfixMathCommand {
    public Imaginary() {
        numberOfParameters = 1;
    }

    public void run(Stack<Object> inStack) throws ParseException {

        checkStack(inStack); // check the stack
        Object param = inStack.pop();
        inStack.push(im(param)); //push the result on the inStack
        return;
    }

    public Number im(Object param) throws ParseException {

        if (param instanceof Number) {
            return new Double(0);
        }
        else if (param instanceof Complex) {
            return new Double( ( (Complex) param).im());
        }

        throw new ParseException("Invalid parameter type");
    }

}
