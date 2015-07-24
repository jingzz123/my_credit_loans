package com.intellicredit.platform.component.transaction;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;

public class Schema implements Serializable {

	private static final long serialVersionUID = 5117456423537734034L;
	public static final int FIXED_LENGTH = 0;
	public static final int VARIOUS_LENGTH = 1;
	// public static final int NESTED = 2;
	public static final int NUMBER = 1;
	public static final int TEXT = 2;
	public static final int DATE = 3;

	public static final int INT = 4;
	public static final int LONG = 5;
	public static final int FLOAT = 6;
	public static final int DOUBLE = 7;
	public static final int STRING = 2;
	// these are package member for formformence
	String[] m_columnNames = null;
	String[] m_columnDesces = null;// Schema的描述
	int[] m_columnPositions = null;
	int[] m_columnLengths = null;
	int[] m_columnTypes = null;
	String m_delimeter = "";
	private Map<String, Integer> m_nameIndexMap;
	private Map<String, Integer> nameTypeMap;// 名称和字段类型的对应关系
	private int m_type; // (FIXED_LENGTH, VARIOUS_LENGTH);
	/*
	 * The following is saved with schema attributes, add by small 2004.4.15 If
	 * the schema is "NestSchema", there must be two key value: =>nested=yes;
	 * id_length={the Id Length of subSchema} If the schema
	 * type="Various_Length", there must be one key value: delimiter= If the
	 * schema is "NestSchema" && its shema and its subSchemas have
	 * "Various_Length" type, there must be one key value: =>field_delimiter=
	 * {the delimiter of all "Various_Length" Schemas and field_delimiter must
	 * be the same}
	 */
	Map<String, String> m_propertyMap = new HashMap<String, String>();
	String m_schemaName = null;

	private String[] sortNames = null;// 排序的数据项名称

	private int[] sortTypes = null;// 排序的字段的排序方式

	private int sortCount = 0;// 排序的字段数量，为了程序处理方便

	/** constructor */
	public Schema() {
	}

	/**
	 * constructor
	 * 
	 * @param columnNames
	 *            column names
	 * @param columnTypes
	 *            column types
	 * @param columnLengths
	 *            column lengths
	 */
	public Schema(String[] columnNames, int[] columnTypes, int[] columnLengths) {
		m_type = FIXED_LENGTH;
		m_columnNames = columnNames;
		m_columnDesces = new String[columnNames.length];
		Arrays.fill(m_columnDesces, "");
		m_columnTypes = columnTypes;
		m_columnPositions = new int[columnLengths.length];
		m_columnLengths = columnLengths;
		m_nameIndexMap = new HashMap<String, Integer>();
		nameTypeMap = new HashMap<String, Integer>();
		int pos = 0;

		for (int i = 0; i < m_columnNames.length; i++) {
			pos += columnLengths[i];
			m_columnPositions[i] = pos;
			m_nameIndexMap.put(m_columnNames[i], new Integer(i));
			nameTypeMap.put(m_columnNames[i], columnTypes[i]);
		}
		m_delimeter = "";
	}

	public void setColumnNames(String[] columnNames) {
		m_columnNames = columnNames;
		if (m_columnLengths != null) { // not set names yet
			m_nameIndexMap = new HashMap<String, Integer>();
			int pos = 0;
			for (int i = 0; i < m_columnLengths.length; i++) {
				pos += m_columnLengths[i];
				m_columnPositions[i] = pos;
				m_nameIndexMap.put(m_columnNames[i], new Integer(i));
			}
		}
		if (m_columnTypes != null) {
			if (nameTypeMap == null) {
				int len = columnNames.length;
				nameTypeMap = new HashMap<String, Integer>();
				for (int i = 0; i < len; i++) {
					nameTypeMap.put(m_columnNames[i], m_columnTypes[i]);
				}
			}
		}
	}

	public void setColumnDesces(String[] columnDesces) {
		m_columnDesces = columnDesces;
	}

	public void setColumnTypes(int[] columnTypes) {
		m_columnTypes = columnTypes;
		if (m_columnNames != null) {
			if (nameTypeMap == null) {
				int len = columnTypes.length;
				nameTypeMap = new HashMap<String, Integer>();
				for (int i = 0; i < len; i++) {
					nameTypeMap.put(m_columnNames[i], m_columnTypes[i]);
				}
			}
		}
	}

	public void setColumnLengths(int[] columnLengths) {
		m_columnLengths = columnLengths;
		m_columnPositions = new int[columnLengths.length];
		m_nameIndexMap = new HashMap<String, Integer>();
		int pos = 0;
		if (m_columnNames != null) { // not set names yet
			for (int i = 0; i < m_columnLengths.length; i++) {
				pos += m_columnLengths[i];
				m_columnPositions[i] = pos;
				m_nameIndexMap.put(m_columnNames[i], new Integer(i));
			}
		}
		m_delimeter = "";
	}

	public void setDelimiter(String delimeter) {
		m_delimeter = delimeter;
	}

