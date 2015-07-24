package cn.creditloans.core.entity.af;

import java.util.Date;
import java.util.List;

/**
 * base_element_variable_relation
 * 
 * @author Ash
 * 
 */
public class BaseVariableRelation {
	private int id;
	private int baseId;
	private int type; //变量类型，主要为了和前面的不同类型变量关联：simple,transaction,situation,expression
	private int variableId;// 变量id
	private int sequence; // 次序
	private int createUserId; // 创建用户
	private Date createTime; // 创建时间
	private int upadteUserId; // 最后修改用户
	private Date updateTime; // 最后修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBaseId() {
		return baseId;
	}

	public void setBaseId(int baseId) {
		this.baseId = baseId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getVariableId() {
		return variableId;
	}

	public void setVariableId(int variableId) {
		this.variableId = variableId;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
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
