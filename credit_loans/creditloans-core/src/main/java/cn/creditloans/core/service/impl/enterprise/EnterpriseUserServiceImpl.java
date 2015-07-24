package cn.creditloans.core.service.impl.enterprise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.cache.redis.EnterpriseStateCache;
import cn.creditloans.core.cache.redis.EnterpriseUserCache;
import cn.creditloans.core.dao.EnterpriseDao;
import cn.creditloans.core.dao.EnterpriseRoleDao;
import cn.creditloans.core.dao.EnterpriseUserDao;
import cn.creditloans.core.dto.enterprise.EnterpriseDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseMenuDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseRoleDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseUserDTO;
import cn.creditloans.core.entity.enterprise.Enterprise;
import cn.creditloans.core.entity.enterprise.EnterpriseRole;
import cn.creditloans.core.entity.enterprise.EnterpriseUser;
import cn.creditloans.core.entity.enterprise.EnterpriseUserRole;
import cn.creditloans.core.service.EnterpriseAdminService;
import cn.creditloans.core.service.EnterpriseUserService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.encrypt.MD5Util;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Service("enterpriseUserService")
public class EnterpriseUserServiceImpl implements EnterpriseUserService {
	
	private final static Log logger = LogFactory.getLog(EnterpriseUserServiceImpl.class);
	
	@Autowired
	EnterpriseUserDao enterpriseUserDao;
	
	@Autowired
	EnterpriseDao enterpriseDao;
	
	@Autowired
	EnterpriseRoleDao enterpriseRoleDao;
	
	
	@Autowired
	EnterpriseAdminService enterpriseAdminService;
	
	@Override
	public PageModel<EnterpriseUserDTO> getEnterpriseUserPageList(
			EnterpriseUserDTO enterpriseUserDto, int currentPage, int pageSize) {
		// 转换查询参数
		EnterpriseUser enterpriseUser = new EnterpriseUser();
		BeanUtils.copyProperties(enterpriseUserDto, enterpriseUser);

		PageModel<EnterpriseUserDTO> pm = new PageModel<EnterpriseUserDTO>();
		pm.init(currentPage,pageSize);
		
		Integer count = enterpriseUserDao.selectEnterpriseUserPageCount(enterpriseUser);
		List<EnterpriseUser> enterpriseUserList = enterpriseUserDao.selectEnterpriseUserPageList(pm, enterpriseUser);
		
		// 转换
		List<EnterpriseUserDTO> enterpriseUserDtoList = new ArrayList<EnterpriseUserDTO>();
		List<Enterprise> enterpriseList = enterpriseDao.selectEnterpriseList();
		for (EnterpriseUser _enterpriseUser : enterpriseUserList) {
			EnterpriseUserDTO _enterpriseUserDto = new EnterpriseUserDTO();
			BeanUtils.copyProperties(_enterpriseUser, _enterpriseUserDto);
			for(Enterprise enterprise:enterpriseList){
				if(enterprise.getId()==_enterpriseUserDto.getEnterpriseId()){
					_enterpriseUserDto.setEnterpriseName(enterprise.getName());
				}		
			}
			enterpriseUserDtoList.add(_enterpriseUserDto);
		}
		pm.setDatas(enterpriseUserDtoList);
		pm.setTotal(count);
		return pm;
	}
	
	@Override
	public EnterpriseUserDTO getEnterpriseUserByEmial(String email) {
		EnterpriseUser enterpriseUser = enterpriseUserDao.selectEnterpriseUserByEmail(email);
		EnterpriseUserDTO enterpriseUserDto = null;
		if(enterpriseUser != null) {
			enterpriseUserDto = new EnterpriseUserDTO();
			BeanUtils.copyProperties(enterpriseUser, enterpriseUserDto);
		}
		return enterpriseUserDto;
	}

	@Override
	public int saveEnterpriseUser(EnterpriseUserDTO enterpriseUserDto) {
		EnterpriseUser enterpriseUser = new EnterpriseUser();
		BeanUtils.copyProperties(enterpriseUserDto, enterpriseUser);
		
		// 密码加密
		String encryptPassword = MD5Util.md5(enterpriseUserDto.getPassword());
		enterpriseUser.setPassword(encryptPassword);
		
		//保存用户
		enterpriseUserDao.insertEnterpriseUser(enterpriseUser);
		int enterpriseUserId =enterpriseUser.getId();
		
		// 处理该用户的角色
		List<Integer> roleIdList = enterpriseUserDto.getRoleIdList();
		List<EnterpriseUserRole> enterpriseUserRoleList = new ArrayList<EnterpriseUserRole>();
		for(Integer roleId : roleIdList){
			EnterpriseUserRole enterpriseUserRole = new EnterpriseUserRole();
			enterpriseUserRole.setUserId(enterpriseUserId);
			enterpriseUserRole.setRoleId(roleId);
			enterpriseUserRoleList.add(enterpriseUserRole);
		}
		//保存用户角色中间表
		enterpriseUserDao.batchInsertEnterpriseUserRole(enterpriseUserRoleList);
		return enterpriseUserId;
	}

