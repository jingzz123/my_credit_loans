package com.intellicredit.platform.service.rule.forward2;

import java.util.*;

//存放"preconditions"
public class PreRuleCondition {
    private List<Rule> m_ruleRefs = new ArrayList<Rule>(); //引用此PreRuleCondition的Rule列表
    private List<RuleCondition> m_conditionRefs = new ArrayList<RuleCondition>();
    protected String m_name; //PreCondition的名称
    protected InferenceEngine m_engine = null;

    PreRuleCondition() {}

    public void setName(String name) {
        m_name = name;
    }

    public String getName() {
        return m_name;
    }

    public List<RuleCondition> conditionRefs() {
        return m_conditionRefs;
    }

    public List<Rule> ruleRefs() {
        return m_ruleRefs;
    }

    public void setEngine(InferenceEngine engine) {
        m_engine = engine;
    }

    public void addRuleRef(Rule ref) {
        if (!m_ruleRefs.contains(ref)) {
            m_ruleRefs.add(ref);
        }
    }

    public void setConditionRef(List<RuleCondition> conditionRefs) {
            m_conditionRefs = conditionRefs;
    }
}
