package com.intellicredit.platform.service.rule.forward2;

import java.util.*;

/**
 * The Rule class is used to define a single rule and also contains
 * methods that support the inference process. Each rule has a name,
 * a reference to the owning RuleBase object, an array of antecedent
 * (left-hand-side LHS) RuleConditions, and an single consequent (right-hand-side RHS)
 * RuleCondition The Rule's true value is stored in the Boolean true, Note,
 * this is a Boolean object, not an elementary boolean variable. This
 * allows us to use a null value to indicate the rule's truth can not
 * be determined (because one of the variables referenced in a RuleCondition
 * is also null or undefined. the fired variable indicates whether
 * this rule has been fired or not.
 *
 * @author wallace
 */

public class Rule {
    private String m_name;//rule的名称
    private RuleCondition[] m_conditions;//当前rule所包含的conditions
    private RuleAction[] m_actions;//当前rule所包含的actions
    private Boolean m_truth; //  = null(unknown), true,  false
    private boolean m_fired;//是否已经fire
    //add by wu 2004/08/09,rule 是否可用,缺省为可用
    private boolean m_used = true;
    private String m_description = null;//rule的描述

    //constructor
    public Rule(String name, RuleCondition[] conditions, RuleAction[] actions) {
        m_name = name;
        m_conditions = conditions;
        for (int i = 0; i < m_conditions.length; i++) {
            m_conditions[i].addRuleRef(this);
        }
        m_actions = actions;
        for (int i = 0; i < m_actions.length; i++) {
            m_actions[i].addRuleRef(this);
        }
        m_truth = null;
        m_fired = false;
    }

    public String getName() {
        return m_name;
    }

    public boolean isFired() {
        return m_fired;
    }

    public int numAntecedents() {
        return m_conditions.length;
    }

    public RuleCondition[] getAntecedents() {
        return m_conditions;
    }

    public Boolean isTrue() {
        return m_truth;
    }

    //whether is used, add by wu 2004/08/09
    public void setUse(boolean used){
        m_used = used;
    }

    //whether is used, add by wu 2004/08/09
    public boolean isUsed(){
        return m_used;
    }

    //whether is used, add by wu 2004/08/09
    public void setDescription(String description){
        m_description = description;
    }

    //whether is used, add by wu 2004/08/09
    public String getDescription(){
        if(m_description==null || m_description.trim().length()==0){
            return m_name;
        }
        return m_description;
    }

    public void reset() {
        m_truth = null;
        m_fired = false;
    }

    /* used by forward chaining only !
     * A variable value was found, so retest all RuleConditions that reference that variable,
     * and then all rules which references those RuleConditions
     * it resets every RuleCondition which refers to the RuleVariable which was changed by the firing of the rule.
     * Because the Ruleariable noe has a value, all of the antecedent RuleConditions which reffered to it and
     * had undefined or null truth values can now be set to either true or false.
     *  this means that Rules which refered to those RuleConditions may now evaluate either true or false.
     */
    //public static void checkRules(ArrayList RuleConditionRefs) {
    public void checkRules(List<RuleCondition> RuleConditionRefs) {//Modify by small
        int size = RuleConditionRefs.size();
        for (int i = 0; i < size; i++) {
            RuleCondition condition = (RuleCondition) RuleConditionRefs.get(i);
            List<Rule> rules = condition.ruleRefs();
            int size1 = rules.size();
            for (int j = 0; j < size1; j++) {
                ( (Rule) rules.get(j)).check();
            }
        }
    }

    /* if antecedent [Condition] is true and rule has not fired,
     * used by forward chaining only !
     */
    public Boolean check() {
        //System.out.println("rule name is "+m_name);
        for (int i = 0; i < m_conditions.length; i++) {
            if (m_conditions[i].isTrue() == null) {
                return null;
            }

            if (m_conditions[i].isTrue().booleanValue() == true) {
                continue;
            } else {
                return m_truth = new Boolean(false); //don't fire this rule
            }
        }
        return m_truth = new Boolean(true); // could fire this rule
    }

    /* used by forward chaining only !
     * fire this rule -- perform the consequent RuleCondition
     * if a variable is changes, update all RuleConditions where
     * it is references, and then all rules which contain
     * those RuleConditions
     */
    public void fire() {
        //m_truth = new Boolean(true);
        m_fired = true;
        //set the variable value and update RuleConditions that associates with current conditions
        for (int i = 0; i < m_actions.length; i++) {
            m_actions[i].go();
            checkRules(m_actions[i].getRuleConditions());
        }
    }

    /**
     * Display the rule in text format.
     */
    public void display() {
        /*
        System.out.println(m_name + ": IF ");
        for (int i = 0; i < m_conditions.length; i++) {
            RuleCondition nextRuleCondition = m_conditions[i];
            System.out.println(nextRuleCondition.lhs().getName() + nextRuleCondition.cond().asString() +
            nextRuleCondition.rhs() + " ");
            if ((i + 1) < m_conditions.length)
                System.out.println("\n     AND ");
        }
        System.out.println("\n     THEN ");
        System.out.println(m_actions[0].lhs().getName() + m_actions[0].cond().asString() +
        m_actions[0].rhs() + "\n");
         */
    }
}
