package com.intellicredit.platform.component.validator2;

import java.util.LinkedList;
import java.util.List;

import com.intellicredit.platform.component.validator2.compare.Compare;
import com.intellicredit.platform.component.validator2.rule.Rule;

/**
 * <p>
 *  The <code>Constraint</code> class represents a single data constraint,
 *    including the data type, allowed values, and required ranges.
 * </p>
 */
public class Constraint {

    public static final String ALIGN_LEFT = "Left";
    public static final String ALIGN_RIGHT = "Right";
    public static final String ALIGN_NONE = "None";
    private String m_desc = "";//描述
    private boolean m_use;//是否检测
    private String m_name;//The identifier for this constraint
    private String dataType;//The Java data type for this constraint
    private String m_alignType;//队齐方式
    private String m_minInclusive;//Minimum inclusive value allowed
    private String m_minExclusive;//Minimum exclusive value allowed
    private String m_maxInclusive;//Maximum inclusive value allowed
    private String m_maxExclusive;//Maximum exclusive value allowed
    private List<String> allowedValues;//Allowed values
    private List<String> m_bufferValues;//
    private String m_pattern;//正规表达式

    private int m_length;
    private int m_maxLength;
    private int m_minLength;
    private String m_default;

    private boolean m_allowNull;//是否允许为空,缺省为不允许为空
    
    private int beginPos = Integer.MIN_VALUE;//取值的起始位置
    
    private int endPos = Integer.MAX_VALUE;//取值的截止位置
    
    private List<Compare> compares;//主要做比較使用
    
    private List<Rule> rules;//可以包含多个规则
    
    private String function;//满足函数，暂时只做一个简单的就可以了

    /**
     * <p>
     *  This will create a new <code>Constraints</code> with the specified
     *    identifier as the "name".
     * </p>
     *
     * @param identifier <code>String</code> identifier for <code>Constraint</code>.
     */
    public Constraint(String name) {
        this.m_name = name;
        m_alignType = ALIGN_NONE;
        // Null out values
        m_minInclusive = "";
        m_minExclusive = "";
        m_maxInclusive = "";
        m_maxExclusive = "";
        m_length = Integer.MIN_VALUE;
        m_minLength = Integer.MIN_VALUE;
        m_maxLength = Integer.MIN_VALUE;
        m_pattern = null;
        m_whiteSpace = null;
        m_allowNull = false;
        allowedValues = new LinkedList<String>();// Allocate storage for allowed values
        m_bufferValues = new LinkedList<String>();
        compares = null;
        rules = new LinkedList<Rule>();
    }

    /**
     * <p>
     *  This will return the identifier for this <code>Constraint</code>.
     * </p>
     *
     * @return <code>String</code> - identifier for this constraint.
     */
    public String getName() {
        return m_name;
    }

    public void setDescription(String description){
        m_desc = description.trim();
    }

    public String getDescription(){
        if(m_desc==null || m_desc.length()==0){
            return m_name;
        }
        return m_desc;
    }

    /**
     * @param dataType <code>String</code> that is the Java data type for this constraint.
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * @return <code>String</code> - the data type for this constraint.
     */
    public String getDataType() {
        return dataType;
    }

    public void setAlignType(String alignType){
        if(m_alignType.equals(ALIGN_LEFT)||
           m_alignType.equals(ALIGN_RIGHT)||
           m_alignType.equals(ALIGN_NONE)){
            m_alignType = alignType;
        }else{
            m_alignType = ALIGN_NONE;
        }
    }

    public String getAlignType(){
        return m_alignType;
    }
    /**
     * <p>
     *  This will set the minimum allowed value for this data type (inclusive).
     * </p>
     *
     * @param m_minInclusive minimum allowed value (inclusive)
     */
    public void setMinInclusive(String m_minInclusive) {
        this.m_minInclusive = m_minInclusive;
    }

    /**
     * <p>
     *  This will return the minimum allowed value for this data type (inclusive).
     * </p>
     *
     * @return <code>String</code> - minimum value allowed (inclusive)
     */
    public String getMinInclusive() {
        return m_minInclusive;
    }

    /**
     * <p>
     *  This will return <code>true</code> if a minimum value (inclusive) constraint
     *    exists.
     * </p>
     *
     * @return <code>boolean</code> - whether there is a constraint for the
     *         minimum value (inclusive)
     */
    public boolean hasMinInclusive() {
        if(m_minInclusive.equals(""))
            return false;
        else
            return true;
    }

    public boolean hasLength() {
        if(m_length == Integer.MIN_VALUE)
         return false;
        else
            return true;
    }

    public boolean hasMinLength() {
        if(m_minLength == Integer.MIN_VALUE)
            return false;
        else
            return true;
    }

    public boolean hasMaxLength() {
        if(m_maxLength == Integer.MIN_VALUE)
         return false;
        else
            return true;

    }
    public boolean hasPattern() {
        if(m_pattern == null)
         return false;
        else
            return true;
    }

    public boolean hasWhiteSpace() {
        if(m_whiteSpace == null)
         return false;
        else
            return true;
    }

  /*  public boolean hasTime() {
        if(m_time == null)
         return false;
        else
            return true;
    }
    */
    /**
     * <p>
     *  This will set the minimum allowed value for this data type (exclusive).
     * </p>
     *
     * @param m_minInclusive minimum allowed value (exclusive)
     */
    public void setMinExclusive(String m_minExclusive) {
        this.m_minExclusive = m_minExclusive;
    }

