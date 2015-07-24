package com.intellicredit.platform.component.situation2;

import java.util.*;
import java.text.SimpleDateFormat;
import com.intellicredit.platform.component.expression.*;
import com.intellicredit.platform.util.CalendarUtil;
import com.intellicredit.platform.service.rule.forward2.InferenceEngine;
import com.intellicredit.platform.service.rule.forward2.RuleUtil;
import com.intellicredit.platform.service.rule.forward2.RuleVariable;

/**
 * The source that get value from expression.
 *
 * @author wallace
 */

public class ExpressionSource implements Source {

    /**
     * The map which ExpressionSource search variables from.
     */
    private InferenceEngine m_engine;//now get m_variablesMap and m_situation from it

    /**
     * The situation which ExpressionSource search variables from.
     */

    /**
     * The data type of ExpressionSource. e.g. NUMBER, TEXT, DATE.
     */
    private int m_type;

    /**
     * Buffer the value as the result of evalution.
     */
    private Object m_value;

    /**
     * The reference of JEP expression parser.
     */
    private Expression m_jep;

    /**
     * The expression string to pasrse.
     */
    private String m_expStr;

    /**
     * All the variables in the expression string that are preceded by a '$' char.
     */
    private String[] m_vars;

    /**
     * indicate whether only search variables in Situation.
     */
    private boolean m_onlySearchSituation;

    private Map<String,Object> m_varMap = new HashMap<String,Object>();

    private static NullObject nullObject = new NullObject();

    /**
     * Construct a new ExpressionSource by given expStr.
     * @param expStr   the expression string
     */
    public ExpressionSource(String expStr) {
        this(Variable.TEXT, expStr, true);
    }

    /**
     * Construct a new ExpressionSource by given type and expStr.
     * @param type     the data type of Source
     * @param expStr   the expression string
     */
    public ExpressionSource(int type, String expStr) {
        this(type, expStr, true);
    }

    /**
     * Construct a new ExpressionSource by given expStr and addStdFun.
     * @param expStr      the expression string
     * @param addStdFun   specify whether add standard functions
     */
    public ExpressionSource(String expStr, boolean addStdFun) {
        this(Variable.TEXT, expStr, addStdFun);
    }

    /**
     * Construct a new ExpressionSource by given type, expStr and addStdFun.
     * @param type        the data type of Source
     * @param expStr      the expression string
     * @param addStdFun   specify whether add standard functions
     */
    public ExpressionSource(int type, String expStr, boolean addStdFun) {
        m_type = type;
        m_value = null;
        m_jep = new Expression();
        m_jep.setAllowUndeclared(true);
        if (addStdFun) {
            m_jep.addStandardFunctions();
            RuleUtil.addUserFunction(m_jep);
        }
        m_onlySearchSituation = false;

        setExpression(expStr);
    }

    public static ExpressionSource getInstance(int type, String expStr, InferenceEngine engine,boolean addStdFun) {
        ExpressionSource source = new ExpressionSource(type, expStr, addStdFun);
        source.setEngine(engine);
        engine.getInstanceMap().put(expStr, source);
        return source;
    }

    public void setEngine(InferenceEngine engine) {
        m_engine = engine;
    }

    public InferenceEngine getEngine() {
        return m_engine;
    }

    public Map<String,RuleVariable> getVariablesMap() {
        return m_engine.getVariablesMap();
    }

    public String[] getVars() {
        return m_vars;
    }

    public void setOnlySearchSituation(boolean b) {
        m_onlySearchSituation = b;
    }

