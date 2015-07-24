package cn.creditloans.core.service.impl.platform;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.cache.redis.PlatformUserCache;
import cn.creditloans.core.cache.redis.PlatformUserTimeOutCache;
import cn.creditloans.core.dao.PlatformDepartmentDao;
import cn.creditloans.core.dao.PlatformUserDao;
import cn.creditloans.core.dto.platform.PlatformDepartmentDTO;
import cn.creditloans.core.dto.platform.PlatformMenuDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.entity.platform.PlatformDepartment;
import cn.creditloans.core.entity.platform.PlatformUser;
import cn.creditloans.core.service.PlatformAdminService;
import cn.creditloans.core.service.PlatformLoginService;
import cn.creditloans.tools.encrypt.MD5Util;

@Service("platformLoginService")
public class PlatformLoginServiceImpl implements PlatformLoginService {
	private static final Log logger = LogFactory.getLog(PlatformLoginServiceImpl.class);
	
	@Autowired
	PlatformUserDao platformUserDao;
	
	@Autowired
	PlatformDepartmentDao platformDepartmentDao;
	
	@Autowired
	PlatformAdminService platformAdminService;
	
	@Override
	public PlatformUserDTO login(PlatformUserDTO platformUserDto)
			throws RuntimeException {
		String emailAddress = platformUserDto.getEmail();
		String password = platformUserDto.getPassword();
		String md5_password = MD5Util.md5(password);

		// 校验platformUser
		PlatformUser platformUser = platformUserDao.selectPlatformUserByEmail(emailAddress);
		if (platformUser == null) {
			throw new RuntimeException("the user with email address : " + emailAddress + " is not exist!");
		} else {
			String _password = platformUser.getPassword();
			if (_password.equals(md5_password)) { // 校验通过
				// 旧token
				String oldToken = platformUser.getToken();
				
				// 产生 zzc-token
				String newToken = MD5Util.md5(platformUserDto.getEmail());
				platformUser.setToken(newToken);
				
				// 更新 platformUser 的token
				platformUserDao.updatePlatformUserToken(platformUser);
				
				// 在redis缓存中清除以前的(old)token
				if (oldToken != null) {
					if (logger.isInfoEnabled()) {
						logger.info("delete old token : (" + oldToken + ") from platformUserCache and platformUserTimeOutCache");
					}
					PlatformUserCache.getInstance().delete(oldToken);
					//PlatformUserTimeOutCache.getInstance().delete(oldToken);
				}
				
				// 获取platformUser的menu
				List<PlatformMenuDTO> platformMenuDto = platformAdminService.obtainPlatformUserMenuByUserId(platformUser.getId());
				// 转platformUser dto
				BeanUtils.copyProperties(platformUser, platformUserDto);
				
				// 查询用户所属部门的信息
				//PlatformDepartment platformDepartment = platformDepartmentDao.selectPlatformDepartmentById(platformUserDto.getDepId());
				// 转 platformDepartment dto
				//PlatformDepartmentDTO platformDepartmentDto = new PlatformDepartmentDTO();
				//BeanUtils.copyProperties(platformDepartment, platformDepartmentDto);
				
				// 赋值
				//platformUserDto.setPlatformDepartmentDto(platformDepartmentDto);
				platformUserDto.setOwnerPlatformMenuDtoList(platformMenuDto);
				
				// 将 platformUserDto 保存在redis中
				PlatformUserCache.getInstance().set(newToken, platformUserDto);
				
				//保存登录时间至redis中
				//PlatformUserTimeOutCache.getInstance().save(newToken, System.currentTimeMillis());
								
				return platformUserDto;
			} else {
				throw new RuntimeException("the user with email address : " + emailAddress + ", its password not valid!");
			}
		}
	}

}
