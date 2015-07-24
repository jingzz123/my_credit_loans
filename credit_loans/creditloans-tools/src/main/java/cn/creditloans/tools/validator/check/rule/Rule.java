package cn.creditloans.tools.validator.check.rule;

import java.util.Map;
import java.util.Stack;

import cn.creditloans.tools.validator.check.Validator;
import cn.creditloans.tools.validator.check.compare.Operator;
import cn.creditloans.tools.validator.check.function.Function;
import cn.creditloans.tools.validator.transaction.Transaction;

public class Rule {
	private String name;
	
	private String desc;
	
	private String constraintName;//当前constraint的名称，也是Transaction的某项的名称
	
	private RuleCondition condition;
	
	private RuleAction action;
	
	/**
	 * 检测，这里暂时只处理int,long
	 * @return
	 */
	public boolean check(Transaction transaction, Map<String,Object> parameterValue){
		if(transaction==null){
			return true;
		}
		String cValue = transaction.getString(constraintName);
		if(cValue==null){
			return true;
		}
		//检测 condition
		String lhs = condition.getLhs();
		String rhs = condition.getRhs();
		Operator operator = condition.getOpertor();
		String dataType = condition.getValueType();
		String lValue = getValue(lhs,transaction,parameterValue);
		String rValue = getValue(rhs,transaction,parameterValue);
		if(lValue==null||rValue==null){//条件又问题，此规则不进行处理
			return true;
		}
		boolean conditionCheck = false;
		if ((dataType.equals("long")) 
				|| (dataType.equals("int"))
				|| (dataType.equals("idate"))
				|| (dataType.equals("idatetime"))
				|| (dataType.equals("java.lang.Integer"))
				|| (dataType.equals("java.lang.Long"))) {
			try {
				// Integer test = new Integer(data);
				conditionCheck = operator.eval(new Long(lValue), new Long(rValue));
			} catch (NumberFormatException e) {//条件有错
				e.printStackTrace();
				return true;
			}
		} else if ((dataType.equals("float"))
				|| (dataType.equals("java.lang.Float"))) {
			try {
				conditionCheck = operator.eval(new Float(lValue), new Float(rValue));
			} catch (NumberFormatException e) {//条件有错
				return true;
			}
		} else if ((dataType.equals("double"))
				|| (dataType.equals("java.lang.Double"))) {
			try {
				conditionCheck = operator.eval(new Double(lValue), new Double(rValue));
			} catch (NumberFormatException e) {//条件有错
				return true;
			}
		}else if (dataType.equals("date") || dataType.equals("java.util.Date")) {//yyyy-MM-dd
			try {
				conditionCheck = operator.eval(Validator.m_dataFormat.parse(lValue),Validator.m_dataFormat.parse(rValue));
			} catch (java.text.ParseException e) {//条件有错
				return true;
			}
		} else if (dataType.equals("date2")) {// 支持yyyy/MM/dd
			try {
				conditionCheck = operator.eval(Validator.m_dataFormat_2.parse(lValue),Validator.m_dataFormat_2.parse(rValue));
			} catch (java.text.ParseException e) {//条件有错
				return true;
			}
		} else if ((dataType.equals("string"))
				|| dataType.equals("string_eng")
				|| (dataType.equals("java.lang.String"))) {//条件有错
			conditionCheck = operator.eval(lValue,rValue);
		}
		if(!conditionCheck){//如果不满足条件，那么返回正确
			return true;
		}
		//在条件正确的前提下
		Function func = Validator.getFunction(action.getFunctionName());
		if(func==null){
			return true;
		}
		Stack<Object> stacks = new Stack<Object>();
		String value;
		String[] otherParameters = action.getOtherParameters();
		if(otherParameters!=null&&otherParameters.length>0){
			for(int i=otherParameters.length-1; i>=0; i--){
				value = getValue(otherParameters[i],transaction,parameterValue);
				if(value==null){
					return true;
				}
				stacks.push(value);
			}
		}
		stacks.push(cValue);
		return func.run(stacks);
	}
	
	private String getValue(String parameter, Transaction transaction, Map<String,Object> parameterValue){
		if(parameter.charAt(0)=='$'){//表示从Transaction中获取
			parameter = parameter.substring(1);
			return transaction.getString(parameter);
		}else if(parameter.charAt(0)=='#'){
			parameter = parameter.substring(1);
			Object obj = parameterValue.get(parameter);
			if(obj==null){
				return null;
			}
			return String.valueOf(obj);
		}else{
			return parameter;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getConstraintName() {
		return constraintName;
	}

	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}

	public RuleCondition getCondition() {
		return condition;
	}

	public void setCondition(RuleCondition condition) {
		this.condition = condition;
	}

	public RuleAction getAction() {
		return action;
	}

	public void setAction(RuleAction action) {
		this.action = action;
	}
	
}
