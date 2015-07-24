package com.intellicredit.platform.service.rule.forward2;

import java.util.*;

public abstract class RuleAction {

    protected InferenceEngine m_engine;
    private String name;//Action 名称
    private List<Rule> m_rules = new ArrayList<Rule>();//使用此Action的所有Rule列表

    //如果为VarAction/ExpRuleAction或者FinCallAction,存放Action使用的变量对应的所有RuleCondition-s
    private List<RuleCondition> m_ruleConditons = new ArrayList<RuleCondition>();

    public RuleAction(String name) {
        this.name = name;
    }

    public void setEngine(InferenceEngine engine){
        m_engine = engine;
    }

    public String getName() {
        return name;
    }

    public void addRuleRef(Rule rule) {
        if(!m_rules.contains(rule))
            m_rules.add(rule);
    }

    public abstract void go();

    public List<RuleCondition> getRuleConditions() {
        return m_ruleConditons;
    }

}
