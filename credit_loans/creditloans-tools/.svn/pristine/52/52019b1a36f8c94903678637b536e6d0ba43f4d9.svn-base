package cn.creditloans.tools.parameters;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParameterInfo {
	private String name;
	
	/**
	 * no-->name的对应关系
	 */
	private Map<String,String> keyValueMap = null;
	
	/**
	 * name-->no的对应关系
	 */
	private Map<String,String> valueKeyMap = null;
	
	public ParameterInfo(){
		keyValueMap = new LinkedHashMap<String,String>();
		valueKeyMap = new LinkedHashMap<String,String>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String key, String value){
		keyValueMap.put(key, value);
		valueKeyMap.put(value, key);
	}
	
	/**
	 * 根据key得到value
	 * @param key
	 * @return
	 */
	public String getValue(String key){
		return keyValueMap.get(key);
	}
	
	/**
	 * 根据value得到key
	 * @param value
	 * @return
	 */
	public String getKey(String value){
		return valueKeyMap.get(value);
	}
	
	public Map<String, String> getKeyValueMap() {
		return keyValueMap;
	}

	public void setKeyValueMap(Map<String, String> keyValueMap) {
		this.keyValueMap = keyValueMap;
	}
}
