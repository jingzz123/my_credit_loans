package com.intellicredit.platform.service.dataflow.transform.common;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.intellicredit.platform.service.dataflow.transform.template.TTarget;
import com.intellicredit.platform.component.transaction.Transaction;

/** A file target Transform */
public class TTextFileTarget extends TTarget {
    BufferedWriter m_writer;
    String m_targetDelimiter = null;
    String m_newLine = null;
    //    boolean first = true;
    
    /** constructor */
    public TTextFileTarget() {
        super();
    }
    
    /**
     * @return error code with 0 means no error
     */
    public int init() {
        try {
            //           first = true;
            m_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((String)getParameter("output_file"))));
            
            if(getParameter("delimiter") != null) {
                String delimiter = (String)getParameter("delimiter");
                if(delimiter.trim().length() > 0)
                    m_targetDelimiter = (String)getParameter("delimiter");
            }
            if(getParameter("newline") != null) {
                String newLine = (String)getParameter("newline");
                if(newLine.trim().equals("\\n"))
                    m_newLine = "\n";
                else if(newLine.trim().equals("\\r\\n"))
                    m_newLine = "\r\n";
            }
        } catch (Exception ee) {
            return 1 + super.init();
        }
        return super.init();
    }
    
    /**
     * @param rec output transaction
     * @return error code with 0 means no error
     */
    protected  int process(Transaction rec){
        if(rec == null)
            System.out.println("REC is NULL");
        if(null == m_writer)
            System.out.println("Writer is NULL");
        //add by small 2004.03.02
        if(rec==Transaction.FAIL){
            return 0;
        }
        if(rec.getData() == null)
            System.out.println("getData is null");
        try {
            //            first = false;
            if(m_targetDelimiter != null)
                m_writer.write(rec.getData(m_targetDelimiter));
            else{
                //                System.out.println("data is"+rec.getData());
                m_writer.write(rec.getData());
            }
            
            if(m_newLine!=null)
                m_writer.write(m_newLine);
            
        } catch (IOException ee) {
            return 1;
        }
        return 0;
    }
    
    /*
     * implement pull in Transform
     */
    /** start the data flow plan to process all transactions in source
     * @return number of successfully processed transaction
     */
    public int pull() {
        int count = 0;
        while(next(m_channels[0]) != null) {
            count++;
        }
        try {
            if(m_writer!=null){
                m_writer.flush();
                m_writer.close();
            }
        } catch (IOException ee) {
        }
        m_writer = null;
        return count;
    }
    
    /** start the data flow plan to process limited number of transaction from source
     * @param n the number of Transactions to be processed
     * @return the number of Transactions successfully processed
     */
    public int pull(int n) {
        int count = 0;
        while(next(m_channels[0]) != null) {
            count++;
            if(count >= n)
                break;
        }
        try {
            if(m_writer!=null) {
                m_writer.flush();
                if(count==0) {
                    m_writer.close();
                    m_writer = null;
                }
            }
        } catch (IOException ee) {
            ee.printStackTrace();
        }
        return count;
    }
    
}
