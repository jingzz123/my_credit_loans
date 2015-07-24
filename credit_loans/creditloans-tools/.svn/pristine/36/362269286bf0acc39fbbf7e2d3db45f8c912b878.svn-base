package cn.creditloans.tools.validator.dataflow.transform.template;

import cn.creditloans.tools.validator.dataflow.transform.DFConstant;
import cn.creditloans.tools.validator.dataflow.transform.Transform;
import cn.creditloans.tools.validator.transaction.Transaction;

/** TTarget defines a template of Target Transform. */
public abstract class TTarget extends TOne2OneSameFormat {

    /** Creates a new instance of Target */
    public TTarget() {
        setType(Transform.TARGET);
    }

    //specified this function to support STOP function.
    /** this method is not used in Target.
     * @param channel number of output ports
     */
    public Transaction next(int outChannel) {
        if(m_engine.getState()==DFConstant.STOP)
            return null;
        else
            return super.next(outChannel);
    }

    /** start the data flow process and process all the record in the source of the DataFlow
     * @return error code, 0 means no error
     */
    public abstract int pull();

    /** Start the dataflow plan to process the count number of transactions in the
     * source of the data`flow plan. The subcalss must implement this method.
     * @param count the number of transactions to be processed
     * @return the number of Transactions processed
     */
    public abstract int pull(int count);

    /** process one Transsaction object. The subcalss must implement this method.
     * @param transaction data record to be processed.
     * @return the number of Transactions processed.
     */
    protected abstract int process(Transaction transaction);
}
