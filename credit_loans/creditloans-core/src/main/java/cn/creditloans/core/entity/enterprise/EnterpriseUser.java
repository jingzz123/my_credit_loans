package cn.creditloans.core.entity.enterprise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 企业用户实体
 * @author Austin
 *
 */
public class EnterpriseUser {
	
	private int id;
	
	/**
	 * 企业id
	 */
	private int enterpriseId;

	/**
	 * 部门id
	 */
	private int depId;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private String tel;

	private String mobile;
	
	private String fax;
	
	/**
	 * 是否有效：0有效，1无效
	 */
	private int isinvalid;
	
	private String token;
	
	/**
	 * 企业用户类型，0平台管理，1企业管理
	 */
	private int type;
	
	private Date createTime;
	
	private Date updateTime;
	
	private List<EnterpriseUserRole> enterpriseUserRoleList = new ArrayList<EnterpriseUserRole>();

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

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public int getIsinvalid() {
		return isinvalid;
	}

	public void setIsinvalid(int isinvalid) {
		this.isinvalid = isinvalid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public List<EnterpriseUserRole> getEnterpriseUserRoleList() {
		return enterpriseUserRoleList;
	}

	public void setEnterpriseUserRoleList(
			List<EnterpriseUserRole> enterpriseUserRoleList) {
		this.enterpriseUserRoleList = enterpriseUserRoleList;
	}
	
}
