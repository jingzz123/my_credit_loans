package com.intellicredit.platform.component.expression.function;

import java.util.*;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.component.expression.type.*;

public class Subtract
    extends PostfixMathCommand {
    private static final long SECONDS_IN_DAY = 24 * 3600 * 1000L;

    public Subtract() {
        numberOfParameters = 2;
    }

    public void run(Stack<Object> inStack) throws ParseException {
        checkStack(inStack); // check the stack

        Object param2 = inStack.pop();
        Object param1 = inStack.pop();

        inStack.push(sub(param1, param2));

        return;
    }

    public Object sub(Object param1, Object param2) throws ParseException {
        if (param1 instanceof Number) {
            if (param2 instanceof Number) {
                return sub( (Number) param1, (Number) param2);
            }
            else if (param2 instanceof Complex) {
                return sub( (Number) param1, (Complex) param2);
            }
        }
        else if (param1 instanceof Complex) {
            if (param2 instanceof Number) {
                return sub( (Complex) param1, (Number) param2);
            }
            else if (param2 instanceof Complex) {
                return sub( (Complex) param1, (Complex) param2);
            }
        }
        else if ( (param1 instanceof Date) && (param2 instanceof Date)) {
            long time1 = ( (Date) param1).getTime() / SECONDS_IN_DAY;
            long time2 = ( (Date) param2).getTime() / SECONDS_IN_DAY;
            return new Double(time1 - time2);
        }
        else if ( (param1 instanceof String) && (param2 instanceof String)) {
            String str1 = (String) param1;
            String str2 = (String) param2;
            return new Double(str1.compareTo(str2));
        }

        throw new ParseException("Invalid parameter type");
    }

    public Double sub(Number d1, Number d2) {
        return new Double(d1.doubleValue() - d2.doubleValue());
    }

    public Complex sub(Complex c1, Complex c2) {
        return new Complex(c1.re() - c2.re(), c1.im() - c2.im());
    }

    public Complex sub(Complex c, Number d) {
        return new Complex(c.re() - d.doubleValue(), c.im());
    }

    public Complex sub(Number d, Complex c) {
        return new Complex(d.doubleValue() - c.re(), -c.im());
    }
}
