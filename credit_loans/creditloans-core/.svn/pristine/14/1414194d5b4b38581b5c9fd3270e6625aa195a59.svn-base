package cn.creditloans.core.service.impl.enterprise;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.cache.redis.EnterpriseStateCache;
import cn.creditloans.core.cache.redis.EnterpriseUserCache;
import cn.creditloans.core.cache.redis.EnterpriseUserTimeOutCache;
import cn.creditloans.core.dao.EnterpriseDao;
import cn.creditloans.core.dao.EnterpriseUserDao;
import cn.creditloans.core.dto.enterprise.EnterpriseDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseMenuDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseUserDTO;
import cn.creditloans.core.entity.enterprise.Enterprise;
import cn.creditloans.core.entity.enterprise.EnterpriseUser;
import cn.creditloans.core.service.EnterpriseAdminService;
import cn.creditloans.core.service.EnterpriseLoginService;
import cn.creditloans.tools.constants.EnterpriseLoginConstants;
import cn.creditloans.tools.encrypt.MD5Util;

@Service("enterpriseLoginService")
public class EnterpriseLoginServiceImpl implements EnterpriseLoginService {
	private static final Log logger = LogFactory.getLog(EnterpriseLoginServiceImpl.class);
	
	@Autowired
	EnterpriseUserDao enterpriseUserDao;
	
	@Autowired
	EnterpriseDao enterpriseDao;
	
	@Autowired
	EnterpriseAdminService enterpriseAdminService;
	
	@Override
	public EnterpriseUserDTO login(EnterpriseUserDTO enterpriseUserDto)
			throws RuntimeException {
		String emailAddress = enterpriseUserDto.getEmail();
		String password = enterpriseUserDto.getPassword();
		String md5_password = MD5Util.md5(password);
		// 校验enterpriseUser
		EnterpriseUser enterpriseUser = enterpriseUserDao
				.selectEnterpriseUserByEmail(emailAddress);
		if (enterpriseUser == null) {
			throw new RuntimeException("the user with email address : "
					+ emailAddress + " is not exist!");
		} else {
			String _password = enterpriseUser.getPassword();
			if (_password.equals(md5_password)) { // 校验通过
				// 旧token
				String oldToken = enterpriseUser.getToken();

				// 产生 zzc-token
				String newToken = MD5Util.md5(enterpriseUserDto.getEmail());
				enterpriseUser.setToken(newToken);

				// 更新 enterpriseUser 的token
				enterpriseUserDao.updateEnterpriseUserToken(enterpriseUser);

				// 在redis缓存中清除以前的(old)token
				if (oldToken != null) {
					if (logger.isInfoEnabled()) {
						logger.info("delete old token : ("
								+ oldToken
								+ ") from enterpriseUserCache and enterpriseUserTimeOutCache");
					}
					EnterpriseUserCache.getInstance().delete(oldToken);
					EnterpriseUserTimeOutCache.getInstance().delete(oldToken);
				}

				// 获取enterpriseUser的menu
				List<EnterpriseMenuDTO> enterpriseMenuDto = enterpriseAdminService
						.obtainEnterpriseUserMenuByUserId(enterpriseUser
								.getId());
				// 转enterpriseUser dto
				BeanUtils.copyProperties(enterpriseUser, enterpriseUserDto);

				// 查询用户所属部门的信息
				Enterprise enterprise = enterpriseDao
						.selectEnterpriseById(enterpriseUserDto
								.getEnterpriseId());
				// 转 enterprise dto
				EnterpriseDTO enterpriseDto = new EnterpriseDTO();
				BeanUtils.copyProperties(enterprise, enterpriseDto);

				// 赋值
				enterpriseUserDto.setEnterpriseDto(enterpriseDto);
				enterpriseUserDto
						.setOwnerEnterpriseMenuDtoList(enterpriseMenuDto);

				// 将 enterpriseUserDto 保存在redis中
				EnterpriseUserCache.getInstance().set(newToken,
						enterpriseUserDto);
				
				//保存登录时间至redis中
				//EnterpriseUserTimeOutCache.getInstance().save(newToken, System.currentTimeMillis());

				return enterpriseUserDto;
			} else {
				throw new RuntimeException("the user with email address : "
						+ emailAddress + ", its password not valid!");
			}
		}
	}

	@Override
	public int loginCheck(EnterpriseUserDTO enterpriseUserDto) {
		String emailAddress = enterpriseUserDto.getEmail();
		String password = enterpriseUserDto.getPassword();
		String md5_password = MD5Util.md5(password);
		//根据email查询用户
		EnterpriseUser enterpriseUser = enterpriseUserDao.selectEnterpriseUserByEmail(emailAddress);
		//判断该email账户是否存在
		if (enterpriseUser == null) {
			return EnterpriseLoginConstants.USER_NOTEXIT;
		}else{
			//判断密码是否正确
			String _password = enterpriseUser.getPassword();
			if (!_password.equals(md5_password)) {
				return EnterpriseLoginConstants.PASSWORD_ERROR;
			}else{
				//判断用户是否禁用 
				if(enterpriseUser.getIsinvalid() == 1){
					return EnterpriseLoginConstants.USER_INVALID;
				}else{
					//判断用户所属企业是否禁用
					Enterprise enterprise = enterpriseDao.selectEnterpriseById(enterpriseUser.getEnterpriseId());
					if(enterprise.getIsinvalid() == 1){
						return EnterpriseLoginConstants.ENTERPRISE_INVALID;
					}else{
						//判断用户所属企业是否存在上级企业
						if(enterprise.getFid() != 0){
							//判断上级企业是否禁用
							Enterprise enterprise_p = enterpriseDao.selectEnterpriseById(enterprise.getFid());
							if(enterprise_p.getIsinvalid() == 1){
								return EnterpriseLoginConstants.P_ENTERPRISE_INVALID;
							}else{
								EnterpriseStateCache.getInstance().set(enterprise_p.getCode(), enterprise_p.getIsinvalid());
								return EnterpriseLoginConstants.LOGIN_SUCCESS;
							}
						}else{
							EnterpriseStateCache.getInstance().set(enterprise.getCode(), enterprise.getIsinvalid());
							return EnterpriseLoginConstants.LOGIN_SUCCESS;
						}
					}
				}
			}
		}
	}
}
