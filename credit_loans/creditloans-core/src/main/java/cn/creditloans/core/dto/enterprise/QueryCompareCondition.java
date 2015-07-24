package cn.creditloans.core.dto.enterprise;

import java.io.Serializable;

/**
 * 查询比对 查询条件 DTO
 * @author Administrator
 *
 */
public class QueryCompareCondition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String idNumber;
	
	private String mobile;
	
	private String tell;
	
	private String address;
	
	private String workName;
	
	/**
	 * 1：所有
	 * 2：本部门
	 * 3：不含本部门
	 */
	private int depCondition;
	
	/**
	 * 0：否
	 * 1：地址模糊
	 * 2：单位名称模糊
	 * 3：地址和单位名称模糊
	 */
	private int isFuzzy;
	
	private String token;
	
	/**
	 * 企业编号
	 */
	private String depCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public int getIsFuzzy() {
		return isFuzzy;
	}

	public void setIsFuzzy(int isFuzzy) {
		this.isFuzzy = isFuzzy;
	}

	public int getDepCondition() {
		return depCondition;
	}

	public void setDepCondition(int depCondition) {
		this.depCondition = depCondition;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}
	
}
