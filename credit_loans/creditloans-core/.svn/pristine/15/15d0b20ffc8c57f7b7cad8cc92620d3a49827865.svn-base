package cn.creditloans.core.service;

import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.tools.page.PageModel;

public interface PlatformUserService {
	
	/**
	 * 分页展示用户信息
	 * @param platformUserDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<PlatformUserDTO> getPlatformUserPageList(PlatformUserDTO platformUserDto, int currentPage, int pageSize);
	
	/**
	 * 根据email返回用户信息
	 * @param email
	 * @return
	 */
	PlatformUserDTO getPlatformUserByEmial(String email);
	
	/**
	 * 保存用户
	 * @param platformUserDto
	 * @return
	 */
	int savePlatformUser(PlatformUserDTO platformUserDto);
	
	/**
	 * 根据userId返回用户信息
	 * @param userId, type
	 * @return
	 */
	PlatformUserDTO getCascadePlatformUserByUserId(int userId);
	
	/**
	 * 修改用户信息
	 * @param platformUserDto
	 */
	void updatePlatformUser(PlatformUserDTO platformUserDto);
	
	/**
	 * 根据userId删除用户
	 * @param platformUserId
	 */
	void deletePlatformUser(int userId);
	
	/**
	 * 根据token返回用户信息
	 * @param token
	 * @return
	 */
	PlatformUserDTO getPlatformUserDtoFromCache(String token);
	
	/**
	 * 在用户退出时，从redis中删除可能缓存的数据
	 * 
	 * @param token
	 */
	void deleteTempDatas(String token);
}
