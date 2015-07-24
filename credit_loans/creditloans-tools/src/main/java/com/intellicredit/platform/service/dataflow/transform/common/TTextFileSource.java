package com.intellicredit.platform.service.dataflow.transform.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import com.intellicredit.platform.service.dataflow.transform.template.TSource;
import com.intellicredit.platform.component.transaction.Transaction;

public class TTextFileSource extends TSource {
    BufferedReader m_reader = null;
    String m_line = null;
    Transaction m_outRec;
    
    /** constructor */    
    public TTextFileSource() {
        super();
    }
    
    /** initialize the Transform
     * @return error code with 0 means no error
     */    
    public int init() {
        try {
            m_reader = new BufferedReader(new InputStreamReader(new FileInputStream((String)getParameter("input_file"))));
        } catch (Exception ee) {
            return 1 + super.init();
        }
        return super.init();
    }
    
    /** get next transaction from source file
     * @return next transaction from source file
     */    
    protected  Transaction retrieve(){
        try {
            m_line = m_reader.readLine();
        } catch (IOException ee) {
            return null;
        }
        if(m_line == null) {
            try {
                m_reader.close();
            } catch(Exception e) {}
            return null;
        }
        m_outRec = new Transaction(m_outSchemas[0]);
        m_outRec.setData(m_line);
       // System.out.println(m_outRec.getData());
        return m_outRec;
    }   
    
    /** process output transaction object
     * @param outRec output transaction
     * @return error code with 0 means no error
     */    
    protected int process(Transaction outRec){
        return 0;
    }   

    public void cleanup() {
        super.cleanup();
        if(m_reader != null) {
            try {
                m_reader.close();
            } catch(Exception ee) {}
        }        
    }
}
