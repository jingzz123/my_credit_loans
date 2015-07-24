package cn.creditloans.tools.cache.datas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.creditloans.tools.validator.transaction.Transaction;

/**
 * 存放所有的处理的错误数据，数据分为3种类型 1. 和内部数据PK相同的 2. 格式校验有错的 3. 和数据库校验有错的
 * 
 * @author Ash
 * 
 */
public class MemoryErrorDatas implements Serializable {

	private static final long serialVersionUID = 2323066523041951964L;

	private String businessType = null;// 业务类型

	/**
	 * pk相同的
	 */
	private List<Transaction> sameInPks = null;

	/**
	 * 和数据库有pk相同的，固定的查询就可以了
	 */
	private List<Transaction> sameDbPks = null;

	/**
	 * 规则校验没有通过的
	 */
	private List<Transaction> errorRules = null;
	
	private List<Integer> uploadBatchIdList = null;
	
	/**
     * 重复UUID是否覆盖还是算作错误数据处理
     */
    private int isCover;
    /** 上传文件名称 */
    private String fileName;
    
	public void init() {
		if (sameInPks == null) {
			sameInPks = new ArrayList<Transaction>();
		} else {
			sameInPks.clear();
		}
		if (sameDbPks == null) {
			sameDbPks = new ArrayList<Transaction>();
		} else {
			sameDbPks.clear();
		}
		if (errorRules == null) {
			errorRules = new ArrayList<Transaction>();
		} else {
			errorRules.clear();
		}
		if (uploadBatchIdList == null) {
			uploadBatchIdList = new ArrayList<Integer>();
		} else {
			uploadBatchIdList.clear();
		}
	}

	public void cleanup() {
		sameInPks = null;
		sameDbPks = null;
		errorRules = null;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 加入内部存在相同pk情况的
	 * 
	 * @param rec
	 */
	public void addSameInPk(Transaction rec) {
		sameInPks.add(rec);
	}

	/**
	 * 加入和数据库
	 */
	public void addSameDbPk(Transaction rec) {
		sameDbPks.add(rec);
	}

	public List<Transaction> getSameDbPks() {
		return sameDbPks;
	}

	/**
	 * 加入错误数据
	 */
	public void addErrorRule(Transaction rec) {
		errorRules.add(rec);
	}

	public List<Transaction> getSameInPks() {
		return sameInPks;
	}

	public List<Transaction> getErrorRules() {
		return errorRules;
	}

	/**
	 * 判断是否存在数据
	 * 
	 * @return
	 */
	public boolean hasDatas() {
		if (sameInPks.size() > 0 || 
				sameDbPks.size()>0 ||
				errorRules.size() > 0) {
			return true;
		}
		return false;
	}

	public int getTotalSize() {
		return sameInPks.size() + errorRules.size();
	}
	
	public int getAllSize(){
		return sameInPks.size()+sameDbPks.size()+errorRules.size();
	}

    public int getIsCover() {
        return isCover;
    }

    public void setIsCover(int isCover) {
        this.isCover = isCover;
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Integer> getUploadBatchIdList() {
		return uploadBatchIdList;
	}

	public void setUploadBatchIdList(List<Integer> uploadBatchIdList) {
		this.uploadBatchIdList = uploadBatchIdList;
	}

}
