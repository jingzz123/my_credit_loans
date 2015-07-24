package com.intellicredit.platform.component.situation2;

import com.intellicredit.platform.service.rule.forward2.InferenceEngine;

public class ExpressionAction extends SimpleAction {

    private Variable expVar;

    public ExpressionAction(String name, Variable var, String expStr, InferenceEngine engine) {
        super(name, var, null);
        expVar = Variable.newExpressionVariable("", var.getDataType(), expStr,engine);
    }

    /**
     * @return   the error code as the result of execution.<br>
     *            0 ----- no error.<br>
     *            1 ----- variable is NULL.<br>
     *            2 ----- variable is not type of simple variable.
     */
    public int go() {
        if(m_variable==null)
            return 1;

        if(m_variable.getSourceType()!=Variable.SIMPLE)
            return 2;

        m_variable.setValue(expVar.getValue());
        return 0;
    }
}
