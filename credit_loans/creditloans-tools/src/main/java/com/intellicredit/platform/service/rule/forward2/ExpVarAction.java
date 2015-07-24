package com.intellicredit.platform.service.rule.forward2;

import com.intellicredit.platform.component.situation2.*;
import com.intellicredit.platform.service.rule.forward2.InferenceEngine;
import com.intellicredit.platform.service.rule.forward2.RuleVariable;

/**
 *
 * @author wallace
 */

public class ExpVarAction extends VarAction {

    private Variable expVar;

    public ExpVarAction(String name, RuleVariable var, String expStr,InferenceEngine engine) {
        super(name, var, null);
        expVar = Variable.newExpressionVariable("", var.getDataType(), expStr,engine);
    }

    public void go() {
        if(m_variable==null)
            return;
        m_variable.setValue(expVar.getValue());
    }
}
