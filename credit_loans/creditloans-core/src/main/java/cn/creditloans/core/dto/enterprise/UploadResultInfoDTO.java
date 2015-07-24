package cn.creditloans.core.dto.enterprise;

import java.io.Serializable;
import java.util.List;

/**
 * 上传返回结果 DTO
 * @author Administrator
 *
 */
public class UploadResultInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 是否处理成功
	 */
	private boolean isSuccess = true;
	
	/**
	 * 处理不成功的错误信息
	 */
	private String errorInfo = "";
	
	private String tipInformation;//提示信息
	
	private List<String> errorInformation;//错误数据列表html信息
	
	private int errorCount = 0;//错误数据数量
	
	private int correctCount = 0;//正确数据数量
	
	private int dbcorrectCount = 0; // 数据库中已有条数
	
	private String businessType;// 上传文件类型
	
	private String totalTime;// 耗时
	
	private String fileName;
	
	/** 总页数 */
	private int totalCount;
	/** 当前页码 */
	private int pageIndex;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getTipInformation() {
		return tipInformation;
	}

	public void setTipInformation(String tipInformation) {
		this.tipInformation = tipInformation;
	}

	public List<String> getErrorInformation() {
		return errorInformation;
	}

	public void setErrorInformation(List<String> errorInformation) {
		this.errorInformation = errorInformation;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public int getCorrectCount() {
		return correctCount;
	}

	public void setCorrectCount(int correctCount) {
		this.correctCount = correctCount;
	}

	public int getDbcorrectCount() {
		return dbcorrectCount;
	}

	public void setDbcorrectCount(int dbcorrectCount) {
		this.dbcorrectCount = dbcorrectCount;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
}
