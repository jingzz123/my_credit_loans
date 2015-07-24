package cn.creditloans.core.dto.platform;

import java.io.Serializable;
import java.util.Date;

/**
 * 平台用户角色中间信息DTO
 * @author Austin
 *
 */
public class PlatformUserRoleDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	/*
	 * 用户id
	 */
	private int userId;
	
	/**
	 * 角色id
	 */
	private int roleId;
	
	private Date createTime;
	
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
