package cn.creditloans.tools.cache.datas;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.creditloans.tools.validator.transaction.Transaction;

/**
 * 存放所有的处理的正确数据
 * 4. 正确的数据
 * @author Ash
 *
 */
public class MemoryCorrectDatas implements Serializable{
	
	private static final long serialVersionUID = 2323066523041951964L;

	private String businessType = null;//业务类型
	/** 上传文件名称 */
	private String fileName;
	
	/**
	 * 正确的数据
	 */
	private Map<String,Transaction> corrects = null;
	
	public void init(){
		if(corrects==null){
			corrects = new HashMap<String,Transaction>();
		}else{
			corrects.clear();
		}
	}
	
	public void cleanup(){
		corrects = null;
	}
	
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 加入正确数据
	 */
	public void addCorrect(Transaction rec){
		corrects.put(rec.getPkCombine(),rec);
	}
	
	/**
	 * 从正确中将删除记录
	 * @param key
	 */
	public Transaction removeCorrect(String key){
		return corrects.remove(key);
	}
	
	public Transaction getCorrect(String key){
		return corrects.get(key);
	}

	public Map<String, Transaction> getCorrects() {
		return corrects;
	}
	
	public boolean hasDatas(){
		if(corrects.size()>0){
			return true;
		}
		return false;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
