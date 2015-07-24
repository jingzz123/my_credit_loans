package cn.creditloans.core.entity.enterprise;

import java.util.Date;

/**
 * 上传记录 实体类
 * 
 * @author Administrator
 * 
 */
public class UploadSummaries {
	/** 上传id */
	private int id;
	/** 上传日期 */
	private Date uploadedOn;
	/** 总条数 */
	private int recordCount;
	/** 正确条数 */
	private int validatedRecordCount;
	/** 部门id */
	private int depId;
	/** user id */
	private int userId;
	/** 上传文件名称 */
	private String fileName;

	private Date createTime;
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getValidatedRecordCount() {
		return validatedRecordCount;
	}

	public void setValidatedRecordCount(int validatedRecordCount) {
		this.validatedRecordCount = validatedRecordCount;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
