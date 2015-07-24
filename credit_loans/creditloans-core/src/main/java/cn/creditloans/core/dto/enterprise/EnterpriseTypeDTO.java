package cn.creditloans.core.dto.enterprise;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 企业类型DTO
 * @author Austin
 *
 */
public class EnterpriseTypeDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private Date createTime;
	
	private Date updateTime;

	//临时字段用于编辑时接收前端id
	@JsonIgnore
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
	}	
}
