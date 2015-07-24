package com.intellicredit.platform.component.validator2;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.intellicredit.platform.component.situation2.Operator;
import com.intellicredit.platform.component.transaction.Transaction;
import com.intellicredit.platform.component.validator2.compare.Compare;
import com.intellicredit.platform.component.validator2.function.CheackConfirmedDetail;
import com.intellicredit.platform.component.validator2.function.CheckChineseName;
import com.intellicredit.platform.component.validator2.function.CheckIDNo;
import com.intellicredit.platform.component.validator2.function.CheckTelephone;
import com.intellicredit.platform.component.validator2.function.Function;
import com.intellicredit.platform.component.validator2.rule.Rule;
import com.intellicredit.platform.util.ValidatorConstants;

/**
 * <p>
 * The <code>Validator</code> class allows an application component or client to
 * provide data, and determine if the data is valid for the requested type.
 * </p>
 */
public class Validator {

	
	// The instances of this class for use (singleton design pattern)
	private static Map<String, Validator> m_instances = null;
	
	private static Map<String,Function> m_functions = null;//所有函数
	
	// private URL schemaURL; //The URL of the XML Schema for this
	// <code>Validator</code>
	private Map<String, List<Constraint>> m_constraints; // The constraints for this
													// XML Schema
	public static DateFormat m_dataFormat = new SimpleDateFormat("yyyyy-MM-dd");
	public static DateFormat m_dataFormat_2 = new SimpleDateFormat("yyyyy/MM/dd");
	private StrDateCheck m_dateCheck = new StrDateCheck(); // 用来检查8位[YYYYMMDD]或者16位[YYYYMMDDhhmmss]日期的正确性
	private static Map<Integer, String> m_errorMap = null;
	
	/**
	 * <p>
	 * This constructor is private so that the class cannot be instantiated
	 * </p>
	 * 
	 * @param schemaURL
	 *            <code>URL</code> to parse the schema at.
	 * @throws <code>IOException</code> - when errors in parsing occur.
	 */
	private Validator(InputStream schemaStream) throws IOException {
		// this.schemaURL = schemaURL;
		SchemaParser parser = new SchemaParser(schemaStream); // parse the XML
															// Schema and create
															// the constraints
		m_constraints = parser.getConstraints();
	}

	private Validator(InputStream schemaStream, String encoding) throws IOException {
		// this.schemaURL = schemaURL;
		// parse the XML Schema and create the constraint
		SchemaParser parser = new SchemaParser(schemaStream, encoding); 
		m_constraints = parser.getConstraints();
	}

	public String[] getConstraintIds() {
		return (String[]) m_constraints.keySet().toArray(new String[0]);
	}

	public String getDescripiton(String constraintName) {
		List<Constraint>list = m_constraints.get(constraintName);
		if (list == null) {
			return constraintName;
		} else {
			return (list.get(0)).getDescription();
		}
	}

	/**
	 * <p>
	 * This will return the instance for the specific XML Schema URL. If a
	 * schema exists, it is returned (as parsing will already be done);
	 * otherwise, a new instance is created, and then returned.
	 * </p>
	 * 
	 * @param schemaURL
	 *            <code>URL</code> of schema to validate against.
	 * @return <code>Validator</code> - the instance, ready to use.
	 * @throws <code>IOException</code> - when errors in parsing occur.
	 */
	public static synchronized Validator getInstance(URL schemaURL,InputStream schemaStream)
			throws IOException {
		return getInstance(schemaURL,schemaStream, ValidatorConstants.ENCODING);
	}

	public static synchronized Validator getInstance(URL schemaURL, InputStream schemaStream, String charset) throws IOException {
		if (m_instances != null) {
			if (m_instances.containsKey(schemaURL.toString())) {
				return (Validator) m_instances.get(schemaURL.toString());
			} else {
				Validator validator = new Validator(schemaStream);
				m_instances.put(schemaURL.toString(), validator);
				return validator;
			}
		} else {
			m_instances = new LinkedHashMap<String, Validator>();
			Validator validator = new Validator(schemaStream, charset);
			m_instances.put(schemaURL.toString(), validator);
			return validator;
		}
	}
	
	/**
	 * 返回检测结果
	 * @param constraintName
	 * @param data
	 * @param transaction：传入的Transaction
	 * @param parameterValue：传入的一个参数
	 * @return
	 */
	public ValidateResult checkValidity(String constraintName, String data,
			Transaction transaction, Map<String,Object> parameterValue) {
		List<Constraint> list = m_constraints.get(constraintName);
		ValidateResult result = new ValidateResult();
		result.setErrorCode(0);
		if(list==null||list.size()==0){
			return result;
		}
		int size = list.size();
		for(int i=0; i<size; i++){
			result = checkValidity(list.get(i),data,transaction,parameterValue);
			if(result.getErrorCode()!=0){
				return result;
			}
		}
		return result;
	}
	
