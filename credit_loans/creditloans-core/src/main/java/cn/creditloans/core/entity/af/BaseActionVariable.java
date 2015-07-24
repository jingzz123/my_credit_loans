package cn.creditloans.core.entity.af;

import java.util.Date;
import java.util.List;

/**
 * af_base_action_variable
 * 
 * @author Ash
 * 
 */
public class BaseActionVariable {
	private int id;
	private String name; // 名称
	private String description; // 描述
	private int variableId; // 变量id
	private String content; // 表达式
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

	public int getVariableId() {
		return variableId;
	}

	public void setVariableId(int variableId) {
		this.variableId = variableId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
