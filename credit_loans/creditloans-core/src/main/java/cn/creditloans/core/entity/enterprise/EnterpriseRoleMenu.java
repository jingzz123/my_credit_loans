package cn.creditloans.core.entity.enterprise;

import java.util.Date;

/**
 * 企业角色菜单中间信息实体
 * @author Austin
 *
 */
public class EnterpriseRoleMenu {
	
	private int id;
	
	/**
	 * 角色id
	 */
	private int roleId;
	
	/**
	 * 菜单id
	 */
	private int menuId;
	
	private Date createTime;
	
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
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
