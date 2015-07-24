package com.intellicredit.platform.service.rule.forward2;

import java.util.*;
import com.intellicredit.platform.component.situation2.*;
import com.intellicredit.platform.component.transaction.*;
import com.intellicredit.platform.component.expression.Expression;

public abstract class InferenceEngine {
    protected Expression jep;
    {
        jep = new Expression();
        jep.setAllowUndeclared(true);
        jep.addStandardFunctions();
        RuleUtil.addUserFunction(jep);//Fixed，需要修改
    }

    protected String m_name; //Inference Engine的名称

    //TODO
    protected RuleVariable[] m_variables;//存放所有的RuleVariable，Fixed
    protected Map<String,RuleVariable> m_variablesMap;//存放所有的name和RuleVariable的关系，Fixed
    protected List<RuleCondition> m_conditionList = new ArrayList<RuleCondition>(); //所有Rule使用的所有Condition-s对象列表，Fixed
    protected List<Rule> m_ruleList = null; // list of all rules，Fixed
    protected RuleCondition[] m_conditions; //由m_conditionList.toArrary()得到，Fixed
    protected Schema m_schema;//Fixed
    //End：TODO

    protected List<String> m_actionList = new ArrayList<String>();
    protected Map<String,Object> m_rusultVarMap = new HashMap<String,Object>();
    protected Transaction m_transaction = null; //当前传入的Transaction

    protected boolean m_debug;
    protected boolean m_isErrorRule = false; //当发现数据违法某个rule，告知
    protected boolean m_terminated = false; //当发现数据违法某个rule，是否立即终止
    protected List<Rule> m_errorRuleList = null; //有问题的一系列rule

    protected Situation m_situation;

    public InferenceEngine(String name) {
        m_name = name;
        m_debug = false;
    }

    public InferenceEngine(String name, Situation situation) {
        this(name);
        setSituation(situation);
    }

    public void isDebug(boolean b) {
        m_debug = b;
    }

    public void setIsError(boolean b) {
        m_isErrorRule = b;
    }

    public boolean isError() {
        return m_isErrorRule;
    }

    public void setTerminated(boolean b) {
        m_terminated = b;
    }

    public boolean isTerminated() {
        return m_terminated;
    }

    public void addErrorRule(Rule rule) {
        if(m_errorRuleList==null){
            m_errorRuleList = new ArrayList<Rule>();
        }
        m_errorRuleList.add(rule);
    }

    public List<Rule> getErrorRule() {
        return m_errorRuleList;
    }

    public void setSituation(Situation situation) {
        m_situation = situation;
    }

    //得到当前的Transaction,add by wu 2004/09/13
    public Transaction getTransaction() {
        return m_transaction;
    }

    public void setTransaction(Transaction tran) {
        m_transaction = tran;
    }

    public Situation getSituation() {
        return m_situation;
    }

    public void addNameAction(NameAction action) {
        if (!m_actionList.contains(action.getName())) {
            m_actionList.add(action.getName());
        }
    }

    public List<String> getResultActs() {
        return m_actionList;
    }

    public Map<String,Object> getResultVars() {
        return m_rusultVarMap;
    }

    public void setVariableSet(List<RuleVariable> list) {
        m_variables = list.toArray(new RuleVariable[0]);
    }

    public RuleVariable[] getVariableSet() {
        return m_variables;
    }

    public Map<String,RuleVariable> getVariablesMap() {
        return m_variablesMap;
    }

    public void displayVariables() {
        for (int i = 0; i < m_variables.length; i++) {
            System.out.println(m_variables[i].getName() + " value = " + m_variables[i].getValue() + "\n");
        }
    }

    public void displayRules() {
        System.out.println(m_name + " Rule Base: " + "\n");
        int size = m_ruleList.size();
        for (int i = 0; i < size; i++) {
            ( (Rule) m_ruleList.get(i)).display();
        }
    }

    /**
     * Reset the rule base for another round of inferencing
     * by setting all variable values to null.
     */
    public void reset() {
        //reset rule
        m_isErrorRule = false;
        m_errorRuleList = new ArrayList<Rule>();
        m_transaction = null;
        int size = m_ruleList.size();
        for (int i = 0; i < size; i++) {
            m_ruleList.get(i).reset();
        }

        //reset condition
        size = m_conditionList.size();
        for (int i = 0; i < size; i++) {
            m_conditionList.get(i).reset();
        }

        for (int i = 0; i < m_variables.length; i++) {
            m_variables[i].reset();
        }

        m_actionList.clear();
        m_rusultVarMap.clear();
    }

    //firstCheck: 表示此方法是否是第一次被调用,如果是第一次被调用,那firstCheck=true
    private List<Rule> match(boolean firstCheck) {

        /* Used for forward chaining only.
         * Determine which rules can fire, return a Vector.
         * It walks through the m_ruleList. If the test parameter is true,
         * it calls the Rule.check() method to test all rule antecedent
         * conditions and set the rule's truth value. If test is false,
         * match() simply looks at the current rule's truth value.
         * If the rule is true, and it hasn't already been fired,
         * it will pass it to the metchList Vector. If not, we just continue
         * on to the next Rule on the m_ruleList.
         */

        List<Rule> matchList = new ArrayList<Rule>();
        int size = m_ruleList.size();
        for (int i = 0; i < size; i++) {
            Rule testRule = (Rule) m_ruleList.get(i);

            if (testRule.isUsed()) { //add by wu 2004/08/09
                if (firstCheck) { // test the rule antecedents
                    testRule.check();
                }
                if (testRule.isTrue() == null) {
                    continue;
                }

                // fire the rule only once for now
                if ( (testRule.isTrue().booleanValue() == true) &&
                    (testRule.isFired() == false)) {
                    matchList.add(testRule);
                }
            }
        }

        return matchList;
    }