	/*
	public int checkValidity(String constraintName, String data) {
		List<Constraint> list = m_constraints.get(constraintName);
		if(list==null||list.size()==0){
			return 0;
		}
		int size = list.size();
		for(int i=0; i<size; i++){
			int val = checkValidity(list.get(i),data);
			if(val!=0){
				return val;
			}
		}
		return 0;
	}*/
	
	/**
	 * <p>
	 * This will validate a data value (in <code>String</code> format) against a
	 * specific constraint, and return an error message if there is a problem.
	 * </p>
	 * 
	 * @param constraintName
	 *            the identifier in the constraints to validate this data
	 *            against.
	 * @param data
	 *            <code>String</code> data to validate.
	 * @return <code>Int</code> - Error Code, 0 means no error.
	 */
	public ValidateResult checkValidity(Constraint constraint, String data, 
			Transaction transaction, Map<String,Object> parameterValue) {
		// 首先看是否要求判断
		/*
		 * if (!constraint.isUse()) { //如果不要求判断 return 0; }
		 */
		// 首先判断是否可以为空
		// 先判断是否为空
		ValidateResult result = new ValidateResult();
		if (data == null || data.trim().length() == 0) {
			if (!constraint.getAllowNull()) {
				result.setErrorCode(11);
				result.setErrorMsg(m_errorMap.get(11));
				return result; // 不允许为空
			} else {
				result.setErrorCode(0);
				return result;
			}
		}
		int beginPos = constraint.getBeginPos();
		int endPos = constraint.getEndPos();
		if(beginPos>=0&&endPos<=data.length()&&beginPos<=endPos){//暂时这么处理
			try{
				data = data.substring(beginPos,endPos);
			}catch(Exception e){
				e.printStackTrace();
				result.setErrorCode(1);
				result.setErrorMsg(m_errorMap.get(1));
				return result;
			}
		}
		// Validate against allowed values
		if (constraint.hasAllowedValues()) {
			// List allowedValues = constraint.getAllowedValues();
			// if (constraint.getAllowedValues().contains(data)) {
			if (constraint.containValue(data)) {
				result.setErrorCode(0);
				if(!constraint.hasCompare()){
					return result;
				}
			} else {
				result.setErrorCode(2);
				result.setErrorMsg(m_errorMap.get(2));
				return result; // 值不在特定范围
			}
		}
		// 判断队齐方式
		if (data.trim().length() != 0) {
			if (constraint.getAlignType().equals(Constraint.ALIGN_LEFT)) {
				if (data.substring(0, 1).equals(" ")) {
					result.setErrorCode(12);
					result.setErrorMsg(m_errorMap.get(12));
					return result;
				}
			} else if (constraint.getAlignType().equals(Constraint.ALIGN_RIGHT)) {
				if (data.substring(data.length() - 1).equals(" ")) {
					result.setErrorCode(12);
					result.setErrorMsg(m_errorMap.get(12));
					return result;
				}
			}
		}
		data = data.trim();
		// Validate data type
		if (!correctDataType(data, constraint.getDataType())) {
			result.setErrorCode(1);
			result.setErrorMsg(m_errorMap.get(1));
			return result; // 数据类型正确
		}

		if (constraint.hasLength()) {
			if (data.length() != constraint.getLength()) {
				result.setErrorCode(7);
				result.setErrorMsg(m_errorMap.get(7));
				return result; // 数据长度不正确
			}
		}

		// Validate against range specifications
		if (constraint.hasMinInclusive()) {
			if (compare(data, constraint.getMinInclusive(),
					constraint.getDataType()) < 0) {
				result.setErrorCode(4);
				result.setErrorMsg(m_errorMap.get(4));
				return result; // 数据不能小于规定值
			}
		} else if (constraint.hasMinExclusive()) {
			if (compare(data, constraint.getMinExclusive(),
					constraint.getDataType()) <= 0) {
				result.setErrorCode(3);
				result.setErrorMsg(m_errorMap.get(3));
				return result; // 数据不能小于等于规定值
			}
		}
		if (constraint.hasMaxExclusive()) {
			if (compare(data, constraint.getMaxInclusive(),
					constraint.getDataType()) > 0) {
				result.setErrorCode(5);
				result.setErrorMsg(m_errorMap.get(5));
				return result; // 数据不能大于等于规定值
			}
		} else if (constraint.hasMaxInclusive()) {
			if (compare(data, constraint.getMaxExclusive(),
					constraint.getDataType()) >= 0) {
				result.setErrorCode(6);
				result.setErrorMsg(m_errorMap.get(6));
				return result; // 数据必须小于等于规定值
			}
		}

		if (constraint.hasMinLength()) {
			if (data.length() < constraint.getMinLength()) {
				result.setErrorCode(8);
				result.setErrorMsg(m_errorMap.get(8));
				return result; // 数据长度不能小于特定值
			}
		}

		if (constraint.hasMaxLength()) {
			if (data.length() > constraint.getMaxLength()) {
				result.setErrorCode(9);
				result.setErrorMsg(m_errorMap.get(9));
				return result; // 数据长度不能大于特定值
			}
		}

		if (constraint.hasPattern()) {
			Pattern pattern = Pattern.compile(constraint.getPattern());
			Matcher matcher = pattern.matcher(data);
			if (!matcher.matches()) {
				result.setErrorCode(10);
				result.setErrorMsg(m_errorMap.get(10));
				return result; // 数据必须匹配特定正规表达式
			}
		}
		if(constraint.hasFunction()){
			if(!this.checkFunction(constraint.getFunction(), data)){
				result.setErrorCode(13);
				result.setErrorMsg(m_errorMap.get(13));
				return result;
			}
		}
		if(constraint.hasCompare()){//需要 比較
			List<Compare> compareList = constraint.getCompares();
			for(Compare comp: compareList){
				if(!outerCompare(data, constraint.getDataType(),
						comp, transaction, parameterValue)){
					result.setErrorCode(10);
					result.setErrorMsg(comp.getErrorMsg());
					return result;
				}
			}
		}
		if(constraint.hasRules()){
			List<Rule> list = constraint.getRules();
			for(Rule rule : list){
				if(!rule.check(transaction,parameterValue)){
					result.setErrorCode(10);
					result.setErrorMsg(rule.getDesc());
					return result;
				}
			}
		}
		result.setErrorCode(0);
		return result;
	}

