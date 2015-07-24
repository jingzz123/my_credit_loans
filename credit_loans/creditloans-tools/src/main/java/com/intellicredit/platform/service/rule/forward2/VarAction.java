package com.intellicredit.platform.service.rule.forward2;

import java.util.*;
import com.intellicredit.platform.component.situation2.Variable;

public class VarAction extends RuleAction {
    RuleVariable m_variable;
    private Object m_value;

    public VarAction(String name, RuleVariable var, Object value) {
        super(name);
        m_value = value;
        m_variable = var;
    }

    public void go() {
        if(m_variable==null)
            return;
        setValue(m_value, m_variable.getDataType());
    }

    public List<RuleCondition> getRuleConditions() {
        if(m_variable==null)
            return super.getRuleConditions();
        return m_variable.getRuleConditions();
    }

    private void setValue(Object value, int dataType) {
        if (dataType == Variable.INT) {
            if (value instanceof Double)
                m_variable.setValue(new Integer(((Number)value).intValue()));
            else if (value instanceof String)
                m_variable.setValue(Integer.valueOf((String)value));
            else
                m_variable.setValue(value);
        }
        else if (dataType == Variable.FLOAT) {
            if (value instanceof Double)
                m_variable.setValue(new Float(((Number)value).floatValue()));
            else if (value instanceof String)
                m_variable.setValue(Float.valueOf((String)value));
            else
                m_variable.setValue(value);
        }
        else if (dataType == Variable.LONG) {
            if (value instanceof Double)
                m_variable.setValue(new Long(((Number)value).longValue()));
            else if (value instanceof String)
                m_variable.setValue(Long.valueOf((String)value));
            else
                m_variable.setValue(value);
        }
        else if (dataType == Variable.DOUBLE || dataType == Variable.NUMBER) {
            if (value instanceof String)
                m_variable.setValue(Double.valueOf((String)value));
            else
                m_variable.setValue(value);
        }
        else if (dataType == Variable.TEXT){
            m_variable.setValue(String.valueOf(value));
        }
        else
            m_variable.setValue(value);
    }
}