package cn.creditloans.core.service;

import cn.creditloans.core.dto.enterprise.EnterpriseUserDTO;

public interface EnterpriseLoginService {
	
	/**
	 * 平台用户登录Service
	 * @param enterpriseUserDTO
	 * @return
	 * @throws RuntimeException
	 */
	EnterpriseUserDTO login(EnterpriseUserDTO enterpriseUserDto) throws RuntimeException;
	
	int loginCheck(EnterpriseUserDTO enterpriseUserDto);
}