    //利用expression.Expression解析expStr,同时得到相关联的变量
    public boolean setExpression(String expStr) {
        this.m_expStr = expStr;
        if (expStr == null) {
            m_value = null;
            return true;
        }
        SymbolTable st;
        switch (m_type) {
            case Variable.DATE:
                m_vars = null;
                SimpleDateFormat dt1 = CalendarUtil.simpleDateTimeFormatter != null ?
                    CalendarUtil.simpleDateTimeFormatter : CalendarUtil.defaultSimpleDateTimeFormatter;
                SimpleDateFormat dt2 = CalendarUtil.simpleDateFormatter != null ?
                    CalendarUtil.simpleDateFormatter : CalendarUtil.defaultSimpleDateFormatter;
                try {
                    m_value = dt1.parse(expStr);
                } catch (java.text.ParseException e) {
                    try {
                        m_value = dt2.parse(expStr);
                    } catch (java.text.ParseException ee) {
                        m_jep.initSymTab();
                        m_jep.parseExpression(expStr);
                        if (m_jep.hasError()) {
                            return false;
                        }
                        st = m_jep.getSymbolTable();
                        //if expression has no variable, as of Date type, it has no sense.
                        if (st.isEmpty()) {
                            m_vars = null;
                            m_value = m_jep.getValueAsObject();
                        } else {//if expression has variable-s,变量以$开头
                            Set<String> varSet = new HashSet<String>();
                            for (Iterator<?> it = st.keySet().iterator();
                                 it.hasNext(); ) {
                                String var = (String) it.next();
                                if (var.charAt(0) == '$') {
                                    varSet.add(var.substring(1));
                                }
                            }
                            m_vars = (String[]) varSet.toArray(new String[0]);
                        }
                    }
                }
                return true;

            case Variable.TEXT:
                m_jep.initSymTab();
                m_jep.parseExpression(expStr);
                if (m_jep.hasError()) {
                    return false;
                }

                st = m_jep.getSymbolTable();
                //if expression has no variable, calculate its value and put to buffer.
                if (st.isEmpty()) {
                    m_vars = null;
                    m_value = m_jep.getValueAsObject().toString();
                } else {
                    Set<String> varSet = new HashSet<String>();
                    for (Iterator<?> it = st.keySet().iterator(); it.hasNext(); ) {
                        String var = (String) it.next();
                        if (var.charAt(0) == '$') {
                            varSet.add(var.substring(1));
                        } else {
                            m_jep.addVariableAsObject(var, var);
                        }
                    }
                    m_vars = (String[]) varSet.toArray(new String[0]);
                    if (m_vars.length == 0) {
                        m_vars = null;
                        m_value = m_jep.getValueAsObject().toString();
                    }
                }
                return true;

            case Variable.NUMBER:
            case Variable.INT:
            case Variable.FLOAT:
            case Variable.LONG:
            case Variable.DOUBLE:
                m_jep.initSymTab();
                m_jep.parseExpression(expStr);
                if (m_jep.hasError()) {
                    return false;
                }

                st = m_jep.getSymbolTable();
                //if expression has no variable, calculate its value and put to buffer.
                if (st.isEmpty()) {
                    m_vars = null;
                    m_value = m_jep.getValueAsObject();
                } else {
                    Set<String> varSet = new HashSet<String>();
                    for (Iterator<?> it = st.keySet().iterator(); it.hasNext(); ) {
                        String var = (String) it.next();
                        if (var.charAt(0) == '$') {
                            varSet.add(var.substring(1));
                        }
                    }
                    m_vars = (String[]) varSet.toArray(new String[0]);
                }
                return true;
        }
        return false;
    }

