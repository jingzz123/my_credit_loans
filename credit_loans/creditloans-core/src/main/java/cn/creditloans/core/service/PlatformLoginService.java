package cn.creditloans.core.service;

import cn.creditloans.core.dto.platform.PlatformUserDTO;

public interface PlatformLoginService {
	
	/**
	 * 平台用户登录Service
	 * @param platformUserDTO
	 * @return
	 * @throws RuntimeException
	 */
	PlatformUserDTO login(PlatformUserDTO platformUserDto) throws RuntimeException;
}
