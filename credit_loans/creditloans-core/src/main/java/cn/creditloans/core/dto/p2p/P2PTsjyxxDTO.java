package cn.creditloans.core.dto.p2p;

import java.io.Serializable;
import java.util.Date;

public class P2PTsjyxxDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6065537161438147372L;

	private int id;
	
	private String xm;
	
	private String zjlx;
	
	private String zjhm;
	
	private String ywh;
	
	private String tsjylx;
	
	private String fsrq;
	
	private String bgys;
	
	private String fsje;
	
	private String mxxx;
	
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

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getYwh() {
		return ywh;
	}

	public void setYwh(String ywh) {
		this.ywh = ywh;
	}

	public String getTsjylx() {
		return tsjylx;
	}

	public void setTsjylx(String tsjylx) {
		this.tsjylx = tsjylx;
	}

	public String getFsrq() {
		return fsrq;
	}

	public void setFsrq(String fsrq) {
		this.fsrq = fsrq;
	}

	public String getBgys() {
		return bgys;
	}

	public void setBgys(String bgys) {
		this.bgys = bgys;
	}

	public String getFsje() {
		return fsje;
	}

	public void setFsje(String fsje) {
		this.fsje = fsje;
	}

	public String getMxxx() {
		return mxxx;
	}

	public void setMxxx(String mxxx) {
		this.mxxx = mxxx;
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
