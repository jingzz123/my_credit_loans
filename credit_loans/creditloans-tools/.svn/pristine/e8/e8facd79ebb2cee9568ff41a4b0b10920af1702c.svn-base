package com.intellicredit.platform.component.situation2;

import java.util.Date;
import com.intellicredit.platform.util.ComparableCalendar;
import com.intellicredit.platform.service.rule.forward2.InferenceEngine;

/**
 * The simple source that get value by specified name.
 * @author wallace
 */
public class SimpleSource implements Source {

    /*存放所有Simple变量的name和value的对应关系,key 是name, value是变量对应的值
      对于此中方法存放变量可能需要修改:因为这是一个static,所有如果两个Rule中有相同名称的Simple变量,
      如果两个Rule同时为一个程序所用,此两变量就会发生冲突,应该想出解决方法*/
    //private Map m_valueMap = new HashMap();//放到了InferenceEngine中
    private InferenceEngine m_engine;

    private String m_name;//变量的名称

    private Object m_initValue;//变量的初始值,一直保持不变

    private Object m_bufferValue;//当前变量的值,根据实际情况变化,同时会将其放入到m_valueMap中

    /**
     * Construct a new SimpleSource by given name.
     * @param name   the name to query value from source*/
    public SimpleSource(String name) {
        m_name = name;
        m_initValue = null;
        m_bufferValue = Variable.EMPTY;
    }

    /**
     * Construct a new SimpleSource by given name and initValue.
     * @param name        the name to query value from source
     * @param initValue   the initial value of SimpleSource
     */
    public SimpleSource(String name, Object initValue) {
        m_name = name;
        setInitValue(initValue);
    }

    public void setEngine(InferenceEngine engine){
        m_engine = engine;
    }

    public void addValue(String name, Object value) {
        m_engine.getSValueMap().put(name, value);
    }

    public void addNumberValue(String name, Number value) {
        addValue(name, (Number)value);
    }

    public void addTextValue(String name, String value) {
        addValue(name, (String)value);
    }

    public void addDateValue(String name, Date value) {
        addValue(name, (Date)value);
    }

    public void removeValue(String name) {
        //m_valueMap.remove(name);
        m_engine.getSValueMap().remove(name);
    }

    public String getName() {
        return m_name;
    }

    public Object getValue() {
        if(m_bufferValue!=Variable.EMPTY)
            return m_bufferValue;

        Object value = m_engine.getSValueMap().get(m_name);

        /*
         * because we cannot return ComparableCalendar to represent a data value,
         * we must convert it to java.util.Date.
         */
        if(value instanceof ComparableCalendar){
            value = ( (ComparableCalendar) value).getTime();
        }
        return value;
    }

    public void setInitValue(Object value) {
        setValue(value);
        m_initValue = value;
    }

    public void setValue(Object value) {
        addValue(m_name, value);
        m_bufferValue = value;
    }

    public void reset() {
        if(m_initValue==null){
            removeValue(m_name);
        } else{
            addValue(m_name, m_initValue);
        }
        m_bufferValue = m_initValue;
    }
}
