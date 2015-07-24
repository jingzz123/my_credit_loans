package com.intellicredit.platform.component.validator2.function;

import java.util.Stack;

import com.intellicredit.platform.util.UtilMethod;

/**
 * 对中文名字进行校验
 * @author Ash
 *
 */
public class CheckChineseName extends Function{

	public CheckChineseName(){
		super.numOfParameter = 1;
	}
	
    public boolean run(Stack<Object> stack) {
        if(stack==null||stack.size()!=1){
        	return false;
        }
        Object param = stack.pop();
        if(param.getClass().isArray()){
        	return false;
        }
        return check(String.valueOf(param));
    }

    private boolean check(String name) {
        // 判断姓名中是否含有标点
        String newName = name.replaceAll("\\pP", "");
        if(!name.equals(newName) || name.length() != newName.length()) {
            return false;
        }
    	if(name.substring(0,1).equals(".")){
    		return false;
    	}
    	name = UtilMethod.replace(name, ".", "");
    	if(name.length()<2){
    		return false;
    	}
    	/*
    	if(!name.matches("[u4e00-u9fa5]")){
    		return false;
    	}*/
    	String pattern = "^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]){2,15}$";
    	if(!name.matches(pattern)){
    		return false;
    	}
    	return true;
    }
    
    public static boolean isChinese(char c){
    	int v = (int)c;
    	return (v >= 19968 && v <= 171941);
    }
    
}
