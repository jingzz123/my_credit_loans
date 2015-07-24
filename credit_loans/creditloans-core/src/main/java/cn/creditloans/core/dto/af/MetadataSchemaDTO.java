package cn.creditloans.core.dto.af;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.creditloans.core.entity.af.MetadataRelation;
import cn.creditloans.core.entity.af.MetadataSchema;

/**
 * af_meta_schema
 * 
 * @author Ash
 * 
 */
public class MetadataSchemaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2181058273266424653L;
	private int id;
	private String name; // 名称
	private String description; // 描述
	private int type; // Schema类型非为定长、不定长，为常量参数定义
	private String delimiter; // Schema为非定长时有用，暂时只定义为一位
	private String isNested; // Y表示是nest schema
	private int idLength; // 子SchemaID的长度，只有位固定长度时才需要定义
	private String isInner; // Y表示是内部创建
	private int departmentId; // 机构ID
	private String isUsed = "Y"; // Y表示是有用
	private int createUserId; // 创建用户
	private Date createTime; // 创建时间
	private int upadteUserId; // 最后修改用户
	private Date updateTime; // 最后修改时间

	private List<Integer> mainSchemaIdList;
	private String oldName;
	private int schemaFlag;
	private List<MetadataSchemaDTO> metadataSchemaDTOList;
	
	
	public List<MetadataSchemaDTO> getMetadataSchemaDTOList() {
		return metadataSchemaDTOList;
	}

	public void setMetadataSchemaDTOList(
			List<MetadataSchemaDTO> metadataSchemaDTOList) {
		this.metadataSchemaDTOList = metadataSchemaDTOList;
	}

	public int getSchemaFlag() {
		return schemaFlag;
	}

	public void setSchemaFlag(int schemaFlag) {
		this.schemaFlag = schemaFlag;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public List<Integer> getMainSchemaIdList() {
		return mainSchemaIdList;
	}

	public void setMainSchemaIdList(List<Integer> mainSchemaIdList) {
		this.mainSchemaIdList = mainSchemaIdList;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getIsNested() {
		return isNested;
	}

	public void setIsNested(String isNested) {
		this.isNested = isNested;
	}

	public int getIdLength() {
		return idLength;
	}

	public void setIdLength(int idLength) {
		this.idLength = idLength;
	}

	public String getIsInner() {
		return isInner;
	}

	public void setIsInner(String isInner) {
		this.isInner = isInner;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
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
