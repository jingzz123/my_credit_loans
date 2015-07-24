package cn.creditloans.tools.fuzzy.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 地址模糊查询缓存对象
 * @author Administrator
 *
 */
public class FuzzyMemoryAddressData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 这里存放所有无法分区的地址信息
	 * key1：表示地址名称
	 * value：所有的【业务代码 +'|'+id】的列表
	 */
	private Map<String,List<String>> otherValueMap;
	
	/**
	 * key1是索引目录
	 * key2是地址
	 * value：所有的【业务代码 +'|'+id】的列表
	 */
	private Map<String,Map<String,List<String>>> partitionValueMap;

	public Map<String, List<String>> getOtherValueMap() {
		return otherValueMap;
	}

	public void setOtherValueMap(Map<String, List<String>> otherValueMap) {
		this.otherValueMap = otherValueMap;
	}

	public Map<String, Map<String, List<String>>> getPartitionValueMap() {
		return partitionValueMap;
	}

	public void setPartitionValueMap(
			Map<String, Map<String, List<String>>> partitionValueMap) {
		this.partitionValueMap = partitionValueMap;
	}

}
