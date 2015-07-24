package com.intellicredit.platform.component.transaction;

import com.intellicredit.platform.util.*;

import java.io.Serializable;
import java.math.*;
import java.util.*;
import java.text.*;

/**
 * Comparable来实现排序
 * 
 * @author Hero.Wu
 * 
 */
public class Transaction implements Cloneable, Serializable,
		Comparable<Transaction> {

	private static final long serialVersionUID = -7509014706171457045L;

	public static final int SIGNAL = 0;
	public static final int SIMPLE = 1;
	public static final int NESTED = 2;
	protected int m_type;
	protected Schema m_schema;
	private int m_columnNum;// 数据项数量
	private String[] m_columnValues;
	private boolean m_hasValue = false;

	public static Transaction SUCCESS = new Transaction((Object) null);
	public static Transaction FAIL = new Transaction((Object) null);
	private Object m_obj = null;

	// Add By wu 2004.8.25
	protected Map<String, Object> m_storeMap = null;// 存放一些特殊信息,例如整个记录等等

    /**
     *存放错误信息的Map，key是字段名，value是错误信息
     */
    protected Map<String,String> errorsMap = null;

    public Transaction(Object obj) {
		if (obj instanceof Schema)
			initImpl((Schema) obj);
		else {
			m_type = SIGNAL;
			m_obj = obj;
		}
	}

	public Object getExtraData() {
		return m_obj;
	}

	/**
	 * @param def
	 *            schema
	 */
	public Transaction(Schema def) {
		initImpl(def);
	}

	private void initImpl(Schema def) {
		m_schema = def;
		m_columnNum = m_schema.getColumnNum();
		m_columnValues = new String[m_schema.getColumnNum()];
		// default value is null
		Arrays.fill(m_columnValues, null);
		m_type = SIMPLE;
		m_storeMap = new HashMap<String, Object>();
        errorsMap = new LinkedHashMap<String,String>();
	}

	public int getType() {
		return m_type;
	}

	/**
	 * @param rec
	 *            schema to be copied
	 */

	public void copy(Transaction rec) {
		int type = m_schema.getType();
		if (type == Schema.FIXED_LENGTH)
			setData(rec.getData());
		else
			setData(rec.getData(m_schema.getDelimeter()));
	}

	public String getData(String del, char fill) {
		if (!m_hasValue)
			return null;

		StringBuffer buf = new StringBuffer();
		int[] length = m_schema.getColumnLengths();
		if (m_schema.getType() == Schema.VARIOUS_LENGTH) {
			for (int i = 0; i < m_columnValues.length; i++) {
				if (m_columnValues[i] != null)
					buf.append(m_columnValues[i]);
				else
					buf.append(fill);
				if (i < m_columnValues.length - 1)
					buf.append(del);
			}
		} else {
			for (int i = 0; i < m_columnValues.length; i++) {
				if (m_columnValues[i] != null) {
					buf.append(m_columnValues[i]);
				} else {
					buf.append(Fmt.fmt(fill, length[i]));
				}
				if (i < m_columnValues.length - 1)
					buf.append(del);
			}
		}
		return buf.toString();
	}

	/**
	 * output data using delimeter to separate column valuse instead of the
	 * transaction's delimiter or even the transaction hsa no delimiter
	 * 
	 * @return the data in the transaction using delimiter to separate column
	 *         valuse
	 * @param del
	 *            delimiter to separate column values
	 */
	public String getData(String del) {
		if (!m_hasValue)
			return null;

		StringBuffer buf = new StringBuffer();
		int[] length = m_schema.getColumnLengths();
		if (m_schema.getType() == Schema.VARIOUS_LENGTH) {
			for (int i = 0; i < m_columnValues.length; i++) {
				if (m_columnValues[i] != null)
					buf.append(m_columnValues[i]);
				else
					buf.append("");
				if (i < m_columnValues.length - 1)
					buf.append(del);
			}
		} else {
			for (int i = 0; i < m_columnValues.length; i++) {
				if (m_columnValues[i] != null) {
					buf.append(m_columnValues[i]);
				} else {
					buf.append(Fmt.fmt(' ', length[i]));
				}
				if (i < m_columnValues.length - 1)
					buf.append(del);
			}
		}
		return buf.toString();
	}

	/**
	 * @return the data in the transaction
	 */
	public String getData() {
		if (!m_hasValue)
			return null;
		StringBuffer buf = new StringBuffer();
		int[] length = m_schema.getColumnLengths();
		if (m_schema.getType() == Schema.VARIOUS_LENGTH) {
			String del = m_schema.getDelimeter();
			for (int i = 0; i < m_columnValues.length; i++) {
				if (m_columnValues[i] != null)
					buf.append(m_columnValues[i]);
				else
					buf.append("");
				if (i < m_columnValues.length - 1)
					buf.append(del);
			}
		} else {
			for (int i = 0; i < m_columnValues.length; i++) {
				if (m_columnValues[i] != null) {
					buf.append(m_columnValues[i]);
				} else {
					buf.append(Fmt.fmt(' ', length[i]));
				}
			}
		}
		return buf.toString();

	}

	/**
	 * if column value is "", then set to null
	 * 
	 * @param data
	 *            data of the Transaction
	 * @return error code, 0 means no error
	 * 
	 */
	public boolean setData(String data) {
		if (null == data)
			return false;
		if (m_schema.getType() == Schema.VARIOUS_LENGTH) {
			Tokenizer tokens = new Tokenizer(data, m_schema.getDelimeter(),
					false);
			int size = tokens.countTokens();
			for (int i = 0; i < size; i++) {
				String t = tokens.nextToken();
				if (t.length() != 0)
					m_columnValues[i] = t;
				else
					m_columnValues[i] = null;
			}
			if (size < m_columnNum)
				for (int i = size; i < m_columnNum; i++)
					m_columnValues[i] = null;
		} else {
			int pos = 0;
			for (int i = 0; i < m_columnNum; i++) {
				m_columnValues[i] = data.substring(pos,
						m_schema.getColumnPositions()[i]);
				pos = m_schema.getColumnPositions()[i];
			}
		}
		m_hasValue = true;
		return true;
	}

	/**
	 * @param data
	 *            data of the Transaction
	 * @return error code, 0 means no error
	 * 
	 */
	public boolean setData(String[] data) {
		if (null == data)
			return false;
		if (m_schema.getType() == Schema.VARIOUS_LENGTH) {
			for (int i = 0; i < m_columnNum; i++) {
				// if(data == null || data[i].length() == 0)//modified by wu
				if (data[i] == null) {
					m_columnValues[i] = null;
				} else {
					m_columnValues[i] = data[i];
				}
			}
		} else {
			int[] lengths = m_schema.getColumnLengths();
			int[] types = m_schema.getColumnTypes();
			for (int i = 0; i < m_columnNum; i++) {
				if (data[i] == null)
					m_columnValues[i] = data[i];
				else {
					if (types[i] != Schema.DATE && types[i] != Schema.TEXT)
						m_columnValues[i] = Fmt.fmt(data[i], lengths[i], Fmt.ZF
								| Fmt.WN);
					else
						m_columnValues[i] = Fmt.fmt(data[i], lengths[i]);
				}
			}
		}
		m_hasValue = true;
		return true;
	}

	/** reset the Transaction */
	public void reset() {
		Arrays.fill(m_columnValues, null);
		m_storeMap.clear();
		m_hasValue = false;
	}

	public boolean isNull(String columnName) {
		return isNull(m_schema.findColumnIndex(columnName));
	}

	public boolean isNull(int columnIndex) {
		if (m_columnValues[columnIndex] == null)
			return true;
		else
			return false;
	}

	/**
	 * Gets the value of the designated column as a java.math.BigDecimal with
	 * full precision.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @return value of the column
	 */
	public BigDecimal getBigDecimal(int columnIndex) {
		BigDecimal bigD = new BigDecimal(m_columnValues[columnIndex]);
		return bigD;
	}

	/**
	 * Gets the value of the designated column as a java.math.BigDecimal with
	 * full precision.
	 * 
	 * @param columnName
	 *            name of column
	 * @return value of the column
	 */
	public BigDecimal getBigDecimal(String columnName) {
		return getBigDecimal(m_schema.findColumnIndex(columnName));
	};

	/**
	 * Gets the value of the designated column as a boolean in the Java
	 * programming language.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @return value of the column
	 */
	public boolean getBoolean(int columnIndex) {
		return Boolean.getBoolean(m_columnValues[columnIndex]);
	}

	/**
	 * Gets the value of the designated column as a boolean in the Java
	 * programming language.
	 * 
	 * @param columnName
	 *            name of column
	 * @return value of the column
	 */
	public boolean getBoolean(String columnName) {
		return getBoolean(m_schema.findColumnIndex(columnName));
	}

	/**
	 * Gets the value of the designated column as a byte in the Java programming
	 * language.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @return value of the column
	 */
	public byte getByte(int columnIndex) {
		return Byte.parseByte(m_columnValues[columnIndex]);
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a byte in the Java programming language.
	 * 
	 * @param columnName
	 *            name of column
	 * @return value of the column
	 */
	public byte getByte(String columnName) {
		return getByte(m_schema.findColumnIndex(columnName));
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a byte array in the Java programming language.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @return value of the column
	 */
	public byte[] getBytes(int columnIndex) {
		return m_columnValues[columnIndex].getBytes();
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a byte array in the Java programming language.
	 * 
	 * @param columnName
	 *            name of column
	 * @return value of the column
	 */
	public byte[] getBytes(String columnName) {
		return getBytes(m_schema.findColumnIndex(columnName));
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a java.sql.Date object in the Java programming language.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @return value of the column
	 */
	public Date getDate(int columnIndex) {
		String s = m_columnValues[columnIndex];

		GregorianCalendar calendar = null;
		if (s.length() == 8)
			calendar = new GregorianCalendar(
					Integer.parseInt(s.substring(0, 4)), Integer.parseInt(s
							.substring(4, 6)), Integer.parseInt(s.substring(6)));
		else
			calendar = new GregorianCalendar(
					Integer.parseInt(s.substring(0, 4)), Integer.parseInt(s
							.substring(4, 6)), Integer.parseInt(s.substring(6,
							8)), Integer.parseInt(s.substring(8, 10)),
					Integer.parseInt(s.substring(10, 12)), Integer.parseInt(s
							.substring(12)));

		return calendar.getTime();
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a java.sql.Date object in the Java programming language.
	 * 
	 * @param columnName
	 *            name of column
	 * @return value of the column
	 */
	public Date getDate(String columnName) {
		int i = m_schema.findColumnIndex(columnName);
		return getDate(i);
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a double in the Java programming language.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @return value of the column
	 */
	public double getDouble(int columnIndex) {
		// System.out.println("value="+m_columnValues[columnIndex]);
		return Double.parseDouble(m_columnValues[columnIndex].trim());
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a double in the Java programming language.
	 * 
	 * @param columnName
	 *            name of column
	 * @return value of the column
	 */
	public double getDouble(String columnName) {
		int i = m_schema.findColumnIndex(columnName);
		return getDouble(i);
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a float in the Java programming language.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @return value of the column
	 */
	public float getFloat(int columnIndex) {
		return Float.parseFloat(m_columnValues[columnIndex].trim());
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a float in the Java programming language.
	 * 
	 * @param columnName
	 *            name of column
	 * @return value of the column
	 */
	public float getFloat(String columnName) {
		int i = m_schema.findColumnIndex(columnName);
		return Float.parseFloat(m_columnValues[i]);
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as an int in the Java programming language.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @return value of the column
	 */
	public int getInt(int columnIndex) {
		return Integer.parseInt(m_columnValues[columnIndex].trim());
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as an int in the Java programming language.
	 * 
	 * @param columnName
	 *            name of column
	 * @return value of the column
	 */
	public int getInt(String columnName) {
		int i = m_schema.findColumnIndex(columnName);
		return getInt(i);
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a long in the Java programming language.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @return value of the column
	 */
	public long getLong(int columnIndex) {
		return Long.parseLong(m_columnValues[columnIndex].trim());
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a long in the Java programming language.
	 * 
	 * @param columnName
	 *            name of column
	 * @return value of the column
	 */
	public long getLong(String columnName) {
		int i = m_schema.findColumnIndex(columnName);
		return getLong(i);
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a short in the Java programming language.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @return value of the column
	 */
	public short getShort(int columnIndex) {
		return Short.parseShort(m_columnValues[columnIndex].trim());
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a short in the Java programming language.
	 * 
	 * @param columnName
	 *            name of column
	 * @return value of the column
	 */
	public short getShort(String columnName) {
		int i = m_schema.findColumnIndex(columnName);
		return getShort(i);
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a String in the Java programming language.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @return value of the column
	 */
	public String getString(int columnIndex) {
		if (m_columnValues[columnIndex] == null)
			return null;
		// return m_columnValues[columnIndex].trim();
		return m_columnValues[columnIndex];
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a String in the Java programming language.
	 * 
	 * @param columnName
	 *            name of column
	 * @return value of the column
	 */
	public String getString(String columnName) {
		int i = m_schema.findColumnIndex(columnName);
		return getString(i);
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a String in the Java programming language.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @return value of the column
	 */
	public String getString2(int columnIndex) {
		if (m_columnValues[columnIndex] == null)
			return null;
		return m_columnValues[columnIndex].trim();
	}

	/**
	 * Gets the value of the designated column in the current row of this Record
	 * object as a String in the Java programming language.
	 * 
	 * @param columnName
	 *            name of column
	 * @return value of the column
	 */
	public String getString2(String columnName) {
		int i = m_schema.findColumnIndex(columnName);
		return getString2(i);
	}

	/**
	 * Updates the designated column with a java.math.BigDecimal value.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @param x
	 *            value of the column
	 */
	public void updateBigDecimal(int columnIndex, BigDecimal x) {
		m_hasValue = true;
		// m_columnValues[columnIndex] = x.;
	}

	/**
	 * Updates the designated column with a java.sql.BigDecimal value.
	 * 
	 * @param columnName
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateBigDecimal(String columnName, BigDecimal x) {
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a boolean value.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @param x
	 *            value of the column
	 */
	public void updateBoolean(int columnIndex, boolean x) {
		// m_columnValues[columnIndex] = Fmt.fmt
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a boolean value.
	 * 
	 * @param columnName
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateBoolean(String columnName, boolean x) {
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a byte value.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @param x
	 *            value of the column
	 */
	public void updateByte(int columnIndex, byte x) {
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a byte value.
	 * 
	 * @param columnName
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateByte(String columnName, byte x) {
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a byte array value.
	 * 
	 * @param columnIndex
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateBytes(int columnIndex, byte[] x) {
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a boolean value.
	 * 
	 * @param columnName
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateBytes(String columnName, byte[] x) {
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a java.sql.Date value.
	 * 
	 * @param columnIndex
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateDate(int columnIndex, Date date, String format) {
		int length = m_schema.m_columnLengths[columnIndex];
		Format formatter = new SimpleDateFormat(format);
		m_columnValues[columnIndex] = Fmt.fmt(formatter.format(date), length);
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a java.sql.Date value.
	 * 
	 * @param columnName
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateDate(String columnName, Date date, String format) {
		int index = m_schema.findColumnIndex(columnName);
		updateDate(index, date, format);
	}

	/**
	 * Updates the designated column with a double value.
	 * 
	 * @param columnIndex
	 * @param x
	 *            name of column
	 */
	public void updateDouble(int columnIndex, double x) {
		int length = 0;
		// if(columnIndex > 0)
		// length = m_schema.m_columnPositions[columnIndex] -
		// m_schema.m_columnPositions[columnIndex-1];
		// else
		// length = m_schema.m_columnPositions[0];
		length = m_schema.m_columnLengths[columnIndex];
		if (x < 0) {
			if (length > 2)
				m_columnValues[columnIndex] = Fmt.fmt(x, length, length - 2);
			else
				// innormal
				m_columnValues[columnIndex] = Fmt.fmt(x, length);
		} else {
			if (length > 1)
				m_columnValues[columnIndex] = Fmt.fmt(x, length, length - 1);
			else
				// innormal
				m_columnValues[columnIndex] = Fmt.fmt(x, length);
		}
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a double value.
	 * 
	 * @param columnName
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateDouble(String columnName, double x) {
		int index = m_schema.findColumnIndex(columnName);
		updateDouble(index, x);
	}

	/**
	 * Updates the designated column with a float value.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @param x
	 *            value of the column
	 */
	public void updateFloat(int columnIndex, float x) {
		int length = 0;
		length = m_schema.m_columnLengths[columnIndex];

		m_columnValues[columnIndex] = Fmt.fmt(x, length);
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a float value.
	 * 
	 * @param columnName
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateFloat(String columnName, float x) {
		int index = m_schema.findColumnIndex(columnName);
		updateFloat(index, x);
	}

	/**
	 * Updates the designated column with an int value.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @param x
	 *            value of the column
	 */
	public void updateInt(int columnIndex, int x) {
		int length = 0;
		length = m_schema.m_columnLengths[columnIndex];

		m_columnValues[columnIndex] = Fmt.fmt(x, length);
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with an int value.
	 * 
	 * @param columnName
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateInt(String columnName, int x) {
		int index = m_schema.findColumnIndex(columnName);
		updateInt(index, x);
	}

	/**
	 * Updates the designated column with a long value.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @param x
	 *            value of the column
	 */
	public void updateLong(int columnIndex, long x) {
		int length = 0;
		length = m_schema.m_columnLengths[columnIndex];

		m_columnValues[columnIndex] = Fmt.fmt(x, length);
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a long value.
	 * 
	 * @param columnName
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateLong(String columnName, long x) {
		int index = m_schema.findColumnIndex(columnName);
		updateLong(index, x);
	}

	/**
	 * Gives a nullable column a null value.
	 * 
	 * @param columnIndex
	 *            index of column
	 */
	public void updateNull(int columnIndex) {
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a null value.
	 * 
	 * @param columnName
	 *            name of column
	 */
	public void updateNull(String columnName) {
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a short value.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @param x
	 *            value of the column
	 */
	public void updateShort(int columnIndex, short x) {
		int length = 0;
		length = m_schema.m_columnLengths[columnIndex];

		m_columnValues[columnIndex] = Fmt.fmt(x, length);
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a short value.
	 * 
	 * @param columnName
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateShort(String columnName, short x) {
		int index = m_schema.findColumnIndex(columnName);
		updateShort(index, x);
	}

	/**
	 * Updates the designated column with a String value.
	 * 
	 * @param columnIndex
	 *            index of column
	 * @param x
	 *            value of the column
	 */
	public void updateString(int columnIndex, String x) {
		int type = m_schema.getType();
		if (type == Schema.FIXED_LENGTH) {
			int length = 0;
			length = m_schema.m_columnLengths[columnIndex];
			m_columnValues[columnIndex] = Fmt.fmt(x, length, Fmt.LJ);
		} else {
			m_columnValues[columnIndex] = x;
		}
		m_hasValue = true;
	}

	/**
	 * Updates the designated column with a String value.
	 * 
	 * @param columnName
	 *            name of column
	 * @param x
	 *            value of the column
	 */
	public void updateString(String columnName, String x) {
		int index = m_schema.findColumnIndex(columnName);
		updateString(index, x);
	}

	/**
	 * @param type
	 *            index of column
	 * @return String represent of the Transaction
	 */
	public String toString(int type) {
		StringBuffer buf = new StringBuffer();
		String[] names = m_schema.getColumnNames();
		int columnNum = m_schema.getColumnNum();
		if (type == 0) { // bealty
			buf.append("record:\n");
			for (int i = 0; i < names.length; i++) {
				buf.append(names[i] + "=");
				buf.append(getString(i) + ", ");
			}
			buf.append("\n");
		} else if (type == 1) { // fixed length
			for (int i = 0; i < columnNum; i++) {
				buf.append(getString(i));
			}
		} else if (type == 2) { // delimeter
			for (int i = 0; i < columnNum; i++) {
				buf.append(getString(i) + ",");
			}
		}
		return buf.toString();
	}

	/**
	 * put your documentation comment here
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] len = new int[] { 1, 2, 3 };
		String[] names = new String[] { "a", "b", "c" };
		int[] types = new int[] { Schema.INT, Schema.INT, Schema.INT };
		Schema def = new Schema(names, types, len);

		long t = System.currentTimeMillis();
		Transaction record1 = null;
		for (int i = 0; i < 1000000; i++) {
			record1 = new Transaction(def);
			// record1.setData("1223.3");
			record1.setData("122333");
			record1.getFloat(0);
			record1.getLong(1);
			record1.getInt(2);
		}
		// <6000
		System.out.println(System.currentTimeMillis() - t);

		System.out.print(record1.getInt(0) + " ");
		System.out.print(record1.getInt(1) + " ");
		System.out.print(record1.getInt(2) + " ");

		System.out.print(record1.getInt("a") + " ");
		System.out.print(record1.getInt("b") + " ");
		System.out.print(record1.getInt("c") + " ");

	}

	/**
	 * @param ref
	 *            schema of the Transaction
	 */
	public void setSchema(Schema ref) {
		m_schema = ref;
	}

	/**
	 * @return schema of the transaction
	 */
	public Schema getSchema() {
		return m_schema;
	}

	public String getColData(int columnIndex) {
		return m_columnValues[columnIndex];
	}

	public String getColData(String columnName) {
		int i = m_schema.findColumnIndex(columnName);
		return getColData(i);
	}

	/**
	 * get the values according to special requirement
	 * 
	 * @parameter: itentifier=>
	 */
	public Object getMyData(int identifier) {
		return null;
	}

	/*
	 * get the combining String according to the column indexes; it will not
	 * judge the correction of the parameters
	 * 
	 * @parameter: delimiter is the
	 */
	public String getString(int[] columnIndexs, String delimiter) {
		StringBuffer strBuf = new StringBuffer("");
		int len = columnIndexs.length;
		for (int i = 0; i < len; i++) {
			if (m_columnValues[columnIndexs[i]] == null)
				strBuf.append("");
			else
				strBuf.append(m_columnValues[columnIndexs[i]]);
			if (i < len - 1) {
				strBuf.append(delimiter);
			}
		}
		return strBuf.toString();
	}

	// add by wu for speed
	public Object clone() {
		Transaction obj = null;
		try {
			obj = (Transaction) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		obj.m_columnValues = new String[m_schema.getColumnNum()];
		obj.m_storeMap.clear();
		Arrays.fill(obj.m_columnValues, null);
		return obj;
	}

	/**
	 * 为了排序所用
	 */
	public int compareTo(Transaction tran) {
		int sortCount = m_schema.getSortCount();
		if (sortCount == 0) {
			return -1;
		}
		String[] sortNames = m_schema.getSortNames();
		int[] sortTypes = m_schema.getSortTypes();
		String name;
		int sortNameType;
		int result;
		for (int i = 0; i < sortCount; i++) {
			name = sortNames[i];
			sortNameType = m_schema.getSingleColumnType(name);
			if (sortNameType == Schema.NUMBER || sortNameType == Schema.INT
					|| sortNameType == Schema.LONG
					|| sortNameType == Schema.FLOAT
					|| sortNameType == Schema.DOUBLE) {
				Double d1 = new Double(getDouble(i));
				Double d2 = new Double(tran.getDouble(i));
				result = sortTypes[i] == TranConstants.ASCEND ? d1
						.compareTo(d2) : d2.compareTo(d1);
			} else if (sortNameType == Schema.STRING
					|| sortNameType == Schema.TEXT) {
				String s1 = TranConstants.select(getString(i));
				String s2 = TranConstants.select(tran.getString(i));
				result = sortTypes[i] == TranConstants.ASCEND ? s1
						.compareTo(s2) : s2.compareTo(s1);
			} else {
				// TODO：主要日期没有处理
				result = 0;
			}
			if (result != 0)
				return result;
		}
		return 0;
	}

	// add by wu 2004.08.25
	public void put(String key, Object value) {
		m_storeMap.put(key, value);
	}

	public Object get(String key) {
		return m_storeMap.get(key);
	}

    public boolean isErrorColumn(String name){
    	if(this.errorsMap==null){
    		return false;
    	}
    	return errorsMap.containsKey(name);
    }
    
    /**
     * 判断是否是错误的数据
     * @param index：name的位置
     * @return
     */
    public boolean isErroColumn(int index){
    	try{
    		String name = m_schema.getColumnNames()[index];
    		return isErrorColumn(name);
    	}catch(Exception e){
    		return false;
    	}
    }
    
	public Map<String, String> getErrorsMap() {
		return errorsMap;
	}

	public void setErrorsMap(Map<String, String> errorsMap) {
		this.errorsMap = errorsMap;
	}
    
    public void putError(String name,String error){
    	this.errorsMap.put(name, error);
    }
    
    /**
     * 获取字符串表示的错误信息，格式如下：
     * 【字段描述=字段值】：错误信息
     * @return
     */
    public String getErrorInfo(){
    	if(errorsMap==null||errorsMap.size()==0){
    		return "";
    	}
    	StringBuilder buf = new StringBuilder("");
    	Iterator<String> names = errorsMap.keySet().iterator();
    	String name,desc,value;
    	int index;
    	int count = 0;
    	while(names.hasNext()){
    		name = names.next();
    		index = m_schema.findColumnIndex(name);
    		desc = m_schema.getColumnDesces()[index];
    		value = this.getString(index);
    		if(value==null){
    			value = "";
    		}
    		count++;
    		if(count>1){
    			buf.append("；");
    		}
    		buf.append("【").append(desc).append("=").append(value).append("】：");
    		buf.append(errorsMap.get(name));
    	}
    	return buf.toString();
    }
    
    public String getPkCombine(){
		String[] pkNames = m_schema.getSortNames();
		if(pkNames==null||pkNames.length==0){
			return  null;
		}
		int len = pkNames.length;
		StringBuilder builder = new StringBuilder("");
		for(int i=0; i<len; i++){
			builder.append(getString(pkNames[i]));
			if(i<len-1)
				builder.append(ValidatorConstants.PK_DELIMITER);
		}
		//System.out.println("pk combining="+builder.toString());
		return builder.toString();
    }
}
