package com.intellicredit.platform.component.situation2;

import com.intellicredit.platform.component.transaction.*;
import com.intellicredit.platform.service.rule.forward2.InferenceEngine;

/**
 * The source that get value from transaction.
 * Before we use this source to extract value, we must initialize source
 * by one of the following ways:
 * 1. call setTransaction(...) to pass an existed transaction to it.
 * 2. first call setSchema(...) to specify a schema of the transaction,
 *    then call setData(...) to pass a string to construct a transaction
 *    according to the schema former specified.
 *
 * @author wallace
 */

public class TransactionSource implements Source {

    /**
     * The schema of transaction.
     */
    private Schema m_schema;


    /**
     * The transaction will be got from m_engine
     */
    private InferenceEngine m_engine;

    /**
     * The data type of TransactionSource. e.g. NUMBER, TEXT, DATE.
     */
    private int m_type;

    /**
     * The column name in the transaction.
     */
    private String m_field;

    /**
     * The id of segment in the transaction. It is the first column name in the segment.
     */
    private String m_subId;

    /**
     * Bufferd transaction reference.
     */
    private Transaction m_bufferTransaction;

    /**
     * Buffered value
     */
    private Object m_bufferValue;

    public TransactionSource(){

    }

    /**
     * Construct a new TransactionSource by given type, field and subid.
     *
     * @param type   the data type of TransactionSource. e.g. NUMBER, TEXT, DATE
     * @param field  the column name in the transaction
     * @param subId  the id of segment in the transaction.
     *                It is the first column name in the segment.
     */
    public TransactionSource(int type, String field, String subId) {
        m_type = type;
        m_field = field;
        m_subId = subId;
        m_bufferTransaction = null;
        m_bufferValue = Variable.EMPTY;
    }

    public Schema getSchema() {
        return m_schema;
    }

    public void setSchema(Schema schema) {
        m_schema = schema;
    }

    public Transaction getTransaction() {
        return m_engine.getTransaction();
    }

    public void setEngine(InferenceEngine engine) {
        m_engine = engine;
    }

    public int getType() {
        return m_type;
    }

    public String getField() {
        return m_field;
    }

    public String getSubId() {
        return m_subId;
    }

    public Object getValue() {
        Transaction tran = m_engine.getTransaction();
        if(tran==null)
            return null;

        if(tran==m_bufferTransaction)
            return m_bufferValue;

        m_bufferTransaction = tran;

        if(m_subId!=null && !m_subId.equals("")) {
            NestTransaction transaction = (NestTransaction)tran;
            switch(m_type) {
                case Variable.NUMBER:
                    return (m_bufferValue=transaction.getDouble(m_subId,m_field));
                case Variable.TEXT:
                    return (m_bufferValue=transaction.getString(m_subId,m_field));
                case Variable.DATE:
                    return (m_bufferValue=transaction.getDate(m_subId,m_field));
                case Variable.INT:
                    return (m_bufferValue=transaction.getInt(m_subId,m_field));
                case Variable.FLOAT:
                    return (m_bufferValue=transaction.getFloat(m_subId,m_field));
                case Variable.LONG:
                    return (m_bufferValue=transaction.getLong(m_subId,m_field));
                case Variable.DOUBLE:
                    return (m_bufferValue=transaction.getDouble(m_subId,m_field));
                default:
                    return (m_bufferValue=transaction.getString(m_subId,m_field));
            }
        } else {
            switch(m_type) {
                case Variable.NUMBER:
                    return (m_bufferValue=new Double(tran.getDouble(m_field)));
                case Variable. TEXT:
                    return (m_bufferValue=tran.getString(m_field));
                case Variable.DATE:
                    return (m_bufferValue=tran.getDate(m_field));
                case Variable.INT:
                    return (m_bufferValue=new Integer(tran.getInt(m_field)));
                case Variable.FLOAT:
                    return (m_bufferValue=new Float(tran.getFloat(m_field)));
                case Variable.LONG:
                    return (m_bufferValue=new Long(tran.getLong(m_field)));
                case Variable.DOUBLE:
                    return (m_bufferValue=new Double(tran.getDouble(m_field)));
                default:
                    return (m_bufferValue=tran.getString(m_field));
            }
        }
    }

    /*
     * Clear the transaction in the source.
     * @see com.intellicredit.platform.component.situation2.Source#reset()
     */
    public void reset() {
        m_bufferTransaction = null;
        m_bufferValue = Variable.EMPTY;
    }
}
