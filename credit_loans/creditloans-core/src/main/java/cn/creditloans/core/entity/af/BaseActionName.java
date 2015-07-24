package cn.creditloans.core.entity.af;

import java.util.Date;
import java.util.List;

/**
 * af_base_action_name
 * 
 * @author Ash
 * 
 */
public class BaseActionName {
	private int id;
	private String name; // 名称
	private String description; // 描述
	private int createUserId; // 创建用户
	private Date createTime; // 创建时间
	private int upadteUserId; // 最后修改用户
	private Date updateTime; // 最后修改时间
	private List<BaseActionRelation> baseActionRelationList;
	
	public List<BaseActionRelation> getBaseActionRelationList() {
		return baseActionRelationList;
	}

	public void setBaseActionRelationList(
			List<BaseActionRelation> baseActionRelationList) {
		this.baseActionRelationList = baseActionRelationList;
	}

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

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getUpadteUserId() {
		return upadteUserId;
	}

	public void setUpadteUserId(int upadteUserId) {
		this.upadteUserId = upadteUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