    public Object getValue() {
        if (m_expStr == null) {
            return null;
        }
        //if m_vars==null, means expression has no variable. Get value from buffer.
        if (m_vars == null) {
            return m_value;
        } else {
            //if all m_vars value have not been changed since last getValue(), get value from buffer.
            //otherwise calculate the value using jep.
            boolean needParse = false;
            boolean returnNull = false;
            for (int i = 0; i < m_vars.length; i++) {
                //get last value
                Object oldValue = m_varMap.get(m_vars[i]);

                //get current value
                Object value;
                Object val;
                if (!m_onlySearchSituation) {
                    //先从m_variablesMap去取值,如果没有,然后从m_situation去取值
                    //Variable v = (Variable)m_variablesMap.get(m_vars[i]);
                    Map<String,RuleVariable> variablesMap = m_engine.getVariablesMap();
                    Variable v = (Variable)variablesMap.get(m_vars[i]);
                    if (v != null) {
                        val = v.getValue();
                        if (val == null) {
                            returnNull = true;
                            value = nullObject;
                        } else {
                            if (m_type == Variable.TEXT) {
                                value = (val instanceof String[]) ? val : val.toString();
                            } else {
                                value = val;
                            }
                        }
                    } else {
                        //val = (m_situation == null) ? null : m_situation.getAttribute(m_vars[i]);
                        Situation situation = m_engine.getSituation();
                        val = (situation == null) ? null : situation.getAttribute(m_vars[i]);
                        if (m_type == Variable.TEXT) {
                            value = (val == null) ? "" : val.toString();
                        } else if (m_type == Variable.DATE) {
                            value = (val == null) ? RuleUtil.getMinDate() : val;
                        } else {
                            value = (val == null) ? new Double(0) : val;
                        }
                    }
                } else {
                    //val = (m_situation == null) ? null : m_situation.getAttribute(m_vars[i]);
                    Situation situation = m_engine.getSituation();
                    val = (situation == null) ? null : situation.getAttribute(m_vars[i]);
                    if (m_type == Variable.TEXT) {
                        value = (val == null) ? "" : val.toString();
                    } else if (m_type == Variable.DATE) {
                        value = (val == null) ? RuleUtil.getMinDate() : val;
                    } else {
                        value = (val == null) ? new Double(0) : val;
                    }
                }

                //在变量的值变化的情况下: 向m_varMap中put变量的当前值
                if (oldValue == null || !oldValue.equals(value)) {
                    if (oldValue instanceof Object[] && value instanceof Object[]) {
                        needParse = !Arrays.equals( (Object[]) oldValue, (Object[]) value);
                        if (needParse) {
                            m_varMap.put(m_vars[i], value);
                        }
                    } else if (oldValue instanceof int[] && value instanceof int[]) {
                        needParse = !Arrays.equals( (int[]) oldValue, (int[]) value);
                        if (needParse) {
                            m_varMap.put(m_vars[i], value);
                        }
                    } else if (oldValue instanceof float[] && value instanceof float[]) {
                        needParse = !Arrays.equals( (float[]) oldValue, (float[]) value);
                        if (needParse) {
                            m_varMap.put(m_vars[i], value);
                        }
                    } else if (oldValue instanceof long[] && value instanceof long[]) {
                        needParse = !Arrays.equals( (long[]) oldValue, (long[]) value);
                        if (needParse) {
                            m_varMap.put(m_vars[i], value);
                        }
                    } else if (oldValue instanceof double[] && value instanceof double[]) {
                        needParse = !Arrays.equals( (double[]) oldValue, (double[]) value);
                        if (needParse) {
                            m_varMap.put(m_vars[i], value);
                        }
                    } else {
                        needParse = true;
                        m_varMap.put(m_vars[i], value);
                    }
                }
            }

            //如果Expression中包含的一个变量没有值,那整个Expression值为空
            if (returnNull) {
                return null;
            }
            //System.out.println("needParse: "+ needParse + "," + m_expStr);
            if (needParse) {
                for (int i = 0; i < m_vars.length; i++) {
                    //if(!m_onlySearchSituation) {
                    //    Variable v = (Variable)m_variablesMap.get(m_vars[i]);
                    //    if(v!=null) {
                    //        Object val = v.getValue();
                    //        if(val==null)
                    //            return null;
                    //        if(m_type==Variable.TEXT)
                    //            m_jep.addVariableAsObject('$'+m_vars[i],(val instanceof String[])? val : val.toString());
                    //        else {
                    //            m_jep.addVariableAsObject('$'+m_vars[i],val);
                    //        }
                    //        continue;
                    //    }
                    //}
                    //Object value = (m_situation==null) ? null : m_situation.getAttribute(m_vars[i]);
                    //if(m_type==Variable.TEXT)
                    //    m_jep.addVariableAsObject('$'+m_vars[i],value==null?"":value.toString());
                    //else if(m_type==Variable.DATE)
                    //    m_jep.addVariableAsObject('$'+m_vars[i],value==null?new Date(0,0,1):value);
                    //else
                    //    m_jep.addVariableAsObject('$'+m_vars[i],value==null?new Double(0):value);
                    m_jep.addVariableAsObject('$' + m_vars[i], m_varMap.get(m_vars[i]));
                }
                m_value = convert(m_jep.getValueAsObject());
            }
            return m_value;
        }
    }

    public void reset() {}

    //根据value的不同类型，将其转化为相应类型的对象
    private Object convert(Object value) {
        Object result = value;
        if (m_type == Variable.INT) {
            if (value instanceof Double) {
                result = new Integer( ( (Number) value).intValue());
            } else if (value instanceof String) {
                result = Integer.valueOf( (String) value);
            }
        } else if (m_type == Variable.FLOAT) {
            if (value instanceof Double) {
                result = new Float( ( (Number) value).floatValue());
            } else if (value instanceof String) {
                result = Float.valueOf( (String) value);
            }
        } else if (m_type == Variable.LONG) {
            if (value instanceof Double) {
                result = new Long( ( (Number) value).longValue());
            } else if (value instanceof String) {
                result = Long.valueOf( (String) value);
            }
        } else if (m_type == Variable.DOUBLE || m_type == Variable.NUMBER) {
            if (value instanceof String) {
                result = Double.valueOf( (String) value);
            }
        } else if (m_type == Variable.TEXT) {
            result = String.valueOf(value);
        }
        return result;
    }

    public static class NullObject {}
}
