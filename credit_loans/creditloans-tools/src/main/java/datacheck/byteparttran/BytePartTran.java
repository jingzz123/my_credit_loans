package datacheck.byteparttran;

import com.intellicredit.platform.component.transaction.Schema;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 字节读取的
 * 假设用户传入的是一行数据
 * @author Ash
 * 
 */
public class BytePartTran implements Cloneable, Comparable<BytePartTran> {
	public static int ASCEND;
	public static int DESCEND;
	public Double MIN_DOUBLE = new Double(4.9E-324D);
	public Integer MIN_INTEGER = new Integer(-2147483648);
	public Long MIN_LONG = new Long(-9223372036854775808L);

	public String MIN_STRING = "                                                           ";

	public static int VARIOUS_BYTE_FIXED = 1;
	public static int VARIOUS_DELIMITER = 2;

	/**
	 * 各个字段的起始位置-s
	 */
	protected int[] m_beginPoses = null;

	/**
	 * 各个字段包含的字节
	 */
	private byte[] m_byteRecord = null;

	protected char m_charDel = '|';

	/**
	 * 数据文件的编码方式
	 */
	private String m_charset = "GB18030";

	/**
	 * 每一个字节的截止位置
	 */
	protected int[] m_endPoses = null;
	private static int[] m_every_sort_type;
	protected int[] m_indexes = null;

	private int m_maxIndex = 0;
	private Map<String, String> m_nameIndexMap;
	protected String[] m_names = null;

	private int m_needCount = 0;

	private String m_record = null;

	private byte[] m_recordLen = null;
	protected Schema m_schema;
	private int m_type = VARIOUS_BYTE_FIXED;
	protected int[] m_types;
	private String[] m_values;

	static {
		ASCEND = 0;
		DESCEND = 1;
		m_every_sort_type = null;
	}

	public BytePartTran(Schema def, String[] names, int type) {
		if ((type == VARIOUS_BYTE_FIXED) || (type == VARIOUS_DELIMITER))
			this.m_type = type;
		else
			this.m_type = VARIOUS_BYTE_FIXED;
		initImpl(def, names);
	}

	public BytePartTran() {
	}
	
	public void setCharset(String charset){
		this.m_charset = charset;
	}
	
	public String getCharset(){
		return this.m_charset;
	}

