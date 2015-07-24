package com.intellicredit.platform.component.situation2;

/**
 * The source that get value from alias. Alias represents a method name.
 * To use this source we should give the alias, the object to invoke, and
 * the argument to be passed into the method.
 *
 * @author wallace
 */
//实现通过调用某个class的method得到值
public class AliasSource implements Source {

    /**
     * The situation which stores object and method names.
     */
    private Situation m_situation;

    /**
     * The name of object on which method invokes.
     */
    private String m_objectName;

    /**
     * The object on which method invokes.
     */
    private Object m_object;

    /**
     * The name of method to be invoked.
     */
    private String m_aliasName;

    /**
     * The arguments to be passed into method.
     */
    private String[] m_argumentNames;

    /**
     * Buffer the value as the result of invocation.
     */
    private Object m_value;

    /**
     * indicate whether to buffer the value.
     */
    private boolean m_useCache;

    /**
     * Construct a new AliasSource by given parameters.
     *
     * @param aliasName   the name of method to be invoked
     * @param objName     the name of object on which method invokes
     * @param argNames    the arguments to be passed into method
     */
    public AliasSource(String aliasName, String objName, String[] argNames) {
        m_aliasName = aliasName;
        m_objectName = objName;
        m_object = null;
        m_argumentNames = argNames;

        m_value = null;
        m_useCache = false;
    }

    /**
     * Construct a new AliasSource by given parameters.
     *
     * @param aliasName   the name of method to be invoked
     * @param obj         the object on which method invokes
     * @param argNames    the arguments to be passed into method
     */
    public AliasSource(String aliasName,Object obj, String[] argNames) {
        m_aliasName = aliasName;
        m_objectName = null;
        m_object = obj;
        m_argumentNames = argNames;

        m_value = null;
        m_useCache = false;
    }

    public void setSituation(Situation situation) {
        m_situation = situation;
    }

    public void setUseCache(boolean useCache) {
        m_useCache = useCache;
    }

    public Object getValue() {
        if(m_useCache && m_value!=null)
        	return m_value;

        ArgumentHolder args = new ArgumentHolder();
        for(int i = 0; i < m_argumentNames.length; i++)
            args.setArgument(m_situation.getAttribute(m_argumentNames[i]));
        try {
            if(m_object == null) {
                m_value =  m_situation.getAliasAttribute(m_aliasName, m_situation.getAttribute(m_objectName), args);
            }
            else {
                m_value =  m_situation.getAliasAttribute(m_aliasName, m_object, args);
            }
        }
        catch (Exception ee) {
            m_value = null;
        }
        return m_value;
    }

    public void reset() {
        m_value = null;
    }

}
