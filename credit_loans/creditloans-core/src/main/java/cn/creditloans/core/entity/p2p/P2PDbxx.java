package cn.creditloans.core.entity.p2p;

import java.util.Date;

public class P2PDbxx {
	
	private int id;
	
	private String dbrxm;
	
	private String dbrzjlx;
	
	private String dbrzjhm;
	
	private String dbje;
	
	private String dbzt;
	
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

	public String getDbrxm() {
		return dbrxm;
	}

	public void setDbrxm(String dbrxm) {
		this.dbrxm = dbrxm;
	}

	public String getDbrzjlx() {
		return dbrzjlx;
	}

	public void setDbrzjlx(String dbrzjlx) {
		this.dbrzjlx = dbrzjlx;
	}

	public String getDbrzjhm() {
		return dbrzjhm;
	}

	public void setDbrzjhm(String dbrzjhm) {
		this.dbrzjhm = dbrzjhm;
	}

	public String getDbje() {
		return dbje;
	}

	public void setDbje(String dbje) {
		this.dbje = dbje;
	}

	public String getDbzt() {
		return dbzt;
	}

	public void setDbzt(String dbzt) {
		this.dbzt = dbzt;
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