	/**
	 * @param columnNames
	 *            column names
	 * @param columnTypes
	 *            column types
	 * @param delimeter
	 *            delimeter to separator the column value. It is used for the
	 *            variable length of record
	 */
	public Schema(String[] columnNames, int[] columnTypes, String delimeter) {
		m_type = VARIOUS_LENGTH;
		m_columnNames = columnNames;
		m_columnDesces = new String[columnNames.length];
		Arrays.fill(m_columnDesces, "");
		m_columnTypes = columnTypes;
		m_delimeter = delimeter;
		m_columnPositions = null;
		m_nameIndexMap = new HashMap<String, Integer>();
		nameTypeMap = new HashMap<String, Integer>();
		for (int i = 0; i < m_columnNames.length; i++) {
			m_nameIndexMap.put(m_columnNames[i], new Integer(i));
			nameTypeMap.put(m_columnNames[i], columnTypes[i]);
		}
	}

	/**
	 * @return number of the columns
	 */
	public int getColumnNum() {
		return m_columnNames.length;
	}

	/**
	 * @return array of column names
	 */
	public String[] getColumnNames() {
		return m_columnNames;
	}

	/**
	 * @return array of column descriptions
	 */
	public String[] getColumnDesces() {
		return m_columnDesces;
	}

	/**
	 * @return array of column types
	 */
	public int[] getColumnTypes() {
		return m_columnTypes;
	}

	/**
	 * @return array of column lengths
	 */
	public int[] getColumnLengths() {
		// modify by small 2004.4.15
		// if(m_type == FIXED_LENGTH ||m_type == NESTED)
		if (m_type == FIXED_LENGTH)
			return m_columnLengths;
		else
			return null;
	}

	/**
	 * @return array of column positions
	 */
	public int[] getColumnPositions() {
		// modify by small 2004.4.15
		// if(m_type == FIXED_LENGTH ||m_type == NESTED)
		if (m_type == FIXED_LENGTH)
			return m_columnPositions;
		else
			return null;
	}

	/**
	 * @return type of the Transaction this schema defined. It can be either
	 *         VARIOUS_LENGTH or FIXED_LENGTH
	 */
	public int getType() {
		return m_type;
	}

	protected void setType(int type) {
		m_type = type;
	}

	/**
	 * @return delimeter used if the data record represented by the schema has
	 *         variable length.
	 */
	public String getDelimeter() {
		if (m_type == VARIOUS_LENGTH)
			return m_delimeter;
		else
			return null;
	}

	/**
	 * @param columnName
	 *            column name
	 * @return column index
	 */
	public int findColumnIndex(String columnName) {
		// if(m_type == FIXED_LENGTH) {
		Object obj = m_nameIndexMap.get(columnName);
		if (obj != null)
			return ((Integer) obj).intValue();
		// }
		return -1;
	}

	/*
	 * @param recordDef The Schema object to compared with this Schemareturn
	 * true if this recordDef's is a subset of the parameter recordDef
	 */
	public boolean isCompatible1(Schema def) {
		int[] types = def.getColumnTypes();
		int[] lengths = def.getColumnLengths();
		for (int i = 0; i < m_columnNames.length; i++) {
			if (m_columnTypes[i] != types[i]
					|| m_columnLengths[i] != lengths[i])
				return false;
		}
		return true;
	}

	/*
	 * @param recordDef The Schema object to compared with this Schemareturn
	 * true if this recordDef is a subset of the parameter recordDef
	 */
	public boolean isCompatible(Schema recordDef) {
		boolean notFound = false;
		outer: for (int i = 0; i < m_columnNames.length; i++) {
			for (int j = 0; j < recordDef.m_columnNames.length; j++) {
				if (recordDef.m_columnNames[j].equals(m_columnNames[i])
						&& recordDef.m_columnTypes[j] == m_columnTypes[i]) {
					continue outer;
				}
			}
			notFound = true;
			break;
		}
		return (!notFound);
	}

	/**
	 * @param i
	 *            index of type
	 * @return name of type
	 */
	public static String convertTypeString(int i) {
		switch (i) {
		case 1:
			return "NUMBER";
		case 2:
			return "TEXT";
		case 3:
			return "DATE";
		case 4:
			return "INT";
		case 5:
			return "LONG";
		case 6:
			return "FLOAT";
		case 7:
			return "DOUBLE";
		default:
			return "TEXT";
		}
	}

	// add by small 2004.4.15
	public void setProperty(String key, String value) {
		m_propertyMap.put(key, value);
	}

	public String getProperty(String key) {
		if (m_propertyMap.containsKey(key))
			return m_propertyMap.get(key);
		else
			return null;
	}

	// add by small 2004.4.14
	public void setName(String name) {
		m_schemaName = name;
	}

	public String getName() {
		return m_schemaName;
	}

	/**
	 * 根据名称得到字段类型
	 * 
	 * @param name
	 * @return
	 */
	public int getSingleColumnType(String name) {
		return nameTypeMap.get(name);
	}

	public String[] getSortNames() {
		return sortNames;
	}

	public void setSortNames(String[] sortNames) {
		this.sortNames = sortNames;
		if (this.sortNames != null) {
			this.sortCount = sortNames.length;
		}
	}

	public int[] getSortTypes() {
		return sortTypes;
	}

	public void setSortTypes(int[] sortTypes) {
		this.sortTypes = sortTypes;
	}

	public int getSortCount() {
		return sortCount;
	}

}
