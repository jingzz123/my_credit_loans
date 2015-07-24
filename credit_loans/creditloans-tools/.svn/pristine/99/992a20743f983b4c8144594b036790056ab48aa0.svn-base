package cn.creditloans.tools.validator.check.function;

import java.util.Stack;

public class CheackConfirmedDetail extends Function{

	@Override
	public boolean run(Stack<Object> stack) {
		if(stack==null||stack.size()!=1){
        	return false;
        }
        Object param = stack.pop();
        if(param.getClass().isArray()){
        	return false;
        }
		return getConfirmedDetailsMap((String) param);
	}

}
