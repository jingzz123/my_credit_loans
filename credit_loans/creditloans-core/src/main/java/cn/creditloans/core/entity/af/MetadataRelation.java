package cn.creditloans.core.entity.af;

import java.util.Date;
import java.util.List;

/**
 * metadata_nest_relation
 * 
 * @author Ash
 * 
 */
public class MetadataRelation {
	private int id;
	private int mainSchemaId;
	private int childSchemaId;
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

	public int getMainSchemaId() {
		return mainSchemaId;
	}

	public void setMainSchemaId(int mainSchemaId) {
		this.mainSchemaId = mainSchemaId;
	}

	public int getChildSchemaId() {
		return childSchemaId;
	}

	public void setChildSchemaId(int childSchemaId) {
		this.childSchemaId = childSchemaId;
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
