package com.intellicredit.platform.component.transaction;

import java.io.*;

public class TransactionFile {
    private BufferedReader mReader = null;
    private Schema m_schema;

    public TransactionFile(File file, Schema def) {
        m_schema = def;
        try {
            mReader = new BufferedReader(new InputStreamReader(
                                                 new FileInputStream(file)));
        } catch (Exception ee) {
        }
    }
    public void close() {
            if(mReader != null) {
                try {    
                mReader.close();
                    mReader = null;
                }catch (Exception e) {}
            }
    }
    public Transaction next() {
        try {
            
            Transaction record;
            if(m_schema instanceof NestSchema)
                record = new NestTransaction((NestSchema)m_schema);
            else
                record = new Transaction(m_schema);
            String line = mReader.readLine();
            if(line == null)
                return null;
            record.setData(line);
            return record;
            
        } catch (Exception ee) {
            return null;
        }
    }
}