    //从参数ruleSet中得到所包含的condition最多的最后一个rule
    private Rule selectRule(List<Rule> ruleSet) {

        /* Used for forward chaining only
         * Select a rule to fire based on specificity.
         * It takes a vector of rules, the conflict set, as an input parameter.
         * Our implementation is fairly simple.
         * We use specificity, that is, the number of antecedent conditions,
         * as our primary method for selecfting a rule for fire.
         * If two or more rules have the same number of antecedent conditiond,
         * we select the first rule we encounter.
         * We start by taking the first rule off the list and designate it
         * as our bestRule, also taking the number of antecedents as the
         * current best or mac value. in while() loop, we walk through the
         * rest of the conflict set. If we encounter a rule that has more
         * antecedent conditions than our previous max, we set that rules our
         * current bestRule and corresponding max value. After looking at
         * all of the rules in the conflict set, we return the final bestRule
         * to be fired.
         */

        long numClauses;

        Rule bestRule = (Rule) ruleSet.get(0);
        long max = bestRule.numAntecedents();
        int size = ruleSet.size();

        for (int i = 1; i < size; i++) {
            Rule nextRule = (Rule) ruleSet.get(i);

            //if ( (numClauses = nextRule.numAntecedents()) > max) {
            //modify by wu 2004/08/09,只有可以使用的rule才可以选择
            if (nextRule.isUsed() &&
                ( (numClauses = nextRule.numAntecedents()) > max)) {
                max = numClauses;
                bestRule = nextRule;
            }
        }
        return bestRule;
    }

    private void forwardChain() {

        /*
         * Contains the main control logic for forward chaining.
         * The method first allocates the conflictRuleSet vector. The match
         * method is called with a boolen true parameter to force an initial
         * test of all rules in the rule base. This returns with the intial
         * conflictRuleSet, a Vector of the rules wwhich are triggered and could
         * be fired. We then enter a while() loop, which runs untill we have
         * an empty conflictRuleSet. Inside the loop we first call the
         * selectRule() method, passing the conflictRuleSet as a parameter.
         * The selectRule() method performes the conflict resolution strategy
         * and returns with a single rule to fire. We call the Rule.fire()
         * method to to perform the consequent condition assignment, and then
         * retest all the Clauses and Rules which refer to the updated Variable.
         * While not a Rete implementation, this approach limits the amount of
         * condition testing that need to be performed. With the updated
         * m_variables, we call match() again, but this time we pass in a boolean
         * false parameter value. This tells match() to only look at the rule
         * truth values, not to test each rule.
         */

        m_isErrorRule = false;
        // first test all rules, based on initial data
        List<Rule> conflictRuleSet = match(true); // see which rules can fire

        while (conflictRuleSet.size() > 0) {
            if (m_debug) {
                System.out.println("conflictSet: " + conflictRuleSet.size());
            }
            Rule selected = selectRule(conflictRuleSet); // select the "best" rule

            /* fire the rule
             * do the consequent action/assignment
             * update all conditions and rules
             */
            selected.fire();
            if (m_isErrorRule) {
                addErrorRule(selected);
                if(m_terminated){//当发现一个有问题的Rule是否立即终止
                    return;
                }
            }

            if (m_debug) {
                System.out.println(selected.getName() + " is fired");
            }
            conflictRuleSet = match(false); // see which rules can fire
        }
    }

    public void process() {
        process(null, null);
    }

    public void process(Situation situation) {
        process(null, situation);
    }

    public void process(Transaction transaction) {
        process(transaction, null);
    }

    public void process(Transaction transaction, Situation situation) {
        reset();

        if (situation != null) {
            setSituation(situation);
        }
        m_transaction = transaction;
        /*
        if (transaction != null) {
            TransactionSource.setTransaction(transaction);
        }*/
        for (int i = 0; i < m_conditions.length; i++) {
            m_conditions[i].check();
        }
        forwardChain();

        for (int i = 0; i < m_variables.length; i++) {
            if (m_variables[i].isOutput()) {
                m_rusultVarMap.put(m_variables[i].getName(),m_variables[i].getValue());
            }
        }
    }

    //下面原来放在ExpressionSource中,用来保证同一个expression只被解析一次,例如: A=表达式, B=表达式,如果它们的表达式相同,那只需解析一次
    protected Map<String,Object> m_instanceMap = new HashMap<String,Object>();

    public Map<String,Object> getInstanceMap(){
        return m_instanceMap;
    }

    //下面原来放在SimpleSource中,用来存放每一个SimpleSource的值
    private Map<String,Object> m_simpleValueMap = new HashMap<String,Object>();

    public Map<String,Object> getSValueMap(){
        return m_simpleValueMap;
    }

}
