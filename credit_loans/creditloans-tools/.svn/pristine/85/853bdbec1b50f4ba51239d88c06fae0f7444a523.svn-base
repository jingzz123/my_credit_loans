package com.intellicredit.platform.service.dataflow.transform.template;

import com.intellicredit.platform.service.dataflow.transform.DFConstant;
import com.intellicredit.platform.service.dataflow.transform.Transform;
import com.intellicredit.platform.component.transaction.Schema;
import com.intellicredit.platform.component.transaction.Transaction;
import com.intellicredit.platform.util.Fmt;

/** A Template to create Source Transform */
public abstract class TSource extends Transform {

    private Transaction m_inRec;

    /** Creates a new instance of Source */
    public TSource() {
        m_type = SOURCE;
    }

    /** help method to set input and output Schema together
     * @param def the input and output Schema.
     */
    public void setSchema(Schema def) {
        setInSchemas(new Schema[] {def});
        setOutSchemas(new Schema[] {def});
    }

    /** get the next data record from the source. This method is called repeatedly by the DataFlow Engine.
     * @return Transaction.
     */
    public Transaction next(int channel) {
        while(true) {
            try {
                m_inRec = retrieve();
            } catch(Exception e) {;
                e.printStackTrace();
                m_logger.warning(e.toString());
                return null;
            }
            if(m_inRec != null) {
                int status;
                if((status = process(m_inRec)) == 0)
                    return m_inRec;
                else
                    log(Fmt.fmt(status, DFConstant.ERROR_CODE_LEN) + DFConstant.ERROR_SEPERATOR, m_inRec);
            }
            else {
                return null;
            }
        }
    }

    /**
     *validate the Transform to make sure its internal status is ready and properly connectted with other Transform in a DataFlow Plan
     * correctly.
     * @return true because the input and output schema are same.
     */
    public boolean validate() {
        return true;
    }

    /** get next data record from the real source
     * @return next data record from real source.
     */
    protected abstract Transaction retrieve();

    /** process the transaction. this method is called by Engine automatically.
     * @param outRec output transaction object
     * @return status. 0 successful; others error
     */
    protected abstract int process(Transaction outRec);
}
