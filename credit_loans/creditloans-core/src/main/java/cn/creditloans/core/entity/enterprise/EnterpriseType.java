package cn.creditloans.core.entity.enterprise;

import java.util.Date;

/**
 * 企业类型实体
 * @author Austin
 *
 */
public class EnterpriseType {
	
	private int id;
	//企业类型信息
	private String name;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

	//临时字段用于编辑时接收前端id
	private String idString;
	
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

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
		if(idString != null && "" != idString){
			this.id = Integer.parseInt(idString);
		}
	}
}
