package com.intellicredit.platform.service.rule.forward2;

import java.util.*;
import com.intellicredit.platform.component.expression.*;

public class RuleUtil {
	// user.function.properties中定义的Function加入到Expression
	public static void addUserFunction(Expression jep) {//TODO，修改修改
		try {
			Properties props = new Properties();
			props.load(InferenceEngine.class.getResourceAsStream("/user.function.properties"));
			Iterator<?> iter = props.keySet().iterator();
			while (iter.hasNext()) {
				String key = String.valueOf(iter.next());
				String value = String.valueOf(props.get(key));
				jep.addFunction(key, value);
			}
		} catch (Exception e) {
			return;
		}
	}
	
	/**
	 * 得到最远的一天
	 * @return
	 */
	public static Date getMinDate(){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, 1970);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
}
