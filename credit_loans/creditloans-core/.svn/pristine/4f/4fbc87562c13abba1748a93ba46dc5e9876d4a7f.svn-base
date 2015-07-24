package cn.creditloans.core.service.impl.platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.cache.redis.PlatformUserCache;
import cn.creditloans.core.dao.PlatformDepartmentDao;
import cn.creditloans.core.dao.PlatformRoleDao;
import cn.creditloans.core.dao.PlatformUserDao;
import cn.creditloans.core.dto.platform.PlatformMenuDTO;
import cn.creditloans.core.dto.platform.PlatformRoleDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.core.entity.platform.PlatformRole;
import cn.creditloans.core.entity.platform.PlatformUser;
import cn.creditloans.core.entity.platform.PlatformUserRole;
import cn.creditloans.core.service.PlatformAdminService;
import cn.creditloans.core.service.PlatformUserService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.encrypt.MD5Util;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Service("platformUserService")
public class PlatformUserServiceImpl implements PlatformUserService{
	private static final Log logger = LogFactory.getLog(PlatformUserServiceImpl.class);
	
	@Autowired
	PlatformUserDao platformUserDao;
	
	@Autowired
	PlatformAdminService platformAdminService;
	
	@Autowired
	PlatformDepartmentDao platformDepartmentDao;
	
	@Autowired
	PlatformRoleDao platformRoleDao;

	@Override
	public PageModel<PlatformUserDTO> getPlatformUserPageList(
			PlatformUserDTO platformUserDto, int currentPage, int pageSize) {
		// 转换查询参数
		PlatformUser platformUser = new PlatformUser();
		BeanUtils.copyProperties(platformUserDto, platformUser);

		PageModel<PlatformUserDTO> pm = new PageModel<PlatformUserDTO>();
		pm.init(currentPage, pageSize);

		Integer count = platformUserDao
				.selectPlatformUserPageCount(platformUser);
		List<PlatformUser> platformUserList = platformUserDao
				.selectPlatformUserPageList(pm, platformUser);

		// 转换
		List<PlatformUserDTO> platformUserDtoList = new ArrayList<PlatformUserDTO>();
		//List<PlatformDepartment> platformDepartmentList = platformDepartmentDao.selectPlatformDepartmentList();
		for (PlatformUser _platformUser : platformUserList) {
			PlatformUserDTO _platformUserDto = new PlatformUserDTO();
			BeanUtils.copyProperties(_platformUser, _platformUserDto);
			/*for (PlatformDepartment platformDepartment : platformDepartmentList) {
				if (platformDepartment.getId() == _platformUserDto.getDepId()) {
					_platformUserDto.setDepName(platformDepartment.getName());
				}
			}*/
			platformUserDtoList.add(_platformUserDto);
		}
		pm.setDatas(platformUserDtoList);
		pm.setTotal(count);
		return pm;
	}

	@Override
	public PlatformUserDTO getPlatformUserByEmial(String email) {
		PlatformUser platformUser = platformUserDao.selectPlatformUserByEmail(email);
		PlatformUserDTO platformUserDto = null;
		if(platformUser != null) {
			platformUserDto = new PlatformUserDTO();
			BeanUtils.copyProperties(platformUser, platformUserDto);
			
		}
		return platformUserDto;
	}

	@Override
	public int savePlatformUser(PlatformUserDTO platformUserDto) {
		PlatformUser platformUser = new PlatformUser();
		BeanUtils.copyProperties(platformUserDto, platformUser);
		
		// 密码加密
		String encryptPassword = MD5Util.md5(platformUserDto.getPassword());
		platformUser.setPassword(encryptPassword);
		
		//保存用户
		platformUserDao.insertPlatformUser(platformUser);
		int platformUserId =platformUser.getId();
		
		// 处理该用户的角色
		List<Integer> roleIdList = platformUserDto.getRoleIdList();
		List<PlatformUserRole> platformUserRoleList = new ArrayList<PlatformUserRole>();
		for(Integer roleId : roleIdList){
			PlatformUserRole platformUserRole = new PlatformUserRole();
			platformUserRole.setUserId(platformUserId);
			platformUserRole.setRoleId(roleId);
			platformUserRoleList.add(platformUserRole);
		}
		//保存用户角色中间表
		platformUserDao.batchInsertPlatformUserRole(platformUserRoleList);
		
		return platformUserId;
	}

