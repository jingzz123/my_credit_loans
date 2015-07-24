package cn.creditloans.core.dto.platform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 平台角色信息DTO
 * @author Austin
 *
 */
public class PlatformRoleDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String description;
	
	private int status;
	
	private Date createTime;
	
	private Date updateTime;
	
	// ************************** vo ***********************
	/**
	 * 标志某个角色是否被用户拥有 
	 * 0: 没有拥有   1：已拥有
	 */
	private int ownerFlag;
		
	/**
	 * 企业名称
	 */
	private String platformName;
		
	private List<Integer> menuIdList = new ArrayList<Integer>();
	
	private List<PlatformMenuDTO> platformMenuDtoList = new ArrayList<PlatformMenuDTO>();
	
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getOwnerFlag() {
		return ownerFlag;
	}

	public void setOwnerFlag(int ownerFlag) {
		this.ownerFlag = ownerFlag;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public List<Integer> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Integer> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public List<PlatformMenuDTO> getPlatformMenuDtoList() {
		return platformMenuDtoList;
	}

	public void setPlatformMenuDtoList(List<PlatformMenuDTO> platformMenuDtoList) {
		this.platformMenuDtoList = platformMenuDtoList;
	}
}