	@Override
	public EnterpriseUserDTO getCascadeEnterpriseUserByUserId(int userId, int type) {
		EnterpriseUser enterpriseUser = enterpriseUserDao.selectCascadeEnterpriseUserByUserId(userId);
		EnterpriseUserDTO enterpriseUserDto = new EnterpriseUserDTO();
		BeanUtils.copyProperties(enterpriseUser, enterpriseUserDto);
		
		int enterpriseId = enterpriseUser.getEnterpriseId();

		//根据该用户的企业id返回角色list
		List<EnterpriseRole> enterpriseRoleList =  enterpriseRoleDao.selectEnterpriseRoleListByEnterpriseId(enterpriseId, type);
		List<EnterpriseRoleDTO> enterpriseRoleDtoList = new ArrayList<EnterpriseRoleDTO>();
		for(EnterpriseRole enterpriseRole : enterpriseRoleList){
			EnterpriseRoleDTO enterpriseRoleDto = new EnterpriseRoleDTO();
			BeanUtils.copyProperties(enterpriseRole, enterpriseRoleDto);
			enterpriseRoleDtoList.add(enterpriseRoleDto);
		}
		
		//enterpriseRoleDtoList 转换成map
		Map<Integer, EnterpriseRoleDTO> enterpriseRoleDtoMap = new HashMap<Integer, EnterpriseRoleDTO>();
		for(EnterpriseRoleDTO  enterpriseRoleDto : enterpriseRoleDtoList){
			enterpriseRoleDtoMap.put(enterpriseRoleDto.getId(), enterpriseRoleDto);
		}
		
		//处理该用户拥有的角色
		List<Integer> roleIdList = new ArrayList<Integer>();
		List<EnterpriseUserRole> enterpriseUserRoleList = enterpriseUser.getEnterpriseUserRoleList();
		for(EnterpriseUserRole enterpriseUserRole : enterpriseUserRoleList){
			roleIdList.add(enterpriseUserRole.getRoleId());
			EnterpriseRoleDTO _enterpriseRoleDto = enterpriseRoleDtoMap.get(enterpriseUserRole.getRoleId());
			if(_enterpriseRoleDto != null){
				_enterpriseRoleDto.setOwnerFlag(1);
			}
		}
		
		enterpriseUserDto.setEnterpriseRoleDtoList(enterpriseRoleDtoList);
		return enterpriseUserDto;
	}
	
	@Override
	public void updateEnterpriseUser(EnterpriseUserDTO enterpriseUserDto) {
		EnterpriseUser enterpriseUser = new EnterpriseUser();
		BeanUtils.copyProperties(enterpriseUserDto, enterpriseUser);
		
		String password = enterpriseUserDto.getPassword().trim();
		String resetPwd = CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.ENTERPRISEUSER_RESET_PASSWORD);
		
		if(resetPwd.equals(password)){
			enterpriseUser.setPassword(MD5Util.md5(password));
		}
		
		int enterpriseUserId = enterpriseUser.getId();
		// 处理该用户的角色
		List<Integer> roleIdList = enterpriseUserDto.getRoleIdList();
		List<EnterpriseUserRole> enterpriseUserRoleList = new ArrayList<EnterpriseUserRole>();
		for(Integer roleId : roleIdList){
			EnterpriseUserRole enterpriseUserRole = new EnterpriseUserRole();
			enterpriseUserRole.setUserId(enterpriseUserId);
			enterpriseUserRole.setRoleId(roleId);
			enterpriseUserRoleList.add(enterpriseUserRole);
		}
		
