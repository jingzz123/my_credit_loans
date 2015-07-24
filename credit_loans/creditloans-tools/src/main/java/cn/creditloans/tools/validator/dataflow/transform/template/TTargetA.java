package cn.creditloans.tools.validator.dataflow.transform.template;

import cn.creditloans.tools.validator.dataflow.transform.DFConstant;
import cn.creditloans.tools.validator.dataflow.transform.Transform;
import cn.creditloans.tools.validator.transaction.Schema;
import cn.creditloans.tools.validator.transaction.Transaction;

/** TTarget defines a template of Target Transform. */
public abstract class TTargetA extends Transform {
	Transaction[] m_inRecs = null;

	/** Creates a new instance of Target */
	public TTargetA() {
		setType(Transform.TARGET);
	}

	public void setSchema(Schema def) {
		setInSchemas(new Schema[] { def });
		setOutSchemas(new Schema[] { def });
	}

	public Transaction[] nextA(int outChannel) {
		// if one record fails, try the next one until no record from previous
		// transform
		if (m_engine.getState() == DFConstant.STOP)
			return null;
		else {
			while ((m_inRecs = m_transforms[0].nextA(m_channels[0])) != null) {
				if (process(m_inRecs) == 0) {
					return m_inRecs;
				} else {
					// log(Fmt.fmt(status, DFConstant.ERROR_CODE_LEN) +
					// DFConstant.ERROR_SEPERATOR, m_inRec);
				}
			}
		}
		return null;
	}

	/**
	 * start the data flow process and process all the record in the source of
	 * the Data Flow
	 * 
	 * @return error code, 0 means no error
	 */
	public int pull() {
		int count = 0;
		// System.out.println("SS");
		while (nextA(m_channels[0]) != null) {
			count++;
		}
		return count;
	}

	// public abstract int pull();
	/**
	 * Start the dataflow plan to process the count number of transactions in
	 * the source of the data`flow plan. The subcalss must implement this
	 * method.
	 * 
	 * @param count
	 *            the number of transactions to be processed
	 * @return the number of Transactions processed
	 */
	public int pull(int n) {
		int count = 0;
		// System.out.println("SS");
		while (nextA(m_channels[0]) != null) {
			count++;
			if (count >= n)
				break;
		}
		return count;
	}

	// public abstract int pull(int count);
	/**
	 * process one Transsaction object. The subcalss must implement this method.
	 * 
	 * @param transaction
	 *            data record to be processed.
	 * @return the number of Transactions processed.
	 */
	protected abstract int process(Transaction[] transaction);

	/**
	 * This is the interface for transforms to pass data.
	 * 
	 * @return the next record from the transform. return null when get to the
	 *         end of channel. if some incoming records are invalide and the
	 *         transform can not create a associated output record, the bad
	 *         incoming record and cause of the error is logged.
	 * @param outChannel
	 *            the index of the output port. One transform can have more than
	 *            one output ports
	 * 
	 */
	public Transaction next(int outChannel) {
		return null;
	}

	/**
	 * validate the Transform to make sure its internal status is ready and
	 * properly connectted with other Transform in a DataFlow Plan correctly.
	 * 
	 */
	public boolean validate() {
		return true;
	}

}
