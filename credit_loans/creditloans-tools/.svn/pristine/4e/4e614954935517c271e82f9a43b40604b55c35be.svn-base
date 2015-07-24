package com.intellicredit.platform.service.rule.forward2;

import java.util.*;

import com.intellicredit.platform.component.situation2.*;

/**
 * Clauses are used in the antecedent and consequent parts of a Rule (but a clasue
 * instance can not in both antecedent and consequent parts.
 * Aclause is usually made up of a RuleVariable on the left-hand side
 * a, a Condition , which tests equalty, greater than, or less than, and
 * the right-hand side  which is a String value.
 * A Clause also contains a vector of the Rules which contain this Clause,
 * the variable consequent indicates whether the clause appears in the antecedent
 * or the consequent of the rule. Variable true indicate whether the clause is
 * true, false or unknown (null).
 *
 * The Clause registers itself with the RuleVariable so that whenever the variable's
 * value is changed, the Clause can be automatically retested.
 *
 * @author wallace
 */

public class RuleCondition {
    private List<Rule> ruleRefs = new ArrayList<Rule>(); //引用此RuleCondition的Rule列表
    private Boolean m_truth = null; // states = null(unknown), true or false

    protected RuleVariable m_lhs; //Operator的left RuleVariable
    protected Variable m_rhs; //Operator的right RuleVariable
    protected Operator m_operator;
    protected String m_name; //Condition的名称
    protected InferenceEngine m_engine = null;

    private static Boolean FALSE = new Boolean(false);

    RuleCondition() {}

    public RuleCondition(RuleVariable lhs, Operator op, Variable rhs) {
        m_lhs = lhs;
        m_operator = op;
        m_rhs = rhs;
        m_lhs.addRuleCondition(this);
    }

    public RuleCondition(RuleVariable lhs, Operator op, String rhsStr) {
        m_lhs = lhs;
        m_operator = op;
        m_rhs = Variable.newSimpleVariable("", Variable.TEXT, rhsStr);
        m_lhs.addRuleCondition(this);
    }

    public void setName(String name) {
        m_name = name;
    }

    public String getName() {
        return m_name;
    }

    public List<Rule> ruleRefs() {
        return ruleRefs;
    }

    public RuleVariable lhs() {
        return m_lhs;
    }

    public Variable rhs() {
        return m_rhs;
    }

    public Operator cond() {
        return m_operator;
    }

    public Boolean isTrue() {
        return m_truth;
    }

    public void isTrue(Boolean b) {
        m_truth = b;
    }

    public void reset() {
        m_truth = null;
    }

    public void setEngine(InferenceEngine engine) {
        m_engine = engine;
    }

    public void addRuleRef(Rule ref) {
        if (!ruleRefs.contains(ref)) {
            ruleRefs.add(ref);
        }
    }

