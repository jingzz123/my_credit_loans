package cn.creditloans.tools.validator.dataflow.transform.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import cn.creditloans.tools.validator.dataflow.transform.template.TTargetA;
import cn.creditloans.tools.validator.transaction.Transaction;

/** A file target Transform */
public class TTextFileTargetA extends TTargetA {
	BufferedWriter m_writer;
	String m_targetDelimiter = null;
	String m_newLine = null;

	/** constructor */
	public TTextFileTargetA() {
		super();
	}

	/**
	 * @return error code with 0 means no error
	 */
	public int init() {
		try {
			m_writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(
							(String) getParameter("output_file")))));

			if (getParameter("delimiter") != null) {
				String delimiter = (String) getParameter("delimiter");
				if (delimiter.trim().length() > 0)
					m_targetDelimiter = (String) getParameter("delimiter");
			}
			if (getParameter("newline") != null) {
				String newLine = (String) getParameter("newline");
				if (newLine.trim().equals("\\n"))
					m_newLine = "\n";
				else if (newLine.trim().equals("\\r\\n"))
					m_newLine = "\r\n";
			}
		} catch (Exception ee) {
			return 1 + super.init();
		}

		return super.init();
	}

	/**
	 * @param rec
	 *            output transaction
	 * @return error code with 0 means no error
	 */
	protected int process(Transaction[] recs) {
		try {
			for (int i = 0; i < recs.length; i++) {
				if (m_targetDelimiter != null)
					m_writer.write(recs[i].getData(m_targetDelimiter));
				else {
					m_writer.write(recs[i].getData());
				}
				if (m_newLine != null)
					m_writer.write(m_newLine);
			}
		} catch (IOException ee) {
			return 1;
		}
		return 0;
	}

	public void cleanup() {
		super.cleanup();
		if (m_writer != null)
			try {
				m_writer.close();
			} catch (Exception ee) {
			}
	}
}