    /**
     * <p>
     *  This will return the minimum allowed value for this data type (exclusive).
     * </p>
     *
     * @return <code>String</code> - minimum value allowed (exclusive)
     */
    public String getMinExclusive() {
        return m_minExclusive;
    }

    /**
     * <p>
     *  This will return <code>true</code> if a minimum value (exclusive) constraint
     *    exists.
     * </p>
     *
     * @return <code>boolean</code> - whether there is a constraint for the
     *         minimum value (exclusive)
     */
    public boolean hasMinExclusive() {
        if(m_minExclusive.equals(""))
             return false;
        else
            return true;
    }

    /**
     * <p>
     *  This will set the maximum allowed value for this data type (inclusive).
     * </p>
     *
     * @param m_maxInclusive maximum allowed value (inclusive)
     */
    public void setMaxInclusive(String m_maxInclusive) {
        this.m_maxInclusive = m_maxInclusive;
    }

    /**
     * <p>
     *  This will return the maximum allowed value for this data type (inclusive).
     * </p>
     *
     * @return <code>string</code> - maximum value allowed (inclusive)
     */
    public String getMaxInclusive() {
        return m_maxInclusive;
    }

    /**
     * <p>
     *  This will return <code>true</code> if a maximum value (inclusive) constraint
     *    exists.
     * </p>
     *
     * @return <code>boolean</code> - whether there is a constraint for the
     *         maximum value (inclusive)
     */
    public boolean hasMaxInclusive() {
        if(m_maxInclusive.equals(""))
             return false;
        else
            return true;
    }

    /**
     * <p>
     *  This will set the maximum allowed value for this data type (exclusive).
     * </p>
     *
     * @param m_maxInclusive maximum allowed value (exclusive)
     */
    public void setMaxExclusive(String m_maxExclusive) {
        this.m_maxExclusive = m_maxExclusive;
    }

    /**
     * <p>
     *  This will return the maximum allowed value for this data type (exclusive).
     * </p>
     *
     * @return <code>string</code> - maximum value allowed (exclusive)
     */
    public String getMaxExclusive() {
        return m_maxExclusive;
    }

    /**
     * <p>
     *  This will return <code>true</code> if a maximum value (exclusive) constraint
     *    exists.
     * </p>
     *
     * @return <code>boolean</code> - whether there is a constraint for the
     *         maximum value (exclusive)
     */
    public boolean hasMaxExclusive() {
        if(m_maxExclusive.equals(""))
             return false;
        else
            return true;
    }

    /**
     * <p>
     *  This will add another value to the list of allowed values for this data type.
     * </p>
     *
     * @param value <code>String</code> value to add.
     */
    public void addAllowedValue(String value) {
        allowedValues.add(value);
    }

    /**
     * <p>
     *  This will return the list of allowed values for this data type.
     * </p>
     *
     * @return <code>List</code> - allowed values for this <code>Constraint</code>.
     */
    public List<String> getAllowedValues() {
        return allowedValues;
    }

    public boolean containValue(String value){
        if(m_bufferValues.contains(value)){
            return true;
        }else{
            if(allowedValues.contains(value)){
                m_bufferValues.add(0,value);
                return true;
            }
        }
        return false;
    }
    /**
     * <p>
     *  This will indicate if there are a set of allowed values for this data type.
     * </p>
     *
     * @return <code>boolean</code> - whether there are allowed values for this type.
     */
    public boolean hasAllowedValues() {
        return (allowedValues.size() > 0);
    }

    public void setPattern(String pattern) {
        m_pattern = pattern;
    }

    public String getPattern() {
        return m_pattern;
    }
    String m_whiteSpace;
    public void setWhiteSpace(String value) {
        m_whiteSpace = value;
    }

    public String getWhiteSpace() {
        return m_whiteSpace;
    }

    public void setLength(int len) {
        m_length = len;
    }

    public int getLength() {
        return m_length;
    }

    public void setMaxLength(int len) {
        m_maxLength = len;
    }

    public int getMaxLength() {
        return m_maxLength;
    }

    public void setMinLength(int len) {
        m_minLength = len;
    }

    public int getMinLength() {
        return m_minLength;
    }

    public void setDefault(String val) {
        m_default=val;
    }

    public String getDefault() {
        return m_default;
    }

    public void setUse(boolean use) {
        m_use = use;
    }

    public boolean isUse() {
        return m_use;
    }

    //看是否允许为空
    public void setAllowNull(boolean allow) {
        m_allowNull = allow;
    }

    public boolean getAllowNull() {
        return m_allowNull;
    }

	public int getBeginPos() {
		return beginPos;
	}

	public void setBeginPos(int beginPos) {
		this.beginPos = beginPos;
	}

	public int getEndPos() {
		return endPos;
	}

	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}

	public List<Compare> getCompares() {
		return compares;
	}

	public void setCompares(List<Compare> compare) {
		this.compares = compare;
	}
	
    public boolean hasCompare() {
        if(compares == null||compares.size()==0)
         return false;
        else
            return true;
    }

    public void putRule(Rule rule){
    	rules.add(rule);
    }
    
    public List<Rule> getRules(){
    	return rules;
    }
    
    public boolean  hasRules(){
    	if(rules!=null&&rules.size()>0){
    		return true;
    	}
    	return false;
    }

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
	
	public boolean hasFunction(){
		if(function==null||function.trim().equals("")){
			return false;
		}
		return true;
	}
    
}
