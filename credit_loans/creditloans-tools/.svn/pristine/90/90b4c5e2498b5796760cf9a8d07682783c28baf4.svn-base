package cn.creditloans.tools.validator.check.function;

import java.util.Stack;

import cn.creditloans.tools.util.UtilMethod;

/**
 * 对家庭电话号码进行验证
 * 
 * @author Ash
 * 
 */
public class CheckTelephone extends Function {

	public CheckTelephone() {
		super.numOfParameter = 1;
	}

	public boolean run(Stack<Object> stack) {
		if (stack == null || stack.size() != 1) {
			return false;
		}
		Object param = stack.pop();
		if (param.getClass().isArray()) {
			return false;
		}
		return check(String.valueOf(param));
	}

	private boolean check(String str) {
		if(!UtilMethod.isMobile(str)&&
				!UtilMethod.isPhone(str)){
			return false;
		}
		return true;
	}

}
