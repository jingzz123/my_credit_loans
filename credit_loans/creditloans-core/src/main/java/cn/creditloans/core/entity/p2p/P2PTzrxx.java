package cn.creditloans.core.entity.p2p;

import java.util.Date;

public class P2PTzrxx {
	
	private int id;
	
	private String tzrxm;
	
	private String tzrzjlx;
	
	private String tzrzjhm;
	
	private String tzrtzje;
	
	private int dkjbxxId;
	
	private int status;
	
	private int userId;
	
	private String orgCode;
	
	private Date createTime;
	
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTzrxm() {
		return tzrxm;
	}

	public void setTzrxm(String tzrxm) {
		this.tzrxm = tzrxm;
	}

	public String getTzrzjlx() {
		return tzrzjlx;
	}

	public void setTzrzjlx(String tzrzjlx) {
		this.tzrzjlx = tzrzjlx;
	}

	public String getTzrzjhm() {
		return tzrzjhm;
	}

	public void setTzrzjhm(String tzrzjhm) {
		this.tzrzjhm = tzrzjhm;
	}

	public String getTzrtzje() {
		return tzrtzje;
	}

	public void setTzrtzje(String tzrtzje) {
		this.tzrtzje = tzrtzje;
	}

	public int getDkjbxxId() {
		return dkjbxxId;
	}

	public void setDkjbxxId(int dkjbxxId) {
		this.dkjbxxId = dkjbxxId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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
