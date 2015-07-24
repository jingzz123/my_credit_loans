package com.intellicredit.platform.component.validator2.function;

import java.util.Map;
import java.util.Stack;

import cn.creditloans.tools.context.AppContext;
import cn.creditloans.tools.parameters.ParameterConfig;

public abstract class Function {
	
	protected int numOfParameter = 0;//参数数量

	public abstract boolean run(Stack<Object> aStack);
	
	public int getNumOfParameter() {
		return numOfParameter;
	}
	
	protected boolean getConfirmedDetailsMap(String key) {
		Map<String, String> confirmedDetailsMap = AppContext.getInstance().getPrCongif().getParameterInfo(ParameterConfig.CONFIRMED_DETAILS).getKeyValueMap();
		if(confirmedDetailsMap.containsValue(key)) {
			return true;
		} else {
			return false;
		}
	}
	
}