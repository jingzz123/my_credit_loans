package com.intellicredit.platform.component.validator2.rule;

public class RuleAction {
	private String functionName;//执行函数名称
	
	private String[] otherParameters = null;//其它参数

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String[] getOtherParameters() {
		return otherParameters;
	}

	public void setOtherParameters(String[] otherParameters) {
		this.otherParameters = otherParameters;
	}
	
}
