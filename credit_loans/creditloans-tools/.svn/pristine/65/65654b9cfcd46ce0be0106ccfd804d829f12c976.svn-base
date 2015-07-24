package cn.creditloans.tools.validator.transaction;

import cn.creditloans.tools.validator.util.ValidatorConstants;

public class NestSchema extends Schema {
	private static final long serialVersionUID = 1515932132512205796L;
	private Schema[] m_subschemas = null;

	/** Creates a new instance of Schema1 */
	public NestSchema() {
		// setType(NESTED);
		super();
		m_propertyMap.put(ValidatorConstants.NESTED,
				ValidatorConstants.NESTED_YES);
		m_propertyMap.put(ValidatorConstants.ID_LENGTH,
				ValidatorConstants.ID_DEFAULT_LENGTH);
	}

	/**
	 * Constructor
	 * 
	 * @param columnNames
	 *            column names
	 * @param columnTypes
	 *            column types
	 * @param columnLengths
	 *            column lengths
	 */
	public NestSchema(String[] columnNames, int[] columnTypes,
			int[] columnLengths) {
		super(columnNames, columnTypes, columnLengths);
		// setType(NESTED);
		m_propertyMap.put(ValidatorConstants.NESTED,
				ValidatorConstants.NESTED_YES);
		m_propertyMap.put(ValidatorConstants.ID_LENGTH,
				ValidatorConstants.ID_DEFAULT_LENGTH);
	}

	/**
	 * Constructor
	 * 
	 * @param columnNames
	 *            column names
	 * @param columnTypes
	 *            column types
	 * @param delimeter
	 */
	public NestSchema(String[] columnNames, int[] columnTypes, String delimeter) {
		super(columnNames, columnTypes, delimeter);
		// setType(NESTED);
		m_propertyMap.put(ValidatorConstants.NESTED,
				ValidatorConstants.NESTED_YES);
		m_propertyMap.put(ValidatorConstants.ID_LENGTH,
				ValidatorConstants.ID_DEFAULT_LENGTH);
	}

	/**
	 * set sub schemas
	 * 
	 * @param schemas
	 *            sub schema
	 */
	/*
	 * public void setColumnNames(String[] columnNames) {
	 * super.setColumnNames(columnNames); }
	 * 
	 * public void setColumnTypes(int[] columnTypes) {
	 * super.setColumnTypes(columnTypes); }
	 * 
	 * public void setColumnLengths(int[] columnLengths) {
	 * super.setColumnLengths(columnLengths); }
	 */
	public void setSubSchemas(Schema[] schemas) {
		m_subschemas = schemas;
	}

	/**
	 * get sub schemas
	 * 
	 * @return sub schemas
	 */
	public Schema[] getSubSchemas() {
		return m_subschemas;
	}
}
