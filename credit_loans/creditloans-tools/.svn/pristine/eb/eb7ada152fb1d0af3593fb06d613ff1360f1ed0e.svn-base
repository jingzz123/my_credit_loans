package com.intellicredit.platform.service.rule.forward2;

import java.util.*;
import com.intellicredit.platform.component.situation2.*;
import com.intellicredit.platform.component.expression.*;

//现在的Rule中全部使用ExpRuleCondition而不使用RuleCondition
public class ExpRuleCondition extends RuleCondition {

    private Map<String,RuleVariable> getVariablesMap(){//Add by small
        return m_engine.getVariablesMap();
    }

    private Expression jep = new Expression();
    {
        jep.setAllowUndeclared(true);
        jep.addStandardFunctions();
        RuleUtil.addUserFunction(jep);
    }

    //ExpRuleCondition的左右两边均可以为表达式，如果使用定义的变量，变量必须以$开头并且只能由此一个变量。
    public ExpRuleCondition(String name, int type, String lhsExpStr, Operator op, String rhsExpStr, InferenceEngine engine) {
        m_name = name;
        m_operator = op;
        m_engine = engine;

        jep.parseExpression(lhsExpStr);
        if(jep.getTopNode() instanceof ASTVarNode) {
            String varName = ((ASTVarNode)jep.getTopNode()).getName();
            if(varName.charAt(0)=='$') {
                varName = varName.substring(1);
                //Object val = variablesMap.get(varName);
                Object val = getVariablesMap().get(varName);
                m_lhs = (val instanceof RuleVariable) ? (RuleVariable)val : null;
            }
        }
        if(m_lhs==null) {
            m_lhs = RuleVariable.newExpressionRuleVariable("lhs", type, lhsExpStr,m_engine);
        }
        m_lhs.addRuleCondition(this);

        jep.initSymTab();
        jep.parseExpression(rhsExpStr);
        if(jep.getTopNode() instanceof ASTVarNode) {
            String varName = ((ASTVarNode)jep.getTopNode()).getName();
            if(varName.charAt(0)=='$') {
                varName = varName.substring(1);
                //Object val = variablesMap.get(varName);
                Object val = getVariablesMap().get(varName);
                m_rhs = (val instanceof RuleVariable) ? (RuleVariable)val : null;
            }
        }
        if(m_rhs==null) {
            m_rhs = RuleVariable.newExpressionRuleVariable("rhs", type, rhsExpStr,m_engine);
        }
        ((RuleVariable)m_rhs).addRuleCondition(this);
    }
}
