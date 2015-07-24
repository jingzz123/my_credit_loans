package cn.creditloans.core.dto.enterprise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 企业角色信息DTO
 * @author Austin
 *
 */
public class EnterpriseRoleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	/**
	 * 企业id
	 */
	private int enterpriseId;
	
	private String name;
	
	/**
	 * 角色类型，0平台管理，1企业管理
	 */
	private int type;
	
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
	private String enterpriseName;
	
	private List<Integer> menuIdList = new ArrayList<Integer>();
	
	private List<EnterpriseMenuDTO> enterpriseMenuDtoList = new ArrayList<EnterpriseMenuDTO>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public List<Integer> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Integer> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public List<EnterpriseMenuDTO> getEnterpriseMenuDtoList() {
		return enterpriseMenuDtoList;
	}

	public void setEnterpriseMenuDtoList(
			List<EnterpriseMenuDTO> enterpriseMenuDtoList) {
		this.enterpriseMenuDtoList = enterpriseMenuDtoList;
	}
	
}
