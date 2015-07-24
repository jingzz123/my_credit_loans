package cn.creditloans.core.dto.platform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 平台用户信息DTO
 * @author Austin
 *
 */
public class PlatformUserDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private String tel;

	private String phone;
	
	private String qq;
	
	private int status;
	
	/**
	 * 所属部门id
	 */
	private int depId;
	
	private String token;
	
	private Date createTime;
	
	private Date updateTime;
	
	// ****************** vo **********************
	/**
	 * 部门名称
	 */
	//private String depName;
	
	/**
	 * 该用户的所属部门信息
	 */
	//private PlatformDepartmentDTO platformDepartmentDto = new PlatformDepartmentDTO();
	
	/**
	 * 记录该用户对应的角色id
	 */
	private List<Integer> roleIdList = new ArrayList<Integer>();

	private List<PlatformRoleDTO> platformRoleDtoList = new ArrayList<PlatformRoleDTO>();
	
	/**
	 * 该用户拥有的菜单
	 */
	private List<PlatformMenuDTO> ownerPlatformMenuDtoList = new ArrayList<PlatformMenuDTO>();
	
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	/*public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public PlatformDepartmentDTO getPlatformDepartmentDto() {
		return platformDepartmentDto;
	}

	public void setPlatformDepartmentDto(PlatformDepartmentDTO platformDepartmentDto) {
		this.platformDepartmentDto = platformDepartmentDto;
	}*/

	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public List<PlatformRoleDTO> getPlatformRoleDtoList() {
		return platformRoleDtoList;
	}

	public void setPlatformRoleDtoList(List<PlatformRoleDTO> platformRoleDtoList) {
		this.platformRoleDtoList = platformRoleDtoList;
	}

	public List<PlatformMenuDTO> getOwnerPlatformMenuDtoList() {
		return ownerPlatformMenuDtoList;
	}

	public void setOwnerPlatformMenuDtoList(
			List<PlatformMenuDTO> ownerPlatformMenuDtoList) {
		this.ownerPlatformMenuDtoList = ownerPlatformMenuDtoList;
	}
}
