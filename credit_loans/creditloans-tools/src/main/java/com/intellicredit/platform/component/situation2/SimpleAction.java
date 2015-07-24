package com.intellicredit.platform.component.situation2;

public class SimpleAction implements Action {
    
    String m_name;
    Variable m_variable;
    Object m_value;

    public SimpleAction(String name, Variable variable, Object value) {
        m_name = name;
        m_variable = variable;
        m_value = value;
    }
    
    public String getName() {
        return m_name;
    }
    
    /**
     * @return   the error code as the result of execution.<br>
     *            0 ----- no error.<br>
     *            1 ----- variable is NULL.<br>
     *            2 ----- variable is not type of simple variable.
     */
    public int go() {
        if(m_variable==null)
            return 1;
        
        if(m_variable.getSourceType()!=Variable.SIMPLE)
            return 2;
        
        m_variable.setValue(m_value);
        return 0;
    }
}
