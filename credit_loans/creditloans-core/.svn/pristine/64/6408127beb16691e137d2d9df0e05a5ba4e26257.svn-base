package cn.creditloans.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.platform.PlatformUser;
import cn.creditloans.core.entity.platform.PlatformUserRole;
import cn.creditloans.tools.page.PageModel;

public interface PlatformUserDao {

	/**
	 * 根据id级联查询user 和role
	 * @param id
	 * @return
	 */
	PlatformUser selectCascadePlatformUserByUserId(int userId);
	
	/**
	 * 根据token查询user
	 * @param token
	 * @return
	 */
	PlatformUser selectPlatformUserByToken(String token);
	
	/**
	 * 根据邮箱查询user
	 * @param email
	 * @return
	 */
	PlatformUser selectPlatformUserByEmail(String email);
	
	/**
	 * 根据角色id查询，返回该角色对应用户个数
	 * @param roleId
	 * @return
	 */
	int selectPlatformUserRoleCountByRoleId(int roleId);
	
	/**
	 * 根据查询对象，获取平台用户个数
	 * @param platformUser
	 * @return
	 */
	int selectPlatformUserPageCount(PlatformUser platformUser);
	
	/**
	 * 根据查询对象和分页条件，查询平台用户list
	 * @param pm
	 * @param platformUser
	 * @return
	 */
	List<PlatformUser> selectPlatformUserPageList(@Param("pm") PageModel<?> pm, @Param("user") PlatformUser platformUser);
	
	/**
	 * 添加用户
	 * @param platformUser
	 */
	void insertPlatformUser(PlatformUser platformUser);
	
	/**
	 * 批量插入PlatformUserRole
	 * @param platformUserRoleList
	 */
	void batchInsertPlatformUserRole(List<PlatformUserRole> platformUserRoleList);
	
	/**
	 * 更新用户
	 * @param platformUser
	 */
	void updatePlatformUser(PlatformUser platformUser);
	
	/**
	 * 更新用户密码
	 * @param id
	 * @param password
	 */
	void updatePlatformUserPassword(@Param("id") int userId, @Param("password") String password);
	
	/**
	 * 更新用户token
	 * @param platformUser
	 */
	void updatePlatformUserToken(PlatformUser platformUser);
	
	/**
	 * 更新用户有效性，（删除不做真实删除）
	 * @param platformUser
	 */
	void updatePlatformUserStatus(PlatformUser platformUser);
	
	/**
	 * 根据用户id删除user
	 * @param id
	 */
	void deletePlatformUserByUserId(int userId);
	
	/**
	 * 根据用户id删除user_role中间信息
	 * @param userId
	 */
	void deletePlatformUserRoleByUserId(int userId);
	
}
