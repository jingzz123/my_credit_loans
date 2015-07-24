package com.intellicredit.platform.component.situation2;

import com.intellicredit.platform.util.ComparableCalendar;
import com.intellicredit.platform.service.rule.forward2.InferenceEngine;

/**
 * The source that get value from external environment.
 *
 * @author wallace
 */

public class ExternalSource implements Source {

    /**
     * The situation which ExternalSource get value from.
     */
    //private Situation m_situation;
    private InferenceEngine m_engine;

    /**
     * The attribute name of Situation.
     */
    String m_attrName;

    /**
     * The default value which should be returned when no Situation associated with
     * ExternalSource or attribute name not found in Situaion.
     */
    Object m_defaultValue;

    /**
     * Bufferd situation reference.
     */
    private Situation m_bufferSituation;

    /**
     * Buffered value
     */
    private Object m_bufferValue;

    /**
     * Construct a new ExternalSource by a given attribute name.
     *
     * @param attrName   the attribute name of Situation
     */
    public ExternalSource(String attrName) {
        m_attrName = attrName;
        m_defaultValue = null;
        m_bufferSituation = null;
        //m_bufferValue = m_bufferValue;//Variable.EMPTY;
    }

    /**
     * Construct a new ExternalSource by a given attribute name and defalut value.
     *
     * @param attrName   the attribute name of Situation
     * @param defaultValue  the default value which should be returned when no Situation
     *                    associated with ExternalSource or attribute name not found
     *                    in Situaion.
     */
    public ExternalSource(String attrName, Object defaultValue) {
        m_attrName = attrName;
        m_defaultValue = defaultValue;
        m_bufferSituation = null;
        //m_bufferValue = m_bufferValue;//Variable.EMPTY;
    }

    public void setEngine(InferenceEngine engine) {
        m_engine = engine;
    }

    public void setDefaultValue(Object initValue) {
        m_defaultValue = initValue;
    }

    public Object getValue() {
        Situation situation = m_engine.getSituation();
        if(situation==null)
            return m_defaultValue;

        if(situation==m_bufferSituation)
            return m_bufferValue;

        m_bufferSituation = situation;

        Object value = situation.getAttribute(m_attrName);

        /*
         * because we cannot return ComparableCalendar to represent a data value,
         * we must convert it to java.util.Date.
         */
        if(value instanceof ComparableCalendar)
            value = ((ComparableCalendar)value).getTime();

        m_bufferValue = (value==null) ? m_defaultValue : value;
        return m_bufferValue;
    }

     public void reset() {
         m_bufferSituation = null;
         m_bufferValue = Variable.EMPTY;
     }
}
