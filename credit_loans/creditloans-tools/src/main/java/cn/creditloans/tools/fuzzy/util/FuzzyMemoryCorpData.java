package cn.creditloans.tools.fuzzy.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 单位名称模糊查询缓存对象
 * @author Administrator
 *
 */
public class FuzzyMemoryCorpData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 公司名称或者地址名称 */
	private List<String> nameList;
	
	private Map<String, List<String>> valueMap;

	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	public Map<String, List<String>> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, List<String>> valueMap) {
		this.valueMap = valueMap;
	}

}
