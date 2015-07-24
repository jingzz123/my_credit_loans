package cn.creditloans.core.service;

import cn.creditloans.core.dto.enterprise.EnterpriseUserDTO;
import cn.creditloans.tools.page.PageModel;

public interface EnterpriseUserService {

	/**
	 * 分页展示用户信息
	 * @param enterpriseUserDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<EnterpriseUserDTO> getEnterpriseUserPageList(EnterpriseUserDTO enterpriseUserDto, int currentPage, int pageSize);
	
	/**
	 * 根据email返回用户信息
	 * @param email
	 * @return
	 */
	EnterpriseUserDTO getEnterpriseUserByEmial(String email);
	
	/**
	 * 保存用户
	 * @param enterpriseUserDto
	 * @return
	 */
	int saveEnterpriseUser(EnterpriseUserDTO enterpriseUserDto);
	
	/**
	 * 根据userId返回用户信息 type=0表示为平台管理
	 * @param userId, type
	 * @return
	 */
	EnterpriseUserDTO getCascadeEnterpriseUserByUserId(int userId, int type);
	
	/**
	 * 修改用户信息
	 * @param enterpriseUserDto
	 */
	void updateEnterpriseUser(EnterpriseUserDTO enterpriseUserDto);
	
	/**
	 * 根据userId删除用户
	 * @param enterpriseUserId
	 */
	void deleteEnterpriseUser(int userId);
	
	/**
	 * 根据token返回用户信息
	 * @param token
	 * @return
	 */
	EnterpriseUserDTO getEnterpriseUserDtoFromCache(String token);
	
	/**
	 * 在用户退出时，从redis中删除可能缓存的数据
	 * 
	 * @param token
	 */
	void deleteTempDatas(String token);
	
	boolean enterpriseInterceptorCheck(String token);
	
	/**
	 * 根据用户账号，查询用户相关信息存入Cache中
	 * @param email
	 * @return
	 */
	EnterpriseUserDTO setEnterpriseUserInCache(String email);
}
