package com.intellicredit.platform.component.expression.function;

import java.util.*;
import java.text.SimpleDateFormat;
import com.intellicredit.platform.component.expression.*;

public class DCurTime
    extends PostfixMathCommand {

    private static Double m_dCurTime = null;
    private static SimpleDateFormat m_format = new SimpleDateFormat("yyyyMMdd");

    public DCurTime() {
        // Use a variable number of arguments
        numberOfParameters = 0;
        if (m_dCurTime == null) {
            String strValue = m_format.format(new Date(System.currentTimeMillis()));
            m_dCurTime = new Double(strValue);
        }
    }

    /**
     * Calculates the result of summing up all parameters, which are assumed
     * to be of the Double type.
     */
    public void run(Stack<Object> stack) throws ParseException {
        stack.push(m_dCurTime);
    }

    static {
        if (m_dCurTime == null) {
            String strValue = m_format.format(new Date(System.currentTimeMillis()));
            m_dCurTime = new Double(strValue);
        }
    }

}
