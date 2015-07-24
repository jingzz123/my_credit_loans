package com.intellicredit.platform.component.situation2;

import java.util.*;
import com.intellicredit.platform.component.transaction.Schema;
import com.intellicredit.platform.service.rule.forward2.InferenceEngine;

/**
 * The variable in System. Different type of variables have different type of sources.
 * Variable get value from their source.
 *
 * @author Wallace
 */
public class Variable {
    public static final int NUMBER = 1;
    public static final int TEXT = 2;
    public static final int DATE = 3;

    public static final  int INT = 4;
    public static final  int FLOAT = 5;
    public static final  int LONG = 6;
    public static final  int DOUBLE = 7;

    public static final int SIMPLE = 101;
    public static final int EXTERNAL = 102;
    public static final int TRANSITION = 103;
    public static final int ALIAS = 104;
    public static final int SCRIPT = 105;
    public static final int DATABASE = 106;
    public static final int EXPRESSION = 107;

    public static EmptyValue EMPTY = new EmptyValue();

    /*public static ThreadLocal m_local = new ThreadLocal(){
        protected Object initialValue() {
            return new EmptyValue();
        }
    };*/

    private String m_name;

    /**
     * The source type of variable. e.g. SIMPLE, EXTERNAL, etc.
     */
    private int m_type;

    /**
     * The datatype of value. e.g. NUMBER, TEXT, DATE.
     */
    private int m_dataType;

    /**
     * The data source for variable to extract value.
     */
    private Source m_source;

    /**
     * Construct a variable by its parameters.
     *
     * @param name         the name of variable
     * @param type         the source type of variable. e.g. SIMPLE, EXTERNAL, etc.
     * @param datatype     the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param otherParams  other parameters that should be passed to this variable.<br>
     *                        SIMPLE ----- 1. initValue (optional)<br>
     *
     *                        EXTERNAL ----- 1. attribute name
     *                                       2. defaultValue (optional)<br>
     *
     *                        TRANSACTION ----- 1. field
     *                                          2. subId<br>
     *
     *                        ALIAS ----- 1. aliasName
     *                                    2. objectName
     *                                    3. argNames<br>
     *
     *                        SCRIPT ----- 1. scriptString<br>
     *
     *                        EXPRESSION ----- 1. expStr
     *                                         2. addStdFun<br>
     */
    public Variable(String name, int type, int dataType, Object[] otherParams) {
        m_name = name;
        m_type = type;
        m_dataType = dataType;

        //construct data source according to their type.
        if(type==SIMPLE) {
            m_source = new SimpleSource(name);
            ((SimpleSource)m_source).setEngine((InferenceEngine)otherParams[0]);
            if(otherParams.length>1) {
                ((SimpleSource)m_source).setInitValue(otherParams[1]);
            }
        } else if(type==EXTERNAL) {
            if(otherParams==null || otherParams.length<2)
                throw new IllegalArgumentException("otherParams must pass parameters: attributeName");
            m_source = new ExternalSource((String)otherParams[0]);
            ((ExternalSource)m_source).setEngine((InferenceEngine)otherParams[1]);
            if(otherParams.length>2)
                ((ExternalSource)m_source).setDefaultValue(otherParams[2]);
        } else if(type==TRANSITION) {
            if(otherParams==null || otherParams.length<4)
                throw new IllegalArgumentException("otherParams must pass two parameters: field and subid");
            m_source = new TransactionSource(dataType,(String)otherParams[0], (String)otherParams[1]);
            ((TransactionSource)m_source).setSchema((Schema)otherParams[2]);
            ((TransactionSource)m_source).setEngine((InferenceEngine)otherParams[3]);
        } else if(type==ALIAS) {
            if(otherParams==null || otherParams.length<4)
                throw new IllegalArgumentException("otherParams must pass three parameters: aliasName, objectName and argNames");
            m_source = new AliasSource((String)otherParams[0],(String)otherParams[1], (String[])otherParams[2]);
            ((AliasSource)m_source).setSituation((Situation)otherParams[3]);
        } else if(type==SCRIPT) {//暂时没有，去除了
            if(otherParams==null || otherParams.length<2)
                throw new IllegalArgumentException("otherParams must pass parameter: scriptString");
            m_source = new ScriptSource((String)otherParams[0]);
            //((ScriptSource)m_source).setSituation((Situation)otherParams[1]);
        } else if(type==EXPRESSION) {
            if(otherParams==null || otherParams.length<3)
                throw new IllegalArgumentException("otherParams must pass two parameters: expStr and addStdFun");
            ExpressionSource source = (ExpressionSource)((InferenceEngine)otherParams[1]).getInstanceMap().get((String)otherParams[0]);
            if(source!=null){
                m_source = source;
            }else{
                m_source = ExpressionSource.getInstance(dataType, (String) otherParams[0], (InferenceEngine)otherParams[1],( (Boolean) otherParams[2]).booleanValue());
            }
        }
    }