		enterpriseUserDao.updateEnterpriseUser(enterpriseUser);
		enterpriseUserDao.deleteEnterpriseUserRoleByUserId(enterpriseUserId);
		enterpriseUserDao.batchInsertEnterpriseUserRole(enterpriseUserRoleList);
				
	}
	
	@Override
	public void deleteEnterpriseUser(int userId) {
		EnterpriseUser enterpriseUser = enterpriseUserDao.selectCascadeEnterpriseUserByUserId(userId);
		if(enterpriseUser != null){
			//不做真实删除，而是改为无效
			enterpriseUser.setIsinvalid(1);
		}
		enterpriseUserDao.updateEnterpriseUser(enterpriseUser);
	}
	
	@Override
	public EnterpriseUserDTO getEnterpriseUserDtoFromCache(String token) {
		if (logger.isInfoEnabled()) {
			logger.info("will get enterpriseUserDto from cache with token : " + token);
		}

		// 根据token从缓存读取
		EnterpriseUserDTO enterpriseUserDto = (EnterpriseUserDTO) EnterpriseUserCache.getInstance().get(token);
		
		//判断用户操作是否失效
		/*long now_time = System.currentTimeMillis();
		Long last_login_time = EnterpriseUserTimeOutCache.getInstance().get(token);
		if(last_login_time==null){
			logger.info("now_time : "+now_time+"token : "+ token);
		}
		// 获取配置文件中的登录超时时间
		long time_out = Long.parseLong(ElectricPropertyPlaceholderConfigurer.getContextProperty(Constants.ENTERPRISEUSER_TIMEOUT));
		long diff_time = (now_time - last_login_time)/1000;
		
		if(diff_time > time_out){
			deleteTempDatas(token);
			throw new RuntimeException("登录超时Session失效了， is not exsit!");
		}else{
			// FIXME : 不用delete，直接save吧；最好把这个时间超时验证放到拦截器里面，而不是在 getEnterpriseUserDtoFromCache 这个方法里校验用户超时
			//EnterpriseUserTimeOutCache.getInstance().delete(token);
			EnterpriseUserTimeOutCache.getInstance().save(token, System.currentTimeMillis());
		}*/
		
		if (enterpriseUserDto == null) {
			if (logger.isInfoEnabled()) {
				logger.info("notice! will get enterpriseUserDto from db with token : "
						+ token);
			}

			EnterpriseUser enterpriseUser = enterpriseUserDao.selectEnterpriseUserByToken(token);
			if (enterpriseUser == null) {
				throw new RuntimeException("the enterpriseUser with token : " + token
						+ " is not exsit!");
			} else {
				// 转outer user dto
				enterpriseUserDto = new EnterpriseUserDTO();
				BeanUtils.copyProperties(enterpriseUser, enterpriseUserDto);

				// 获取该用户权限
				List<EnterpriseMenuDTO> enterpriseMenuDtoList = enterpriseAdminService.obtainEnterpriseUserMenuByUserId(enterpriseUserDto.getId());
				
				// 查询用户所属企业的信息
				Enterprise enterprise = enterpriseDao.selectEnterpriseById(enterpriseUserDto.getEnterpriseId());
				// 转 enterprise dto
				EnterpriseDTO enterpriseDto = new EnterpriseDTO();
				BeanUtils.copyProperties(enterprise, enterpriseDto);
				
				// 赋值
				enterpriseUserDto.setEnterpriseDto(enterpriseDto);
				
				//判断当前用户所属企业是否为一级企业
				/*if(enterpriseDto.getFid()==0){
					//查询当前企业及下级所有企业信息
					List<Enterprise> enterpriseList = enterpriseDao.selectEnterprisesListById(enterpriseDto.getId());
					List<EnterpriseDTO> enterpriseDtoList = new ArrayList<EnterpriseDTO>();
					for(Enterprise _enterprise : enterpriseList){
						EnterpriseDTO _enterpriseDto = new EnterpriseDTO();
						BeanUtils.copyProperties(_enterprise, _enterpriseDto);
						enterpriseDtoList.add(_enterpriseDto);
					}
					enterpriseUserDto.setEnterpriseDtoList(enterpriseDtoList);
				}*/
				//
				enterpriseUserDto.setOwnerEnterpriseMenuDtoList(enterpriseMenuDtoList);
				EnterpriseUserCache.getInstance().set(token, enterpriseUserDto); // 保存到缓存

				return enterpriseUserDto;
			}
		} else {
			return enterpriseUserDto;
		}
	}
	
	@Override
	public void deleteTempDatas(String token) {
		EnterpriseUserCache.getInstance().delete(token);
	}

	@Override
	public boolean enterpriseInterceptorCheck(String token) {
		if (logger.isInfoEnabled()) {
			logger.info("will get enterpriseUserDto from cache with token : " + token);
		}
		//判断token是否为空
		if(StringUtils.isEmpty(token)) {
			return false;
		}
		// 根据token从缓存读取
		EnterpriseUserDTO enterpriseUserDto = EnterpriseUserCache.getInstance().get(token);
		//创建标志位,用于标志用户是否在cache中存在
		boolean isExistUserInCache = true;
		//判断用户是否在cache中存在
		if (enterpriseUserDto == null) {
			if (logger.isInfoEnabled()) {
				logger.info("notice! will get enterpriseUserDto from db with token : "
						+ token);
			}
			isExistUserInCache = false;
			EnterpriseUser enterpriseUser = enterpriseUserDao.selectEnterpriseUserByToken(token);
			enterpriseUserDto = new EnterpriseUserDTO();
			BeanUtils.copyProperties(enterpriseUser, enterpriseUserDto);
		}
		//判断用户是否禁用
		if(enterpriseUserDto.getIsinvalid() == 1){
			return false;
		}else{
			Enterprise enterprise = enterpriseDao.selectEnterpriseById(enterpriseUserDto.getEnterpriseId());
			Integer enterpriseCache = EnterpriseStateCache.getInstance().get(enterprise.getCode());
			
			//判断企业状态Cache是否存在
			if(enterpriseCache==null){
				enterpriseCache = enterprise.getIsinvalid();
				EnterpriseStateCache.getInstance().set(enterprise.getCode(), enterprise.getIsinvalid());
			}
			//判断企业是否禁用
			if(enterpriseCache == 1){
				return false;
			}else{
				//判断用户所属企业是否存在上级企业
				if(enterprise.getFid() != 0){
					//算出上级企业code
					String fcode = enterprise.getCode().substring(0, 5)+"0000";
					//获取上级企业Cache
					Integer f_enterpriseCache = EnterpriseStateCache.getInstance().get(fcode);
					//上级企业Cache是否存在
					if(f_enterpriseCache == null){
						Enterprise f_enterprise = enterpriseDao.selectEnterpriseByCode(fcode);
						f_enterpriseCache = f_enterprise.getIsinvalid();
						EnterpriseStateCache.getInstance().set(f_enterprise.getCode(), f_enterprise.getIsinvalid());
					}
					//判断上级企业是否禁用
					if(f_enterpriseCache == 1){
						return false;
					}else{
						if(!isExistUserInCache){
							enterpriseUserDto = setEnterpriseUserInCache(enterpriseUserDto.getEmail());
						}
						return true;
					}	
				}else{
					if(!isExistUserInCache){
						enterpriseUserDto = setEnterpriseUserInCache(enterpriseUserDto.getEmail());
					}
					return true;
				}
			}
		}
		
	}
	
	@Override
	public EnterpriseUserDTO setEnterpriseUserInCache(String email) {
		EnterpriseUser enterpriseUser = enterpriseUserDao.selectEnterpriseUserByEmail(email);
		EnterpriseUserDTO enterpriseUserDto = new EnterpriseUserDTO();
		
		String token = MD5Util.md5(enterpriseUser.getEmail());
		enterpriseUser.setToken(token);

		// 更新 enterpriseUser 的token
		enterpriseUserDao.updateEnterpriseUserToken(enterpriseUser);

		// 在redis缓存中清除以前用户Cache
		if (logger.isInfoEnabled()) {
			logger.info("delete token : ("
					+ token
					+ ") from enterpriseUserCache");
		}
		EnterpriseUserCache.getInstance().delete(token);
		

		// 获取enterpriseUser的menu
		List<EnterpriseMenuDTO> enterpriseMenuDto = enterpriseAdminService
				.obtainEnterpriseUserMenuByUserId(enterpriseUser
						.getId());
		// 转enterpriseUser dto
		BeanUtils.copyProperties(enterpriseUser, enterpriseUserDto);

		// 查询用户所属企业的信息
		Enterprise enterprise = enterpriseDao.selectEnterpriseById(enterpriseUserDto.getEnterpriseId());
		// 转 enterprise dto
		EnterpriseDTO enterpriseDto = new EnterpriseDTO();
		BeanUtils.copyProperties(enterprise, enterpriseDto);
		
		// 赋值
		enterpriseUserDto.setEnterpriseDto(enterpriseDto);
		
		//判断当前用户所属企业是否为一级企业
		/*if(enterpriseDto.getFid()==0){
			//查询当前企业及下级所有企业信息
			List<Enterprise> enterpriseList = enterpriseDao.selectEnterprisesListById(enterpriseDto.getId());
			List<EnterpriseDTO> enterpriseDtoList = new ArrayList<EnterpriseDTO>();
			for(Enterprise _enterprise : enterpriseList){
				EnterpriseDTO _enterpriseDto = new EnterpriseDTO();
				BeanUtils.copyProperties(_enterprise, _enterpriseDto);
				enterpriseDtoList.add(_enterpriseDto);
			}
			enterpriseUserDto.setEnterpriseDtoList(enterpriseDtoList);
		}*/

		enterpriseUserDto.setOwnerEnterpriseMenuDtoList(enterpriseMenuDto);

		// 将 enterpriseUserDto 保存在redis中
		EnterpriseUserCache.getInstance().set(token, enterpriseUserDto);
		return enterpriseUserDto;
	}
	
}
