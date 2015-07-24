package cn.creditloans.tools.validatorEx.excel;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cn.creditloans.tools.cache.datas.MemoryCorrectDatas;
import cn.creditloans.tools.cache.datas.MemoryErrorDatas;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.validator.check.ValidateResult;
import cn.creditloans.tools.validator.check.Validator;
import cn.creditloans.tools.validator.transaction.Transaction;
import cn.creditloans.tools.validatorEx.util.DFConfig;
import cn.creditloans.tools.validatorEx.util.FlowInfo;


/**
 * 对数据进行格式校验
 * @author Hero.wu
 *
 */
public class ExcelValidator {
	/**
	 * 存放传递给Excel的参数
	 */
	private Map<String,Object> parameterMap = null;
	
	private DFConfig dfConfig =  null;
	
	private int[] m_indexes = null;

	private String[] m_names = null;

	private Validator m_validator = null;
	
	/**
	 * 在整个处理过程中传递正确数据
	 */
	private MemoryCorrectDatas memoryCorrectDatas = null;
	
	/**
	 * 处理整个过程中的错误数据
	 */
	private MemoryErrorDatas memoryErrorDatas = null;
	
	/**
	 * 这是外边传入的参数，最终传递给Validator
	 */
	private Map<String,Object> validatorMap = null;
	
	public ExcelValidator(){
		parameterMap = new LinkedHashMap<String,Object>();
	}
	
	public boolean init() {
		Object obj = this.getParameter(Constants.FLOW_NAME);
		String flowName = String.valueOf(obj);
		FlowInfo flowInfo  = dfConfig.getFlowInfo(flowName);
		m_validator = flowInfo.getValidator();
		this.m_names = flowInfo.getValidateNames();
		this.m_indexes = flowInfo.getIndexes();
		return true;
	}

	public boolean process() {
		Map<String,Transaction> trans = this.memoryCorrectDatas.getCorrects();
		if(trans==null||trans.size()==0){
			return true;
		}
		if(this.m_indexes==null||this.m_indexes.length==0){
			return true;
		}
		Iterator<String> iter = trans.keySet().iterator();
		//将有错数据的key统一放到errorKeys中，然后统一删除
		List<String> errorKeys = new LinkedList<String>();
		String key;
		Transaction tran;
		ValidateResult result = null;
		int count = this.m_indexes.length;
		String columnValue;
		int errorCode = 0;
		while(iter.hasNext()){
			key = iter.next();
			tran = trans.get(key);
			errorCode = 0;
			for(int i=0; i<count; i++){
				columnValue = tran.getString(this.m_indexes[i]);
				//对数据进行格式校验
				//System.out.println(this.validatorMap.size());
				result = this.m_validator.checkValidity(this.m_names[i], columnValue, tran, this.validatorMap);
				errorCode += result.getErrorCode();
				if(result.getErrorCode()!=0){
					tran.putError(m_names[i], result.getErrorMsg());
				}
			}
			if(errorCode>0){//如果有错误
				this.memoryErrorDatas.addErrorRule(tran);
				errorKeys.add(key);
			}
		}
		int size = errorKeys.size();
		for(int i=0; i<size; i++){
			this.memoryCorrectDatas.removeCorrect(errorKeys.get(i));
		}
		errorKeys = null;
		return true;
	}

	public void setParameter(String key,Object value){
		parameterMap.put(key, value);
	}
	
	public Object getParameter(String key){
		return parameterMap.get(key);
	}

	public DFConfig getDfConfig() {
		return dfConfig;
	}

	public void setDfConfig(DFConfig dfConfig) {
		this.dfConfig = dfConfig;
	}
	
	public Map<String, Object> getValidatorMap() {
		return validatorMap;
	}

	public void setValidatorMap(Map<String, Object> validatorMap) {
		this.validatorMap = validatorMap;
	}

	
	public MemoryCorrectDatas getMemoryCorrectDatas() {
		return memoryCorrectDatas;
	}

	public void setMemoryCorrectDatas(MemoryCorrectDatas memoryCorrectDatas) {
		this.memoryCorrectDatas = memoryCorrectDatas;
	}

	public MemoryErrorDatas getMemoryErrorDatas() {
		return memoryErrorDatas;
	}

	public void setMemoryErrorDatas(MemoryErrorDatas memoryErrorDatas) {
		this.memoryErrorDatas = memoryErrorDatas;
	}

}