    /* It performes a test of the clause. If the clause is used as a consequent
     * clause, then testing it truth value makes no sense. We return a null value. If the
     * variable on the left hand side is unbound, we also return a null, because a true value
     * cannot be determined. If the variable is bound, we use the switch statement to test the
     * specified logic condition and return the resulting true value.
     */
    //判断Condition是否正确
    public Boolean check() {
        //System.out.println("Condition check:" + m_lhs.getName() + "," + m_rhs.getName() + "," + getName());
        Object lvalue = m_lhs.getValue();
        boolean lhsIsArray = false; //left value 是否为数组
        boolean rhsIsArray = false; //right value 是否为数组
        if (lvalue != null && lvalue.getClass().isArray()) {
            lhsIsArray = true;
            lvalue = dealWithArray2(lvalue);
        }
        Object rvalue = m_rhs.getValue();
        if (rvalue != null && rvalue.getClass().isArray()) {
            rhsIsArray = true;
            rvalue = dealWithArray2(rvalue);
        }
        if (lvalue == null || rvalue == null) {
            return (m_truth = null); // can't check if variable value is undefined
        }
        if (!matchType(m_lhs.getDataType(), m_rhs.getDataType())) {
            return (m_truth = null); // can't check if variable type doesn't match.
        }
        int i;
        switch (m_lhs.getDataType()) {
            case Variable.INT:
            case Variable.FLOAT:
            case Variable.LONG:
            case Variable.DOUBLE:
            case Variable.NUMBER: {
                if(!lhsIsArray&&!rhsIsArray){
                    m_truth = new Boolean(m_operator.eval( (Number) lvalue, (Number) rvalue));
                }else if(!lhsIsArray&&rhsIsArray){
                    Number[] rhsValue = (Number[])rvalue;
                    for(i=0;i<rhsValue.length;i++){
                        m_truth = new Boolean(m_operator.eval( (Number) lvalue, rhsValue[i]));
                        if(m_truth==FALSE){
                            break;
                        }
                    }
                }else if(lhsIsArray&&!rhsIsArray){
                    Number[] lhsValue = (Number[])lvalue;
                    for(i=0;i<lhsValue.length;i++){
                        m_truth = new Boolean(m_operator.eval( lhsValue[i], (Number) rvalue));
                        if(m_truth==FALSE){
                            break;
                        }
                    }
                }else if(lhsIsArray&&rhsIsArray){
                    Number[] lhsValue = (Number[])lvalue;
                    Number[] rhsValue = (Number[])rvalue;
                    if(lhsValue.length!=rhsValue.length){
                        return (m_truth = null);
                    }else{
                        for (i = 0; i < lhsValue.length; i++) {
                            m_truth = new Boolean(m_operator.eval(lhsValue[i], rhsValue[i]));
                            if (m_truth == FALSE) {
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case Variable.TEXT: {
                if(!lhsIsArray&&!rhsIsArray){
                    m_truth = new Boolean(m_operator.eval( (String) lvalue, (String) rvalue));
                }else if(!lhsIsArray&&rhsIsArray){
                    String[] rhsValue = (String[])rvalue;
                    for(i=0;i<rhsValue.length;i++){
                        m_truth = new Boolean(m_operator.eval( (String) lvalue, rhsValue[i]));
                        if(m_truth==FALSE){
                            break;
                        }
                    }
                }else if(lhsIsArray&&!rhsIsArray){
                    String[] lhsValue = (String[])lvalue;
                    for(i=0;i<lhsValue.length;i++){
                        m_truth = new Boolean(m_operator.eval( lhsValue[i], (String) rvalue));
                        if(m_truth==FALSE){
                            break;
                        }
                    }
                }else if(lhsIsArray&&rhsIsArray){
                    String[] lhsValue = (String[])lvalue;
                    String[] rhsValue = (String[])rvalue;
                    if(lhsValue.length!=rhsValue.length){
                        return (m_truth = null);
                    }else{
                        for (i = 0; i < lhsValue.length; i++) {
                            m_truth = new Boolean(m_operator.eval(lhsValue[i], rhsValue[i]));
                            if (m_truth == FALSE) {
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case Variable.DATE: {
                if(!lhsIsArray&&!rhsIsArray){
                    m_truth = new Boolean(m_operator.eval( (Date) lvalue, (Date) rvalue));
                }else if(!lhsIsArray&&rhsIsArray){
                    Date[] rhsValue = (Date[])rvalue;
                    for(i=0;i<rhsValue.length;i++){
                        m_truth = new Boolean(m_operator.eval( (Date) lvalue, rhsValue[i]));
                        if(m_truth==FALSE){
                            break;
                        }
                    }
                }else if(lhsIsArray&&!rhsIsArray){
                    Date[] lhsValue = (Date[])lvalue;
                    for(i=0;i<lhsValue.length;i++){
                        m_truth = new Boolean(m_operator.eval( lhsValue[i], (Date) rvalue));
                        if(m_truth==FALSE){
                            break;
                        }
                    }
                }else if(lhsIsArray&&rhsIsArray){
                    Date[] lhsValue = (Date[])lvalue;
                    Date[] rhsValue = (Date[])rvalue;
                    if(lhsValue.length!=rhsValue.length){
                        return (m_truth = null);
                    }else{
                        for (i = 0; i < lhsValue.length; i++) {
                            m_truth = new Boolean(m_operator.eval(lhsValue[i], rhsValue[i]));
                            if (m_truth == FALSE) {
                                break;
                            }
                        }
                    }
                }
                break;
            }
            default: {
                m_truth = null;
            }
        }
        return m_truth;
    }

    /* It performes a test of the clause. If the clause is used as a consequent
     * clause, then testing it truth value makes no sense. We return a null value. If the
     * variable on the left hand side is unbound, we also return a null, because a true value
     * cannot be determined. If the variable is bound, we use the switch statement to test the
     * specified logic condition and return the resulting true value.
     */
    //判断Condition是否正确,这是旧的做法,对于数组,只是返回数组的第一个值
    /*
         public Boolean check() {
        //System.out.println("Condition check:" + m_lhs.getName() + "," + m_rhs.getName() + "," + getName());
        Object lvalue = m_lhs.getValue();
        if(lvalue!=null && lvalue.getClass().isArray())
            lvalue = dealWithArray(lvalue);

        Object rvalue = m_rhs.getValue();
        if(rvalue!=null && rvalue.getClass().isArray())
            rvalue = dealWithArray(rvalue);
        if (lvalue==null || rvalue==null) {
            return (m_truth = null); // can't check if variable value is undefined
        } else {
            if(!matchType(m_lhs.getDataType(),m_rhs.getDataType()))
                return (m_truth = null); // can't check if variable type doesn't match.

            switch(m_lhs.getDataType()) {
                case Variable.INT:
                case Variable.FLOAT:
                case Variable.LONG:
                case Variable.DOUBLE:
                case Variable.NUMBER: {
                    m_truth = new Boolean(m_operator.eval((Number)lvalue, (Number)rvalue));
                    break;
                }
                case Variable.TEXT: {
                    m_truth = new Boolean(m_operator.eval((String)lvalue, (String)rvalue));
                    break;
                }
                case Variable.DATE: {
                    m_truth = new Boolean(m_operator.eval((Date)lvalue, (Date)rvalue));
                    break;
                }
                default:{
                    m_truth = null;
                }
            }
            return m_truth;
        }
         }*/

    //如果是Array,暂时取[0]的值返回
    //如果是Array,将整个Array返回
    private Object[] dealWithArray2(Object array) {
        Object[] valueArray = null;
        int len;
        int i;
        if (array instanceof Object[]) {
            Object[] values = (Object[]) array;
            if (values.length == 0) {
                return null;
            }
            valueArray = values;
        }
        else if (array instanceof int[]) {
            int[] values = (int[]) array;
            len = values.length;
            if (len == 0) {
                return null;
            }
            valueArray = new Integer[len];
            for (i = 0; i < len; i++) {
                valueArray[i] = new Integer(values[i]);
            }
        }
        else if (array instanceof float[]) {
            float[] values = (float[]) array;
            len = values.length;
            if (len == 0) {
                return null;
            }
            valueArray = new Float[len];
            for (i = 0; i < len; i++) {
                valueArray[i] = new Float(values[i]);
            }
        }
        else if (array instanceof long[]) {
            long[] values = (long[]) array;
            len = values.length;
            if (len == 0) {
                return null;
            }
            valueArray = new Long[len];
            for (i = 0; i < len; i++) {
                valueArray[i] = new Long(values[i]);
            }
        }
        else if (array instanceof double[]) {
            double[] values = (double[]) array;
            len = values.length;
            if (len == 0) {
                return null;
            }
            valueArray = new Double[len];
            for (i = 0; i < len; i++) {
                valueArray[i] = new Double(values[i]);
            }
        }
        return valueArray;
    }

    private boolean matchType(int ltype, int rtype) {
        if (ltype > 3) {
            ltype = 1;
        }
        if (rtype > 3) {
            rtype = 1;
        }
        return (ltype == rtype);
    }
}