	public Object clone() {
		BytePartTran obj = null;
		try {
			obj = (BytePartTran) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		obj.m_values = new String[obj.m_needCount];
		Arrays.fill(obj.m_values, null);
		obj.m_record = null;
		obj.m_byteRecord = null;
		obj.m_recordLen = null;
		return obj;
	}

	public int compareTo(BytePartTran tran) {
		for (int i = 0; i < this.m_needCount; i++) {
			int result;
			if ((this.m_types[i] == 2) || (this.m_types[i] == 2)) {
				String s1 = select(getString(i));
				String s2 = select(tran.getString(i));
				result = m_every_sort_type[i] == ASCEND ? s1.compareTo(s2) : s2
						.compareTo(s1);
			} else if (this.m_types[i] == 4) {
				Integer d1 = new Integer(getInt(i));
				Integer d2 = new Integer(tran.getInt(i));
				result = m_every_sort_type[i] == ASCEND ? d1.compareTo(d2) : d2
						.compareTo(d1);
			} else if (this.m_types[i] == 5) {
				Long d1 = new Long(getLong(i));
				Long d2 = new Long(tran.getLong(i));
				result = m_every_sort_type[i] == ASCEND ? d1.compareTo(d2) : d2
						.compareTo(d1);
			} else if (this.m_types[i] == 1) {
				Double d1 = new Double(getDouble(i));
				Double d2 = new Double(tran.getDouble(i));
				result = m_every_sort_type[i] == ASCEND ? d1.compareTo(d2) : d2
						.compareTo(d1);
			} else {
				return 0;
			}
			if (result != 0)
				return result;
		}
		return 0;
	}

	private String findNameIndex(String name) {
		return this.m_nameIndexMap.get(name);
	}

	public byte[] getBRLen() {
		return this.m_recordLen;
	}

	public byte[] getByteValue() {
		return this.m_byteRecord;
	}

	public int[] getColumnTypes() {
		return this.m_types;
	}

	public double getDouble(String name) {
		String index = findNameIndex(name);
		if (index == null)
			return -1.0D;
		return getDouble(Integer.parseInt(index));
	}

	public double getDouble(int columnIndex) {
		if ((this.m_values[columnIndex] != null)
				&& (this.m_values[columnIndex].trim().length() > 0))
			return Double.parseDouble(this.m_values[columnIndex]);
		return -1.0D;
	}

	public int getInt(String name) {
		String index = findNameIndex(name);
		if (index == null)
			return -1;
		return getInt(Integer.parseInt(index));
	}

	public int getInt(int columnIndex) {
		if ((this.m_values[columnIndex] != null)
				&& (this.m_values[columnIndex].trim().length() > 0))
			return Integer.parseInt(this.m_values[columnIndex]);
		return -1;
	}

	public long getLong(int columnIndex) {
		if ((this.m_values[columnIndex] != null)
				&& (this.m_values[columnIndex].trim().length() > 0))
			return Long.parseLong(this.m_values[columnIndex]);
		return -1L;
	}

	private String[] getMStr(String data, char del, int count) {
		String[] backStr = new String[count];
		int len = data.length();
		char[] values = data.toCharArray();
		int num = 0;
		int begin = 0;
		char[] tmpChar = null;
		while (num < count) {
			for (int i = begin; i < len; i++) {
				if (values[i] == del) {
					int end = i;
					tmpChar = new char[end - begin];
					System.arraycopy(values, begin, tmpChar, 0, i - begin);
					backStr[num] = new String(tmpChar);
					begin = end + 1;
					num++;
					break;
				}

			}

		}

		return backStr;
	}

	private String[] getMStr(byte[] data) {
		String[] backStr = new String[this.m_needCount];
		try {
			byte[] curByte = null;
			int i = 0;
			for (; i < this.m_needCount; i++) {
				int len = this.m_endPoses[i] - this.m_beginPoses[i];
				curByte = new byte[len];
				System.arraycopy(data, this.m_beginPoses[i], curByte, 0, len);
				backStr[i] = new String(curByte, this.m_charset);
			}
		} catch (Exception e) {
			backStr = null;
		}
		return backStr;
	}

	private boolean getPoses(String value, String delimiter, int[] begins,
			int[] ends) {
		StringTokenizer tokens = new StringTokenizer(value, delimiter);

		int[] columnPos = new int[tokens.countTokens()];
		int count = 0;
		int pos = 0;
		try {
			while (tokens.hasMoreTokens()) {
				pos += Integer.parseInt(tokens.nextToken());
				columnPos[(count++)] = pos;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}

		int i = 0;

		for (; i < this.m_indexes.length; i++) {
			if (this.m_indexes[i] == 0)
				begins[i] = 0;
			else {
				begins[i] = columnPos[(this.m_indexes[i] - 1)];
			}
			ends[i] = columnPos[this.m_indexes[i]];
		}

		return true;
	}

	public Schema getSchema() {
		return this.m_schema;
	}

	public String getString(String name) {
		String index = findNameIndex(name);
		if (index == null)
			return null;
		return getString(Integer.parseInt(index));
	}

	public String getString(int columnIndex) {
		return this.m_values[columnIndex];
	}

	public String getValue() {
		return this.m_record;
	}

	private void initImpl(Schema def, String[] names) {
		this.m_schema = def;
		this.m_names = names;
		this.m_needCount = this.m_names.length;
		this.m_values = new String[this.m_needCount];
		this.m_types = new int[this.m_needCount];
		this.m_indexes = new int[this.m_needCount];
		this.m_nameIndexMap = new LinkedHashMap<String,String>();
		int[] types = def.getColumnTypes();
		for (int i = 0; i < names.length; i++) {
			this.m_indexes[i] = this.m_schema.findColumnIndex(names[i]);
			this.m_types[i] = types[this.m_indexes[i]];
			this.m_nameIndexMap.put(names[i], String.valueOf(i));
		}

		this.m_maxIndex = this.m_indexes[0];
		for (int i = 1; i < this.m_indexes.length; i++) {
			if (this.m_indexes[i] > this.m_maxIndex) {
				this.m_maxIndex = this.m_indexes[i];
			}
		}
		if (this.m_type == VARIOUS_BYTE_FIXED) {
			this.m_beginPoses = new int[this.m_needCount];
			this.m_endPoses = new int[this.m_needCount];
			getPoses(def.getProperty("length"), ",", this.m_beginPoses,
					this.m_endPoses);
		} else {
			this.m_charDel = this.m_schema.getProperty("delimiter")
					.toCharArray()[0];
		}
	}

	private String select(String str) {
		if ((str == null) || (str.trim().length() == 0))
			return this.MIN_STRING;
		return str;
	}

	public void setBRLen(byte[] bLen) {
		this.m_recordLen = bLen;
	}

	public boolean setData(byte[] data) {
		if (data == null)
			return false;
		this.m_byteRecord = data;
		if (this.m_schema.getType() == 1) {
			try {
				String[] values = null;
				if (this.m_type == VARIOUS_BYTE_FIXED) {
					values = getMStr(data);
					if ((values == null) || (values.length < this.m_needCount)) {
						return false;
					}
					for (int i = 0; i < this.m_needCount; i++)
						this.m_values[i] = values[i];
				} else {
					values = getMStr(new String(data, this.m_charset),
							this.m_charDel, this.m_maxIndex + 1);
					for (int i = 0; i < this.m_needCount; i++) {
						this.m_values[i] = values[this.m_indexes[i]];
					}
				}
			} catch (Exception e) {
				return false;
			}

		}

		return true;
	}

	public boolean setData(String data) {
		if (data == null)
			return false;
		this.m_record = data;
		if (this.m_schema.getType() == 1) {
			try {
				String[] values = null;
				if (this.m_type == VARIOUS_BYTE_FIXED) {
					values = getMStr(data.getBytes(this.m_charset));
					for (int i = 0; i < this.m_needCount; i++)
						this.m_values[i] = values[i];
				} else {
					this.m_byteRecord = data.getBytes(this.m_charset);
					values = getMStr(data, this.m_charDel, this.m_maxIndex + 1);
					for (int i = 0; i < this.m_needCount; i++)
						this.m_values[i] = values[this.m_indexes[i]];
				}
			} catch (Exception e) {
				e.printStackTrace();

				return false;
			}

		}

		return true;
	}

	public static void setEveryType(int[] every_type) {
		int count = every_type.length;
		m_every_sort_type = new int[count];
		for (int i = 0; i < count; i++)
			if (every_type[i] == DESCEND)
				m_every_sort_type[i] = DESCEND;
			else
				m_every_sort_type[i] = ASCEND;
	}
}
