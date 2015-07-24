package cn.creditloans.tools.validator.check.rule;

import cn.creditloans.tools.validator.check.compare.Operator;

public class RuleCondition {
	//$开头从transaction中获取，#开头从Map中获取值，否则不问
	private String lhs;
	
	private Operator opertor;
	
	//$开头从transaction中获取，#开头从Map中获取值，否则不问
	private String rhs;
	
	private String valueType;//两边值的类型

	public String getLhs() {
		return lhs;
	}

	public void setLhs(String lhs) {
		this.lhs = lhs;
	}

	public Operator getOpertor() {
		return opertor;
	}

	public void setOpertor(Operator opertor) {
		this.opertor = opertor;
	}

	public String getRhs() {
		return rhs;
	}

	public void setRhs(String rhs) {
		this.rhs = rhs;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
}
