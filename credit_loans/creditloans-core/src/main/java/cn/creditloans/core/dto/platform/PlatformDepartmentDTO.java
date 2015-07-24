package cn.creditloans.core.dto.platform;

import java.io.Serializable;
import java.util.Date;

/**
 * 平台部门信息DTO
 * @author Austin
 *
 */
public class PlatformDepartmentDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String description;
	
	/**
	 * 部门编号，唯一不可重复
	 */
	private int code;
	
	/**
	 * 上级部门id
	 */
	private int fid;
	
	private int sequence;
	
	private Date createTime;
	
	private Date updateTime;

	//上级部门名称
	private String parentName;
	
	private String strId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getStrId() {
		return strId;
	}

	public void setStrId(String strId) {
		if(strId != null && "" != strId){
			id = Integer.parseInt(strId);
		}
		this.strId = strId;
	}
}
