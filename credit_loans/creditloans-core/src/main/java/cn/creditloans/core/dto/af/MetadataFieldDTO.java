package cn.creditloans.core.dto.af;

import java.io.Serializable;
import java.util.Date;

import cn.creditloans.core.entity.af.MetadataSchema;

/**
 * af_metadata_field，schema的数据项定义
 * 
 * @author Ash
 * 
 */
public class MetadataFieldDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8246613018122918307L;
	private int id;
	private int schemaId;
	private String name; // 名称
	private String description; // 描述
	private int type; // 字段类型
	private int createUserId; // 创建用户
	private Date createTime; // 创建时间
	private int upadteUserId; // 最后修改用户
	private Date updateTime; // 最后修改时间

	private MetadataSchemaDTO metadataSchemaDTO;
	
	public MetadataSchemaDTO getMetadataSchemaDTO() {
		return metadataSchemaDTO;
	}

	public void setMetadataSchemaDTO(MetadataSchemaDTO metadataSchemaDTO) {
		this.metadataSchemaDTO = metadataSchemaDTO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSchemaId() {
		return schemaId;
	}

	public void setSchemaId(int schemaId) {
		this.schemaId = schemaId;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