    /**
     * Construct a new simple variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @return the variable created
     */
    public static Variable newSimpleVariable(String name, int dataType) {
        return new Variable(name, SIMPLE, dataType, null);
    }

    /**
     * Construct a new simple variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param initValue  the initial value of variable
     * @return the variable created
     */
    public static Variable newSimpleVariable(String name, int dataType, Object initValue) {
        return new Variable(name, SIMPLE, dataType, new Object[] {initValue});
    }

    /**
     * Construct a new external variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param attrName   the attribute name used to search for value
     * @return the variable created
     */
    public static Variable newExternalVariable(String name, int dataType, String attrName) {
        return new Variable(name, EXTERNAL, dataType, new Object[] {attrName});
    }

    /**
     * Construct a new external variable.
     * @param name           the name of variable
     * @param dataType       the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param attrName   the attribute name used to search for value
     * @param defaultValue   the default value which should be returned when no Situation
     *                        associated with ExternalSource or attribute name not found
     *                        in Situaion
     * @return the variable created
     */
    public static Variable newExternalVariable(String name, int dataType, String attrName, Object defaultValue) {
        return new Variable(name, EXTERNAL, dataType, new Object[] {attrName, defaultValue});
    }

    /**
     * Construct a new transaction variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param field      the column name in the transaction
     * @param subId      the id of segment in the transaction.
     *                    It is the first column name in the segment.
     * @return the variable created
     */
    public static Variable newTransactionVariable(String name, int dataType, String field, String subId) {
        return new Variable(name, TRANSITION, dataType, new Object[] {field, subId});
    }

    /**
     * Construct a new alias variable.
     * @param name        the name of variable
     * @param dataType    the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param aliasName   the name of method to be invoked
     * @param objName     the name of object on which method invokes
     * @param argNames    the arguments to be passed into method
     * @return the variable created
     */
    public static Variable newAliasVariable(String name, int dataType, String aliasName, String objectName, String[] argNames) {
        return new Variable(name, ALIAS, dataType, new Object[] {aliasName, objectName, argNames});
    }

    /**
     * Construct a new script variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param script     the script string
     * @return the variable created
     */
    public static Variable newScriptVariable(String name, int dataType, String script) {
        return new Variable(name, SCRIPT, dataType, new Object[] {script});
    }

    /**
     * Construct a new expression variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param expStr     the expression string
     * @return the variable created
     */
    public static Variable newExpressionVariable(String name, int dataType, String expStr,InferenceEngine engine) {
        //return new Variable(name, EXPRESSION, dataType, new Object[] {expStr, Boolean.valueOf(true)});
        return new Variable(name, EXPRESSION, dataType, new Object[] {expStr, engine,Boolean.valueOf(true)});
    }

    /**
     * Construct a new expression variable.
     * @param name       the name of variable
     * @param dataType   the data type of variable. e.g. NUMBER, TEXT, DATE
     * @param expStr     the expression string
     * @param addStdFun  specify whether add standard functions
     * @return the variable created
     */
    public static Variable newExpressionVariable(String name, int dataType, String expStr,InferenceEngine engine, boolean addStdFun) {
        return new Variable(name, EXPRESSION, dataType, new Object[] {expStr, engine, Boolean.valueOf(addStdFun)});
    }

    public String getName() {
        return m_name;
    }

    public int getSourceType() {
        return m_type;
    }

    public int getDataType() {
        return m_dataType;
    }

    public Source getSource() {
        return m_source;
    }

    /**
     * Get the value of variable.
     *
     * @return the value of variable
     */
    public Object getValue() {
        if(m_source==null)
            throw new IllegalStateException("Error! Source have not been initialized!");
        return m_source.getValue();
    }

    public Number getNumberValue() throws ClassCastException {
        return (Number)getValue();
    }

    public String getTextValue() throws ClassCastException {
        return getValue().toString();
    }

    public Date getDateValue() throws ClassCastException {
        return (Date)getValue();
    }

    public void setValue(Object value) {
        if(getSourceType()!=SIMPLE)
            return;
        ((SimpleSource)getSource()).setValue(value);
    }

    /**
     * reset the source.
     */
    public void reset() {
        if(m_source==null)
            throw new IllegalStateException("Error! Source have not been initialized!");
        m_source.reset();
    }

    private static class EmptyValue {}
}