	@Override
	public PlatformUserDTO getCascadePlatformUserByUserId(int userId) {
		PlatformUser platformUser = platformUserDao.selectCascadePlatformUserByUserId(userId);
		PlatformUserDTO platformUserDto = new PlatformUserDTO();
		BeanUtils.copyProperties(platformUser, platformUserDto);
		
		
		List<PlatformRole> platformRoleList =  platformRoleDao.selectPlatformRoleList();
		List<PlatformRoleDTO> platformRoleDtoList = new ArrayList<PlatformRoleDTO>();
		for(PlatformRole platformRole : platformRoleList){
			PlatformRoleDTO platformRoleDto = new PlatformRoleDTO();
			BeanUtils.copyProperties(platformRole, platformRoleDto);
			platformRoleDtoList.add(platformRoleDto);
		}
		
		//platformRoleDtoList 转换成map
		Map<Integer, PlatformRoleDTO> platformRoleDtoMap = new HashMap<Integer, PlatformRoleDTO>();
		for(PlatformRoleDTO  platformRoleDto : platformRoleDtoList){
			platformRoleDtoMap.put(platformRoleDto.getId(), platformRoleDto);
		}
		
		//处理该用户拥有的角色
		List<Integer> roleIdList = new ArrayList<Integer>();
		List<PlatformUserRole> platformUserRoleList = platformUser.getPlatformUserRoleList();
		for(PlatformUserRole platformUserRole : platformUserRoleList){
			roleIdList.add(platformUserRole.getRoleId());
			PlatformRoleDTO _platformRoleDto = platformRoleDtoMap.get(platformUserRole.getRoleId());
			if(_platformRoleDto != null){
				_platformRoleDto.setOwnerFlag(1);
			}
		}
		
		platformUserDto.setPlatformRoleDtoList(platformRoleDtoList);
		
		return platformUserDto;
	}

	@Override
	public void updatePlatformUser(PlatformUserDTO platformUserDto) {
		PlatformUser platformUser = new PlatformUser();
		BeanUtils.copyProperties(platformUserDto, platformUser);
		
		String password = platformUserDto.getPassword().trim();
		String resetPwd = CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORMUSER__RESET_PASSWORD);
		
		if(resetPwd.equals(password)){
			platformUser.setPassword(MD5Util.md5(password));
		}
		
		int platformUserId = platformUser.getId();
		// 处理该用户的角色
		List<Integer> roleIdList = platformUserDto.getRoleIdList();
		List<PlatformUserRole> platformUserRoleList = new ArrayList<PlatformUserRole>();
		for(Integer roleId : roleIdList){
			PlatformUserRole platformUserRole = new PlatformUserRole();
			platformUserRole.setUserId(platformUserId);
			platformUserRole.setRoleId(roleId);
			platformUserRoleList.add(platformUserRole);
		}
		
		platformUserDao.updatePlatformUser(platformUser);
		platformUserDao.deletePlatformUserRoleByUserId(platformUserId);
		platformUserDao.batchInsertPlatformUserRole(platformUserRoleList);
	}

	@Override
	public void deletePlatformUser(int userId) {
		PlatformUser platformUser = platformUserDao.selectCascadePlatformUserByUserId(userId);
		if(platformUser != null){
			//不做真实删除，而是改为禁用
			platformUser.setStatus(1);
		}
		platformUserDao.updatePlatformUser(platformUser);
	}
	
	@Override
	public PlatformUserDTO getPlatformUserDtoFromCache(String token) {
		if (logger.isInfoEnabled()) {
			logger.info("will get platformUserDto from cache with token : " + token);
		}

		// 根据token从缓存读取
		PlatformUserDTO platformUserDto = (PlatformUserDTO) PlatformUserCache.getInstance().get(token);
		
		//判断用户操作是否失效
		/*long now_time = System.currentTimeMillis();
		Long last_login_time = PlatformUserTimeOutCache.getInstance().get(token);
		if(last_login_time==null){
			logger.info("now_time : "+now_time+"token : "+ token);
		}
		// 获取配置文件中的登录超时时间
		long time_out = Long.parseLong(ElectricPropertyPlaceholderConfigurer.getContextProperty(Constants.PLATFORMUSER_TIMEOUT));
		long diff_time = (now_time - last_login_time)/1000;
		
		if(diff_time > time_out){
			deleteTempDatas(token);
			throw new RuntimeException("登录超时Session失效了， is not exsit!");
		}else{
			// FIXME : 不用delete，直接save吧；最好把这个时间超时验证放到拦截器里面，而不是在 getPlatformUserDtoFromCache 这个方法里校验用户超时
			//PlatformUserTimeOutCache.getInstance().delete(token);
			PlatformUserTimeOutCache.getInstance().save(token, System.currentTimeMillis());
		}*/
		
		if (platformUserDto == null) {
			if (logger.isInfoEnabled()) {
				logger.info("notice! will get platformUserDto from db with token : "
						+ token);
			}

			PlatformUser platformUser = platformUserDao.selectPlatformUserByToken(token);
			if (platformUser == null) {
				throw new RuntimeException("the platformUser with token : " + token
						+ " is not exsit!");
			} else {
				// 转outer user dto
				platformUserDto = new PlatformUserDTO();
				BeanUtils.copyProperties(platformUser, platformUserDto);

				// 获取该用户权限
				List<PlatformMenuDTO> platformMenuDtoList = platformAdminService.obtainPlatformUserMenuByUserId(platformUserDto.getId());

				//
				platformUserDto.setOwnerPlatformMenuDtoList(platformMenuDtoList);
				PlatformUserCache.getInstance().set(token, platformUserDto); // 保存到缓存

				return platformUserDto;
			}
		} else {
			return platformUserDto;
		}
	}

	@Override
	public void deleteTempDatas(String token) {
		PlatformUserCache.getInstance().delete(token);
	}
}