	private int compare(String data, String val, String dataType) {
		if (dataType.equals("int") || dataType.equals("long")
				|| dataType.equals("idate") || dataType.equals("idatetime")) {
			try {
				if (Long.parseLong(data) > Long.parseLong(val)) {
					return 1;
				} else if (Long.parseLong(data) == Long.parseLong(val)) {
					return 0;
				} else {
					return -1;
				}
			} catch (NumberFormatException nf) {
				// ;
			}
		} else if (dataType.equals("string") || dataType.equals("string_eng")) {
			; // 字符串比较
		}
		return 0; // 相等
	}

	/**
	 * <p>
	 * This will test the supplied data to see if it can be converted to the
	 * Java data type given in <code>dataType</code>.
	 * </p>
	 * 
	 * @param data
	 *            <code>String</code> to test data type of.
	 * @param dataType
	 *            <code>String</code> name of Java class to convert to.
	 * @return <code>boolean</code> - whether conversion can occur.
	 */
	private boolean correctDataType(String data, String dataType) {
		if(dataType==null){
			return true;
		}
		// 将long和int
		if ((dataType.equals("long")) || (dataType.equals("int"))
				|| (dataType.equals("java.lang.Integer"))
				|| (dataType.equals("java.lang.Long"))) {
			try {
				// Integer test = new Integer(data);
				new Long(data);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else if (dataType.equals("idate")) {
			if (data.length() != 8) {
				return false;
			}
			return m_dateCheck.check(data);
		} else if (dataType.equals("idatetime")) {
			if (data.length() != 14) {
				return false;
			}
			return m_dateCheck.check(data);
		} else if (dataType.equals("date") || dataType.equals("java.util.Date")) {
			try {
				m_dataFormat.parse(data);
				return true;
			} catch (java.text.ParseException e) {
				return false;
			}
		} else if (dataType.equals("date2")) {// 支持yyyy/MM/dd
			try {
				m_dataFormat_2.parse(data);
				return true;
			} catch (java.text.ParseException e) {
				return false;
			}
		} else if ((dataType.equals("string"))
				|| (dataType.equals("java.lang.String"))) {
			return true;
		} else if (dataType.equals("string_eng")) { // 只能有'0'~'9'和'A'-'Z'和'a'~'z'
			// String match = "[a-z0-9A-Z]";最后需要换为正规表达式来实现
			try {
				int len = data.length();
				int bLen = data.getBytes("GB18030").length;
				if (len == bLen) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		} else if ((dataType.equals("float"))
				|| (dataType.equals("java.lang.Float"))) {
			try {
				new Float(data);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else if ((dataType.equals("double"))
				|| (dataType.equals("java.lang.Double"))) {
			try {
				new Double(data);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else if (dataType.equals("java.lang.Boolean")) {
			if ((data.equalsIgnoreCase("true"))
					|| (data.equalsIgnoreCase("false"))
					|| (data.equalsIgnoreCase("yes"))
					|| (data.equalsIgnoreCase("no"))) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param data：当前参数的值
	 * @param dataType：当前参数类型
	 * @param compare：比对信息
	 * @return
	 */
	private boolean outerCompare(String data, String dataType, 
			Compare compare, Transaction transaction, Map<String,Object> parameterValue){
		Operator operator = compare.getOperator();//操作类型
		String parameter = compare.getParameter();
		String value = parameter;
		
		if(parameter.charAt(0)=='#'){//参数值从传入的Map中获取
			if(parameterValue.get(parameter.substring(1))!=null){
				value = String.valueOf(parameterValue.get(parameter.substring(1)));
			}else{
				value = null;
			}
		}else if(parameter.charAt(0)=='$'){
			value = transaction.getString(parameter.substring(1));
		}else{
			value= parameter;
		}
		if(value==null){
			//value = "";
			return true;
		}
		if ((dataType.equals("long")) 
				|| (dataType.equals("int"))
				|| (dataType.equals("idate"))
				|| (dataType.equals("idatetime"))
				|| (dataType.equals("java.lang.Integer"))
				|| (dataType.equals("java.lang.Long"))) {
			try {
				// Integer test = new Integer(data);
				return operator.eval(new Long(data), new Long(value));
			} catch (NumberFormatException e) {
				return false;
			}
		} else if ((dataType.equals("float"))
				|| (dataType.equals("java.lang.Float"))) {
			try {
				return operator.eval(new Float(data), new Float(value));
			} catch (NumberFormatException e) {
				return false;
			}
		} else if ((dataType.equals("double"))
				|| (dataType.equals("java.lang.Double"))) {
			try {
				return operator.eval(new Double(data), new Double(value));
			} catch (NumberFormatException e) {
				return false;
			}
		}else if (dataType.equals("date") || dataType.equals("java.util.Date")) {//yyyy-MM-dd
			try {
				return operator.eval(m_dataFormat.parse(data),m_dataFormat.parse(value));
			} catch (java.text.ParseException e) {
				return false;
			}
		} else if (dataType.equals("date2")) {// 支持yyyy/MM/dd
			try {
				return operator.eval(m_dataFormat_2.parse(data),m_dataFormat_2.parse(value));
			} catch (java.text.ParseException e) {
				return false;
			}
		} else if ((dataType.equals("string"))
				|| dataType.equals("string_eng")
				|| (dataType.equals("java.lang.String"))) {
			return operator.eval(data,value);
		}
		return true;
		
	}
	
	/**
	 * 
	 * @param functionName：函数名称
	 * @param value：当前的值
	 * @return
	 */
	private boolean checkFunction(String functionName, String value){
		Function func = Validator.getFunction(functionName);
		if(func==null){
			return true;
		}
		Stack<Object> stacks = new Stack<Object>();
		stacks.push(value);
		return func.run(stacks);

	}

	static {
		m_errorMap = new HashMap<Integer, String>();
		m_errorMap.put(new Integer(0), "数据通过验证");
		m_errorMap.put(new Integer(1), "数据类型不正确");
		m_errorMap.put(new Integer(2), "数据值不在特定范围");
		m_errorMap.put(new Integer(3), "数据必须大于规定值");
		m_errorMap.put(new Integer(4), "数据必须大于等于规定值");
		m_errorMap.put(new Integer(5), "数据必须小于规定值");
		m_errorMap.put(new Integer(6), "数据必须小于等于规定值");
		m_errorMap.put(new Integer(7), "数据长度不正确");
		m_errorMap.put(new Integer(8), "数据长度必须大于特定值");
		m_errorMap.put(new Integer(9), "数据长度必须小于特定值");
		m_errorMap.put(new Integer(10), "数据必须匹配特定正规表达式");
		m_errorMap.put(new Integer(11), "数据不允许为空");
		m_errorMap.put(new Integer(12), "数据不满足队齐的条件");
		m_errorMap.put(new Integer(13), "数据不满足定义的规则！");		
		m_functions = new HashMap<String,Function>();
		m_functions.put("check_id_no", new CheckIDNo());//校验身份证
		m_functions.put("check_chinese_name", new CheckChineseName());//校验中文名称
		m_functions.put("check_telephone", new CheckTelephone());
		m_functions.put("check_confirmedDetails", new CheackConfirmedDetail());
	}

	public static String getErrorInfo(Integer obj) {
		Object value = m_errorMap.get(obj);
		if (value != null) {
			return String.valueOf(value);
		}
		return null;
	}

	public static String getErrorInfo(int errId) {
		return getErrorInfo(new Integer(errId));
	}

	public static Function getFunction(String key){
		return m_functions.get(key);
	}
	
}
