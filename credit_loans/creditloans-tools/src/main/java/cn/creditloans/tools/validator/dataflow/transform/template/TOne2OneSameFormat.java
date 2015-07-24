package cn.creditloans.tools.validator.dataflow.transform.template;

import cn.creditloans.tools.validator.dataflow.transform.DFConstant;
import cn.creditloans.tools.validator.dataflow.transform.Transform;
import cn.creditloans.tools.validator.transaction.Schema;
import cn.creditloans.tools.validator.transaction.Transaction;
import cn.creditloans.tools.validator.util.Fmt;

/** TOne2OneSameFormat defines a template of the Transform that has one input port
 * and one output port. The format of input and output are same.
 */
public abstract class TOne2OneSameFormat extends Transform {
    private Transaction m_inRec;

    /** constructor */
    public TOne2OneSameFormat() {
        setType(ONE_2_ONE_SAME_FORMAT);
    }

    /** help method to set input and output together.
     * @param def input and out put Schema
     */
    public void setSchema(Schema def) {
        setInSchemas(new Schema[] {def});
        setOutSchemas(new Schema[] {def});
    }

    /**
     *get a Transaction from input Transform, process the Transaction object
     *and pass the same Transaction object to the output Transform
     * @param outChannel ignored becasue there is only output
     * @return transaction object
     */
    public Transaction next(int outChannel){
        //if one record fails, try the next one  until no record from previous transform
        while((m_inRec = m_transforms[0].next(m_channels[0])) != null) {
            int status = process(m_inRec);
            if(status == 0) {
                return m_inRec;
            }
            else {
                log(Fmt.fmt(status, DFConstant.ERROR_CODE_LEN) + DFConstant.ERROR_SEPERATOR, m_inRec);
            }
        }
        return null;
    }

    /**
     * @return always true because the input and output schema are same
     */
    public boolean validate() {
        return true;
    }

    /** process a Transaction object. This method is called in next().
     * @param rec
     * @return status. 0 successful; 1 end of channel; others error
     */
    protected abstract int process(Transaction rec);
}
