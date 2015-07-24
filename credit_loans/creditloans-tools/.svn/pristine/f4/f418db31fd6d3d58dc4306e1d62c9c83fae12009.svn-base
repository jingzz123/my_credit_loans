package com.intellicredit.platform.component.validator2.compare;

import com.intellicredit.platform.component.situation2.Operator;

public class Compare {
	private Operator operator;
	
	/**
	 * 参数如果
	 * $：表示从transaction获取参数
	 * #：表示从传入的map中获取参数
	 * 没有：表示传入的是值
	 * 这个和RuleCondition保持一致
	 */
	private String parameter;
	
	private String errorMsg;//錯誤信息
	
	public Compare(Operator operator, String parameter, String errorMsg){
		this.operator = operator;
		this.parameter = parameter;
		this.errorMsg = errorMsg;
	}

	public Operator getOperator() {
		return operator;
	}

	public String getParameter() {
		return parameter;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}
	
}
