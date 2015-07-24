package cn.creditloans.core.dto.af;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * af_base_variable_simple
 * 
 * @author Ash
 * 
 */
public class BaseVariableSimpleDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2924638173414706774L;
	private int id;
	private String name; // 名称
	private String description; // 描述
	private int type; // 变量类型
	private String initValue; // 初始值
	private int createUserId; // 创建用户
	private Date createTime; // 创建时间
	private int upadteUserId; // 最后修改用户
	private Date updateTime; // 最后修改时间
	private List<Integer> baseIdList;
	private List<BaseElementDTO> baseElementDTOList;
	private String oldName;
	
	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public List<Integer> getBaseIdList() {
		return baseIdList;
	}

	public void setBaseIdList(List<Integer> baseIdList) {
		this.baseIdList = baseIdList;
	}
	
	
	public List<BaseElementDTO> getBaseElementDTOList() {
		return baseElementDTOList;
	}

	public void setBaseElementDTOList(List<BaseElementDTO> baseElementDTOList) {
		this.baseElementDTOList = baseElementDTOList;
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

	public String getInitValue() {
		return initValue;
	}

	public void setInitValue(String initValue) {
		this.initValue = initValue;
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
