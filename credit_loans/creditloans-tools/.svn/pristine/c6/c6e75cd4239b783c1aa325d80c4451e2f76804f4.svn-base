package com.intellicredit.platform.service.rule.forward2;

import java.lang.reflect.Method;
import java.util.*;

import com.intellicredit.platform.component.expression.ASTVarNode;
import com.intellicredit.platform.component.expression.Expression;
import com.intellicredit.platform.component.situation2.ArgumentHolder;
import com.intellicredit.platform.component.situation2.Situation;
import com.intellicredit.platform.component.situation2.Variable;
import com.intellicredit.platform.util.ClassUtil;

//在BaseDef中会设定一个"FunCall" Action，调用某个class生成对象的某个方法得到某个值，
//最后将所得到的值返回给一个变量，方法名，参数名及其参数值均是在Situation中定义，因此它所用到的参数均是Situation变量。
//优化：以后必须加入的功能是：它的参数可以使用其它类型变量及其值。
public class FunCallAction extends RuleAction {

    private static Map<String,Method> m_methodMap = new HashMap<String,Method>();

    private static Integer INT_TYPE = new Integer(Variable.INT);
    private static Integer FLOAT_TYPE = new Integer(Variable.FLOAT);
    private static Integer LONG_TYPE = new Integer(Variable.LONG);
    private static Integer DOUBLE_TYPE = new Integer(Variable.DOUBLE);
    private static Integer TEXT_TYPE = new Integer(Variable.TEXT);
    private static Integer DATE_TYPE = new Integer(Variable.DATE);
    @SuppressWarnings("rawtypes")
	private static final Map<Class, Integer> JAVATYPE_TO_SCHEMATYPE = Collections
			.unmodifiableMap(new HashMap<Class,Integer>() {
		private static final long serialVersionUID = 6540960972564117487L;
		{
            put(int.class, INT_TYPE);
            put(int[].class, INT_TYPE);
            put(Integer.class, INT_TYPE);
            put(Integer[].class, INT_TYPE);

            put(float.class, FLOAT_TYPE);
            put(float[].class, FLOAT_TYPE);
            put(Float.class, FLOAT_TYPE);
            put(Float[].class, FLOAT_TYPE);

            put(long.class, LONG_TYPE);
            put(long[].class, LONG_TYPE);
            put(Long.class, LONG_TYPE);
            put(Long[].class, LONG_TYPE);

            put(double.class, DOUBLE_TYPE);
            put(double[].class, DOUBLE_TYPE);
            put(Double.class, DOUBLE_TYPE);
            put(Double[].class, DOUBLE_TYPE);

            put(String.class, TEXT_TYPE);
            put(String[].class, TEXT_TYPE);

            put(Date.class, DATE_TYPE);
            put(Date[].class, DATE_TYPE);
        }
    });

    private RuleVariable m_variable;
    private Method m_method;
    private RuleVariable[] m_argvars;
    private String m_args;

    private Expression jep = new Expression();
    {
        jep.setAllowUndeclared(true);
        jep.addStandardFunctions();
    }

    public FunCallAction(String name, String className, String methodSignature,
                         RuleVariable var, String args) {
        super(name);

        if (className == null || methodSignature == null || var == null || args == null) {
            return;
        }

        String key = className + "." + methodSignature;
        m_method = (Method) m_methodMap.get(key);
        if (m_method == null) {
            try {
                m_method = ClassUtil.findMethod(className, methodSignature);
            } catch (Exception e) {
                IllegalArgumentException e1 = new IllegalArgumentException("Illegal Parameter: className or methodSignature!");
                e1.initCause(e);
                throw e1;
            }
            m_methodMap.put(key, m_method);
        }

        if (var.getSourceType() != Variable.EXTERNAL) {
            throw new IllegalArgumentException("Parameter var must be External Variable!");
        }
        m_variable = var;
        m_args = args;
    }

    public void go() {
        if (m_variable == null) {
            return;
        }

        ArgumentHolder holder = new ArgumentHolder();
        for (int i = 0; i < m_argvars.length; i++) {
            Situation situation = m_engine.getSituation();
            holder.setArgument(m_argvars[i] == null ? situation : m_argvars[i].getValue());
        }
        try {
            m_method.invoke(m_variable.getValue(), holder.getArguments());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<RuleCondition> getRuleConditions() {
        if (m_variable == null) {
            return super.getRuleConditions();
        }
        return m_variable.getRuleConditions();
    }

    public void parseArgs() {
        if (m_method == null || m_args == null) {
            return;
        }

        Class<?>[] paramTypes = m_method.getParameterTypes();
        m_argvars = new RuleVariable[paramTypes.length];

        StringTokenizer token = new StringTokenizer(m_args, ", ");
        try {
            for (int i = 0; i < paramTypes.length; i++) {
                String expression = token.nextToken();
                jep.parseExpression(expression);
                if (jep.getTopNode()instanceof ASTVarNode) {
                    String varName = ( (ASTVarNode) jep.getTopNode()).getName();
                    if (varName.charAt(0) == '$') {
                        varName = varName.substring(1);
                        if (varName.equals("situation")) {
                            continue;
                        }
                        Object val = m_engine.getVariablesMap().get(varName);
                        m_argvars[i] = (val instanceof RuleVariable) ? (RuleVariable) val : null;
                    }
                }
                if (m_argvars[i] == null) {
                    int type = ( (Integer) JAVATYPE_TO_SCHEMATYPE.get(paramTypes[i])).intValue();
                    m_argvars[i] = RuleVariable.newExpressionRuleVariable("arg", type, expression,m_engine);
                }
            }
        } catch (RuntimeException e) {
            IllegalArgumentException e1 = new IllegalArgumentException("Parameter methodSignature and args must be consistent!");
            e1.initCause(e);
            throw e1;
        }
    }

}
