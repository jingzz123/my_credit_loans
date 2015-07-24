package com.intellicredit.platform.service.rule.forward2;

import java.util.*;

//封装一系列的Rule
public class RuleSet {

    private List<Rule>  m_ruleList;//存放一系列的Rule对象

    public RuleSet() {
        m_ruleList = new ArrayList<Rule>();
    }

    public void add(Rule rule) {
        m_ruleList.add(rule);
    }

    public void remove(Rule rule) {
        m_ruleList.remove(rule);
    }

    public void remove(String name) {
        int size = m_ruleList.size();
        for(int i = 0; i < size; i++) {
            if (((Rule)m_ruleList.get(i)).getName().equals(name))
                m_ruleList.remove(i);
        }
    }

    public boolean exist(Rule rule) {
        return m_ruleList.contains(rule);
    }

    public List<Rule> getRuleList() {
        return m_ruleList;
    }

    public Rule[] getRuleArray() {
        return m_ruleList.toArray(new Rule[0]);
    }

    public Vector<Rule> getRuleVector() {
        Vector<Rule> v = new Vector<Rule>();
        int size = m_ruleList.size();
        for(int i = 0; i < size; i++) {
            v.add(m_ruleList.get(i));
        }
        return v;
    }
}