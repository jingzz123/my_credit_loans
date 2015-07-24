package com.intellicredit.platform.service.rule.forward2;

import java.util.*;
import com.intellicredit.platform.component.situation2.*;
import com.intellicredit.platform.component.transaction.*;

/**
 * RuleVariable is subclass of Variable and add some rule-specific behavior.
 * A new data member is the Vector conditionRefs, which holds reference to all conditions
 * which refer to this variable. Instance of RuleCondition register themselves by calling
 * the addRuleConditionRef() method.  The setValue() method not only sets the value of the variable
 * , it also calls the updateRuleCondition() method, which iterates through every RuleCondition which refers
 * to this RuleVariable and refers its truth value via its check method.
 * The promptString holds the text that is displayed when the user is prompted to provide a value for
 * theis variable. The ruleName holds the name of the rule which set this RuleVariable value;
 * when the rule fires it calls the setRuleName() method accordingly.
 *
 * @author wallace
 */
public class RuleVariable extends Variable {

    /**
     * Whether this variable is output to engine.
     */
    private boolean output;

    /**
     * this is the variable description.
     */
    private String m_desc = null;

    /**
     * Conditions which refer to this var.
     */
    private List<RuleCondition> m_conditionRefs = new ArrayList<RuleCondition>();

    /**
     * Construct a new RuleVariable by given name and type and initValue.
     * @param name         the name of variable
     * @param sourceType   the source type of variable. e.g. SIMPLE, EXTERNAL, etc.
     * @param type         the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param otherParams  other parameters that should be passed to this variable
     */
    public RuleVariable(String name, int sourceType, int type, Object[] otherParams) {
        super(name, sourceType, type, otherParams);
    }

    /**
     * Construct a new simple rule variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @return the rule variable created
     */
    public static RuleVariable newSimpleRuleVariable(String name, int dataType,InferenceEngine engine) {
        return new RuleVariable(name, SIMPLE, dataType, new Object[]{engine});
    }

    /**
     * Construct a new simple rule variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param initValue  the initial value of variable
     * @return the rule variable created
     */
    public static RuleVariable newSimpleRuleVariable(String name, int dataType,InferenceEngine engine, Object initValue) {
        return new RuleVariable(name, SIMPLE, dataType, new Object[] {engine,initValue});
    }

    /**
     * Construct a new external rule variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param attrName   the attribute name used to search for value
     * @return the rule variable created
     */
    public static RuleVariable newExternalRuleVariable(String name, int dataType, String attrName,InferenceEngine engine) {
        return new RuleVariable(name, EXTERNAL, dataType, new Object[] {attrName,engine});
    }

    /**
     * Construct a new external rule variable.
     * @param name           the name of variable
     * @param dataType       the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param attrName   the attribute name used to search for value
     * @param defaultValue   the default value which should be returned when no Situation
     *                        associated with ExternalSource or attribute name not found
     *                        in Situaion
     * @return the rule variable created
     */
    public static RuleVariable newExternalRuleVariable(String name, int dataType, String attrName, InferenceEngine engine,Object defaultValue) {
        return new RuleVariable(name, EXTERNAL, dataType, new Object[] {attrName, engine,defaultValue});
    }

    /**
     * Construct a new transaction rule variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param field      the column name in the transaction
     * @param subId      the id of segment in the transaction.
     *                    It is the first column name in the segment.
     * @param schema      the schema of  the transaction.
     * @return the rule variable created
     */
    public static RuleVariable newTransactionRuleVariable(String name, int dataType, String field, String subId,Schema schema,InferenceEngine engine) {
        return new RuleVariable(name, TRANSITION, dataType, new Object[] {field, subId, schema,engine});
    }

    /**
     * Construct a new expression rule variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param expStr     the expression string
     * @return the rule variable created
     */
    public static RuleVariable newExpressionRuleVariable(String name, int dataType, String expStr,InferenceEngine engine) {
        return new RuleVariable(name, EXPRESSION, dataType, new Object[] {expStr, engine, Boolean.valueOf(true)});
    }

    /**
     * Construct a new expression rule variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param expStr     the expression string
     * @param addStdFun  specify whether add standard functions
     * @return the rule variable created
     */
    public static RuleVariable newExpressionRuleVariable(String name, int dataType, String expStr, InferenceEngine engine,boolean addStdFun) {
        return new RuleVariable(name, EXPRESSION, dataType, new Object[] {expStr, engine, Boolean.valueOf(addStdFun)});
    }

    public void isOutput(boolean b) {
        output = b;
    }

    public boolean isOutput() {
        return output;
    }

    public void setDescription(String desc){
        m_desc = desc;
    }

    public String getDescription(){
        if(m_desc==null||m_desc.trim().length()==0){
            return getName();
        }
        return m_desc.trim();
    }

    public List<RuleCondition> getRuleConditions() {
        return m_conditionRefs;
    }

    public void setValue(Object value) {
        super.setValue(value);
        updateRuleConditions();
    }

    public void addRuleCondition(RuleCondition ref) {
        if(!m_conditionRefs.contains(ref))
            m_conditionRefs.add(ref);

        //如果当前的RuleVaribale为Expression Variable,那将[RuleCondition ref]和Expression Variable
        //包含的变量联系起来
        if(getSourceType()==EXPRESSION) {
            ExpressionSource expSource = (ExpressionSource)getSource();
            String[] vars = expSource.getVars();
            if(vars==null)
                return;

            Map<String,RuleVariable> variablesMap = expSource.getEngine().getVariablesMap();
            for(int i=0;i<vars.length;i++) {
                Variable v = (Variable)variablesMap.get(vars[i]);
                if(v instanceof RuleVariable)
                    ((RuleVariable)v).addRuleCondition(ref);
            }
        }
    }

    //根据当前变量的值,check 使用其的RuleCondition-s
    public void updateRuleConditions() {
        int size   = m_conditionRefs.size();
        for(int i = 0; i < size; i++) {
            ((RuleCondition)m_conditionRefs.get(i)).check();
        }
    }
}
