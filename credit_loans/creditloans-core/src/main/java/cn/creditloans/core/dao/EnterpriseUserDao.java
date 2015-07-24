package cn.creditloans.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.enterprise.EnterpriseUser;
import cn.creditloans.core.entity.enterprise.EnterpriseUserRole;
import cn.creditloans.tools.page.PageModel;

public interface EnterpriseUserDao {
	
	/**
	 * 根据id级联查询user 和role
	 * @param id
	 * @return
	 */
	EnterpriseUser selectCascadeEnterpriseUserByUserId(int userId);
	
	/**
	 * 根据token查询user
	 * @param token
	 * @return
	 */
	EnterpriseUser selectEnterpriseUserByToken(String token);
	
	/**
	 * 根据邮箱查询user
	 * @param email
	 * @return
	 */
	EnterpriseUser selectEnterpriseUserByEmail(String email);
	
	/**
	 * 根据角色id查询，返回该角色对应用户个数
	 * @param roleId
	 * @return
	 */
	int selectEnterpriseUserRoleCountByRoleId(int roleId);
	
	/**
	 * 根据查询对象，获取企业用户个数
	 * @param enterpriseUser
	 * @return
	 */
	int selectEnterpriseUserPageCount(EnterpriseUser enterpriseUser);
	
	/**
	 * 根据查询对象和分页条件，查询企业用户list
	 * @param pm
	 * @param enterpriseUser
	 * @return
	 */
	List<EnterpriseUser> selectEnterpriseUserPageList(@Param("pm") PageModel<?> pm, @Param("user") EnterpriseUser enterpriseUser);
	
	/**
	 * 根据超级管理员角色id查询对应的超级管理员信息（超级管理员用户角色为一对一）
	 * @param roleid
	 * @return
	 */
	EnterpriseUser selectEnterpriseUserByRoleId(int roleId);
	
	/**
	 * 添加用户
	 * @param enterpriseUser
	 */
	void insertEnterpriseUser(EnterpriseUser enterpriseUser);
	
	/**
	 * 批量插入EnterpriseUserRole
	 * @param enterpriseUserRoleList
	 */
	void batchInsertEnterpriseUserRole(List<EnterpriseUserRole> enterpriseUserRoleList);
	
	/**
	 * 更新用户
	 * @param enterpriseUser
	 */
	void updateEnterpriseUser(EnterpriseUser enterpriseUser);
	
	/**
	 * 更新用户密码
	 * @param id
	 * @param password
	 */
	void updateEnterpriseUserPassword(@Param("id") int userId, @Param("password") String password);
	
	/**
	 * 更新用户token
	 * @param enterpriseUser
	 */
	void updateEnterpriseUserToken(EnterpriseUser enterpriseUser);
	
	/**
	 * 更新用户有效性，（删除不做真实删除）
	 * @param enterpriseUser
	 */
	void updateEnterpriseUserIsinvalid(EnterpriseUser enterpriseUser);
	
	/**
	 * 根据用户id删除user
	 * @param id
	 */
	void deleteEnterpriseUserByUserId(int userId);
	
	/**
	 * 根据用户id删除user_role中间信息
	 * @param userId
	 */
	void deleteEnterpriseUserRoleByUserId(int userId);
	
}
