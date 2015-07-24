package com.intellicredit.platform.service.rule.forward2;

import java.util.*;
import com.intellicredit.platform.component.situation2.*;
import com.intellicredit.platform.component.transaction.*;

/**
 * 数据库的内容来进行处理
 * 必须实现一个Service，这里就是将RuleSet的信息直接传入过来
 * @author Ash
 *
 */
public class InferenceDbEngine extends InferenceEngine{

    public InferenceDbEngine(String name) {
    	super(name);
    }

    public InferenceDbEngine(String name, Situation situation) {
        super(name,situation);
    }
    
	public void setSchema(Schema schema){
    	this.m_schema = schema;
    }
    
    //TODO==>传入，conditions，ruleList,actionList
    public void setVariables(RuleVariable[] mv){
    	m_variables = mv;
    	if(m_variables!=null&&m_variables.length>0){
    		int len = m_variables.length;
    		for(int i=0; i<len; i++){
    			m_variablesMap.put(m_variables[i].getName(),m_variables[i]);
    		}
    	}
    }
    
    public void setConditions(List<RuleCondition> conditions){
    	m_conditionList = conditions;
    	m_conditions = conditions.toArray(new RuleCondition[0]);
    }
    
    public void setRules(List<Rule> rules){
    	m_ruleList = rules;
    }
        
}
