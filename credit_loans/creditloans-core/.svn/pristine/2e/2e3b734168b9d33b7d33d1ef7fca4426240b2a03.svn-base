package cn.creditloans.core.service.impl.enterprise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.cache.redis.EnterpriseStateCache;
import cn.creditloans.core.dao.EnterpriseDao;
import cn.creditloans.core.dao.EnterpriseMenuDao;
import cn.creditloans.core.dao.EnterpriseRoleDao;
import cn.creditloans.core.dao.EnterpriseUserDao;
import cn.creditloans.core.dto.enterprise.EnterpriseDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseUserDTO;
import cn.creditloans.core.entity.enterprise.Enterprise;
import cn.creditloans.core.entity.enterprise.EnterpriseOwnMenu;
import cn.creditloans.core.entity.enterprise.EnterpriseRole;
import cn.creditloans.core.entity.enterprise.EnterpriseRoleMenu;
import cn.creditloans.core.entity.enterprise.EnterpriseUser;
import cn.creditloans.core.service.EnterpriseService;
import cn.creditloans.core.service.EnterpriseUserService;
import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.encrypt.MD5Util;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.sequence.SequenceGenerator;
import cn.creditloans.tools.util.CreditloansPropertyPlaceholderConfigurer;

@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService{
	
	private final static Log logger = LogFactory.getLog(EnterpriseServiceImpl.class);
	//删除成功
	private static final int DELETE_SUCCESS = 0;
	//删除失败
	private static final int DELETE_FAIL = 1;
	//删除提示
	private static final int DELETE_TIP = 2;
	
	@Autowired
	EnterpriseDao enterpriseDao; 
	
	@Autowired
	EnterpriseRoleDao enterpriseRoleDao;
	
	@Autowired
	EnterpriseMenuDao enterpriseMenuDao;
	
	@Autowired
	EnterpriseUserService enterpriseUserService;
	
	@Autowired
	EnterpriseUserDao enterpriseUserDao;
	
	@Override
	public List<EnterpriseDTO> getAllEnterpriseDtoList() {
		List<Enterprise> enterpriseList = enterpriseDao.selectEnterpriseList();
		List<EnterpriseDTO> enterpriseDtoList = new ArrayList<EnterpriseDTO>();
		for(Enterprise enterprise : enterpriseList){
			EnterpriseDTO enterpriseDto = new EnterpriseDTO();
			BeanUtils.copyProperties(enterprise, enterpriseDto);
			enterpriseDtoList.add(enterpriseDto);
		}
		return enterpriseDtoList;
	}

	@Override
	public PageModel<EnterpriseDTO> enterpriseInfoList(EnterpriseDTO enterpriseDto, int currentPage, int pageSize) {
		//企业信息
		PageModel<EnterpriseDTO> pageModel = new PageModel<EnterpriseDTO>();
		//更新当前页信息
		pageModel.init(currentPage, pageSize);
		//获取企业类型总条数
		Enterprise enterprise = new Enterprise();
		BeanUtils.copyProperties(enterpriseDto, enterprise);
		int pageCount = enterpriseDao.selectEnterprisePageCount(enterprise);
		pageModel.setTotal(pageCount);
		List<Enterprise> enterpriseResults = enterpriseDao.selectEnterprisePageList(pageModel, enterprise);
		List<EnterpriseDTO> enterpriseDtoList = new ArrayList<EnterpriseDTO>();
		for(Enterprise _enterprise : enterpriseResults ){
			EnterpriseDTO _enterpriseDto = new EnterpriseDTO();
			BeanUtils.copyProperties(_enterprise, _enterpriseDto);
			enterpriseDtoList.add(_enterpriseDto);
		}
		pageModel.setDatas(enterpriseDtoList);
		return pageModel;
	}

	@Override
	public boolean checkEnterpriseName(String name) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		int countName = enterpriseDao.selectEnterpriseName(param);
		if(countName > 0){
			return false;
		}
		return true;
	}
	
	//FIXME: 添加事务
	@Override
	public boolean saveEnterpriseUserInfo(EnterpriseDTO enterpriseDto) {
		String code = sequenceNumber();
		enterpriseDto.setCode(code);
		Enterprise enterprise = new Enterprise();
		BeanUtils.copyProperties(enterpriseDto, enterprise);
		boolean isSucc = false;
		try {			
			enterpriseDao.insertEnterprise(enterprise);
			int enterpriseId = enterprise.getId();
			
			//为企业创建超级管理员角色
			EnterpriseRole enterpriseRole = new EnterpriseRole();
			enterpriseRole.setEnterpriseId(enterpriseId);
			enterpriseRole.setName("超级管理员_"+enterpriseId);
			enterpriseRole.setType(0);
			enterpriseRoleDao.insertEnterpriseRole(enterpriseRole);
			int roleId = enterpriseRole.getId();
			
			//为企业创建超级管理员用户
			EnterpriseUserDTO enterpriseUserDto = new EnterpriseUserDTO();
			enterpriseUserDto.setEnterpriseId(enterpriseId);
			enterpriseUserDto.setName("超级管理员");
			enterpriseUserDto.setEmail(enterpriseDto.getUserEmail());
			enterpriseUserDto.setPassword(enterpriseDto.getUserPassword());
			enterpriseUserDto.setIsinvalid(0);
			enterpriseUserDto.setType(0);
			List<Integer> roleIdList = new ArrayList<Integer>();
			roleIdList.add(roleId);
			enterpriseUserDto.setRoleIdList(roleIdList);
			enterpriseUserService.saveEnterpriseUser(enterpriseUserDto);
			
			//为超级管理员及企业创建对应的菜单信息
			List<EnterpriseOwnMenu> enterpriseOwnMenuList = new ArrayList<EnterpriseOwnMenu>();
			List<EnterpriseRoleMenu> enterpriseRoleMenuList = new ArrayList<EnterpriseRoleMenu>();
			List<Integer> menuIdList = enterpriseDto.getMenuIdList();
			List<Integer> disableMenuIdList = enterpriseMenuDao.selectEnterpriseMenuIdsByIsOpen(1);
			for(Integer menuId : menuIdList){
				EnterpriseOwnMenu enterpriseOwnMenu = new EnterpriseOwnMenu();
				enterpriseOwnMenu.setEnterpriseId(enterpriseId);
				if(!disableMenuIdList.contains(menuId)){
					enterpriseOwnMenu.setMenuId(menuId);
				}
				enterpriseOwnMenuList.add(enterpriseOwnMenu);
				
				EnterpriseRoleMenu enterpriseRoleMenu = new EnterpriseRoleMenu();
				enterpriseRoleMenu.setRoleId(roleId);
				enterpriseRoleMenu.setMenuId(menuId);
				enterpriseRoleMenuList.add(enterpriseRoleMenu);
			}
			enterpriseRoleDao.batchInsertEnterpriseRoleMenu(enterpriseRoleMenuList);
			enterpriseDao.batchInsertEnterpriseOwnMenu(enterpriseOwnMenuList);

			//企业状态信息存入cache
			EnterpriseStateCache.getInstance().set(enterprise.getCode(), enterprise.getIsinvalid());
			isSucc = true;
		} catch (Exception e) {
			isSucc = false;
			logger.error("<<<<<<<<<<<<<<<<<<<<<<<<插入企业信息失败", e);
		}
		return isSucc;
	}

	@Override
	public boolean updateEnterpriseUserInfo(EnterpriseDTO enterpriseDto) {
		Enterprise enterprise = new Enterprise();
		BeanUtils.copyProperties(enterpriseDto, enterprise);
		boolean isSucc = false;
		try {			
			enterpriseDao.updateEnterprise(enterprise);
			int enterpriseId = enterprise.getId();
			int roleId = enterpriseDto.getRoleId();
			//更新超级管理员用户信息
			EnterpriseUser enterpriseUser = new EnterpriseUser();
			enterpriseUser.setId(enterpriseDto.getUserId());
			enterpriseUser.setEnterpriseId(enterpriseId);
			enterpriseUser.setName("超级管理员");
			enterpriseUser.setEmail(enterpriseDto.getUserEmail());
			enterpriseUser.setIsinvalid(0);
			enterpriseUser.setType(0);
			String password = enterpriseDto.getUserPassword();
			String resetPwd = CreditloansPropertyPlaceholderConfigurer.getContextProperty(Constants.ENTERPRISEUSER_RESET_PASSWORD);
			if(resetPwd.equals(password)){
				enterpriseUser.setPassword(MD5Util.md5(password));
			}else{
				enterpriseUser.setPassword(password);
			}
			enterpriseUserDao.updateEnterpriseUser(enterpriseUser);
			
			//更新超级管理员及企业创建对应的菜单信息
			List<EnterpriseOwnMenu> enterpriseOwnMenuList = new ArrayList<EnterpriseOwnMenu>();
			List<EnterpriseRoleMenu> enterpriseRoleMenuList = new ArrayList<EnterpriseRoleMenu>();
			List<Integer> menuIdList = enterpriseDto.getMenuIdList();
			List<Integer> oldMenuIdList = enterpriseDto.getOldMenuIdList();
			//对比修改前后的菜单idList,找出被删除的idList
			List<Integer> delMenuIdList = new ArrayList<Integer>();
			for(Integer oldMenuId : oldMenuIdList){
				if(!menuIdList.contains(oldMenuId)){
					delMenuIdList.add(oldMenuId);
				}
			}
			
			List<Integer> disableMenuIdList = enterpriseMenuDao.selectEnterpriseMenuIdsByIsOpen(1);
			for(Integer menuId : menuIdList){
				EnterpriseOwnMenu enterpriseOwnMenu = new EnterpriseOwnMenu();
				enterpriseOwnMenu.setEnterpriseId(enterpriseId);
				if(!disableMenuIdList.contains(menuId)){
					enterpriseOwnMenu.setMenuId(menuId);
				}
				enterpriseOwnMenuList.add(enterpriseOwnMenu);
				
				EnterpriseRoleMenu enterpriseRoleMenu = new EnterpriseRoleMenu();
				enterpriseRoleMenu.setRoleId(roleId);
				enterpriseRoleMenu.setMenuId(menuId);
				enterpriseRoleMenuList.add(enterpriseRoleMenu);
			}
			enterpriseRoleDao.deleteEnterpriseRoleMenuByRoleId(roleId);
			enterpriseRoleDao.batchInsertEnterpriseRoleMenu(enterpriseRoleMenuList);
			enterpriseDao.deleteEnterpriseOwnMenuByEnterpriseId(enterpriseId);
			enterpriseDao.batchInsertEnterpriseOwnMenu(enterpriseOwnMenuList);
			
			if(delMenuIdList.size() > 0){
				enterpriseRoleDao.deleteEnterpriseRoleMenuByMenuIdListAndEnterpriseId(delMenuIdList, enterpriseId);				
			}
			//企业状态信息存入cache
			EnterpriseStateCache.getInstance().set(enterprise.getCode(), enterprise.getIsinvalid());
			
			isSucc = true;
		} catch (Exception e) {
			isSucc = false;
			logger.error("<<<<<<<<<<<<<<<<<<<<<<<<<<更新企业信息失败", e);
		}
		return isSucc;
	}

	@Override
	public int deleteEnterpriseInfo(int id) {
		//该企业是否和用户已做关联如果已做关联做提示处理
		/*int enterpriseUserCount = enterpriseDao.selectEnterpriseUser(id);
		if(enterpriseUserCount > 0){			
			return DELETE_TIP;
		}*/
		try {
			enterpriseDao.deleteEnterpriseById(id);
			//企业状态信息存入cache
			Enterprise enterprise = enterpriseDao.selectEnterpriseById(id);
			EnterpriseStateCache.getInstance().set(enterprise.getCode(), 1);
		} catch (Exception e) {
			logger.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<删除企业信息失败", e);
			return DELETE_FAIL;
		}
		return DELETE_SUCCESS;
	}

	@Override
	public EnterpriseDTO selectEnterpriseUserInfo(int id) {
		Enterprise enterprise = enterpriseDao.selectCascadeEnterpriseById(id);
		EnterpriseDTO enterpriseDto = new EnterpriseDTO();
		BeanUtils.copyProperties(enterprise, enterpriseDto);
		
		List<EnterpriseRoleMenu> enterpriseRoleMenuList = enterprise.getEnterpriseRoleMenuList();
		int roleId = enterpriseRoleMenuList.get(0).getRoleId();
		
		//加载超级管理员用户信息
		EnterpriseUser enterpriseUser = enterpriseUserDao.selectEnterpriseUserByRoleId(roleId);
		enterpriseDto.setUserId(enterpriseUser.getId());
		enterpriseDto.setUserEmail(enterpriseUser.getEmail());
		enterpriseDto.setUserPassword(enterpriseUser.getPassword());
		
		//加载超级管理员角色及菜单信息
		enterpriseDto.setRoleId(roleId);
		List<Integer> menuIdList = new ArrayList<Integer>();
		for(EnterpriseRoleMenu enterpriseRoleMenu : enterpriseRoleMenuList){
			menuIdList.add(enterpriseRoleMenu.getMenuId());
		}
		enterpriseDto.setMenuIdList(menuIdList);		
		
		return enterpriseDto;
	}

	@Override
	public boolean checkEnterpriseCodeIsExist(String code) {
		Enterprise enterprise = enterpriseDao.selectEnterpriseByCode(code);
		if(enterprise != null){
			return false;
		}
		return true;
	}

	@Override
	public List<EnterpriseDTO> getEnterpriseDtoListByFid(int fid) {
		List<Enterprise> enterpriseList = enterpriseDao.selectEnterpriseListByFid(fid);
		List<EnterpriseDTO> enterpriseDtoList = new ArrayList<EnterpriseDTO>();
		for(Enterprise enterprise : enterpriseList){
			EnterpriseDTO enterpriseDto = new EnterpriseDTO();
			BeanUtils.copyProperties(enterprise, enterpriseDto);
			enterpriseDtoList.add(enterpriseDto);
		}
		return enterpriseDtoList;
	}

	@Override
	public List<EnterpriseDTO> getEnterpriseDtoListByEnterpriseId(
			int enterpriseId) {
		List<Enterprise> enterpriseList = enterpriseDao.selectEnterprisesListById(enterpriseId);		
		List<EnterpriseDTO> enterpriseDtoList = new ArrayList<EnterpriseDTO>();
		for(Enterprise enterprise : enterpriseList){
			EnterpriseDTO enterpriseDto = new EnterpriseDTO();
			BeanUtils.copyProperties(enterprise, enterpriseDto);
			enterpriseDtoList.add(enterpriseDto);
		}
		return enterpriseDtoList;
	}

	@Override
	public PageModel<EnterpriseDTO> subsidiaryInfoList(EnterpriseDTO enterpriseDto, int currentPage, int pageSize) {
		//企业信息
		PageModel<EnterpriseDTO> pageModel = new PageModel<EnterpriseDTO>();
		//更新当前页信息
		pageModel.init(currentPage, pageSize);
		//获取企业类型总条数
		Enterprise enterprise = new Enterprise();
		BeanUtils.copyProperties(enterpriseDto, enterprise);
		int total = enterpriseDao.selectSubsidiaryInfoCount(enterprise);
		List<Enterprise> enterprises = enterpriseDao.selectSubsidiaryPageList(pageModel, enterprise);
		List<EnterpriseDTO> enterpriseDtoList = new ArrayList<EnterpriseDTO>();
		for(Enterprise _enterprise : enterprises ){
			EnterpriseDTO _enterpriseDto = new EnterpriseDTO();
			BeanUtils.copyProperties(_enterprise, _enterpriseDto);
			enterpriseDtoList.add(_enterpriseDto);
		}
		pageModel.setTotal(total);
		pageModel.setDatas(enterpriseDtoList);
		return pageModel;
	}

	@Override
	public EnterpriseDTO getEnterpriseById(int id) {
		Enterprise enterprise = enterpriseDao.selectSubsidiaryById(id);
		EnterpriseDTO enterpriseDTO = null;
		if(enterprise != null){
			enterpriseDTO = new EnterpriseDTO();
			BeanUtils.copyProperties(enterprise, enterpriseDTO);
		}
		return enterpriseDTO;
	}
	
	@Override
	public List<EnterpriseDTO> selectEnterpriseDtoList(int id) {
		List<Enterprise> enterprises = new ArrayList<Enterprise>();
		List<Enterprise> enterpriseList = enterpriseDao.selectEnterpriseList();
		getEnterpriseDtoRecursionById(id,enterprises,enterpriseList);
		List<EnterpriseDTO> enterpriseDtoList = new ArrayList<EnterpriseDTO>();
		for(Enterprise enterprise : enterprises){
			EnterpriseDTO enterpriseDto = new EnterpriseDTO();
			BeanUtils.copyProperties(enterprise, enterpriseDto);
			enterpriseDtoList.add(enterpriseDto);
		}
		return enterpriseDtoList;
	}
	
	@Override
	public boolean saveEnterpriseInfo(EnterpriseDTO enterpriseDto) {
		String code = null;
		// 0 总企业
		if (enterpriseDto.getFid() == 0) {
			code = sequenceNumber();
		}else {
			code = childSequenceNumber(enterpriseDto.getFid());
		}
		enterpriseDto.setCode(code);
		Enterprise enterprise = new Enterprise();
		BeanUtils.copyProperties(enterpriseDto, enterprise);
		boolean isSucc = false;
		try {			
			enterpriseDao.insertEnterprise(enterprise);
			//企业状态信息存入cache
			EnterpriseStateCache.getInstance().set(enterprise.getCode(), enterprise.getIsinvalid());
			isSucc = true;
		} catch (Exception e) {
			isSucc = false;
			logger.error("<<<<<<<<<<<<<<<<<<<<<<<<插入企业信息失败", e);
		}
		return isSucc;
	}

	@Override
	public boolean updateEnterpriseInfo(EnterpriseDTO enterpriseDto) {
		Enterprise enterprise = new Enterprise();
		BeanUtils.copyProperties(enterpriseDto, enterprise);
		boolean isSucc = false;
		try {			
			enterpriseDao.updateEnterprise(enterprise);
			//企业状态信息存入cache
			EnterpriseStateCache.getInstance().set(enterprise.getCode(), enterprise.getIsinvalid());
			isSucc = true;
		} catch (Exception e) {
			isSucc = false;
			logger.error("<<<<<<<<<<<<<<<<<<<<<<<<<<更新企业信息失败", e);
		}
		return isSucc;
	}

	@Override
	public EnterpriseDTO selectEnterpriseInfo(int id) {
		Enterprise enterprise = enterpriseDao.selectEnterpriseById(id);
		EnterpriseDTO enterpriseDto = new EnterpriseDTO();
		BeanUtils.copyProperties(enterprise, enterpriseDto);
		return enterpriseDto;
	}
	
	/**
	 * 递归查询企业
	 * @param id
	 * @param enterprises
	 * @param enterpriseList
	 */
	private void getEnterpriseDtoRecursionById(int id, List<Enterprise> enterprises, List<Enterprise> enterpriseList) {
		if (id > 0) {
			for(Enterprise enterprise : enterpriseList){
				if(enterprise.getId() == id){
					enterprises.add(enterprise);
				}
				if(enterprise.getFid() == id){
					getEnterpriseDtoRecursionById(enterprise.getId(), enterprises, enterpriseList);
				}
			}
		}
		
	}

	/**
	 * 总企业code
	 * @param name   总企业名称
	 * @return
	 */
	@Override
	public String sequenceNumber() {
		// 企业code
		String enterpriseCode = null;
		//获取序列号    enterpriseCode
		int sequence = SequenceGenerator.getInstance().getSequence("enterprise_code_seq");
		// 总企业code编码是 0001-0000
		if (sequence < 10) {
			enterpriseCode = "000" + sequence + "-0000";
		} else if (sequence < 100) {
			enterpriseCode = "00" + sequence + "-0000";
		} else if (sequence < 1000) {
			enterpriseCode = "0" + sequence + "-0000";
		} else {
			enterpriseCode = sequence + "-0000";
		}
		return enterpriseCode;
	}
	
	/**
	 * 
	 * @param parentId  父企业id
	 * @return
	 */
	private String childSequenceNumber(int parentId) {
		// 总企业code
		String headquartersCode = null;
		// 企业code
		String enterpriseCode = null;
		Enterprise enterprise = enterpriseDao.selectCascadeEnterpriseById(parentId);
		if (enterprise != null) {
			headquartersCode = enterprise.getCode().substring(0, 4);   //截取总企业code
		} else {
			headquartersCode = "0000";
		}
		// 获取序列号
		int sequence = SequenceGenerator.getInstance().getSequence("enterpriseCode");
		// 总企业code编码是0000-0000
		if (sequence < 10) {
			enterpriseCode = headquartersCode + "-000" + sequence;
		} else if (sequence < 100) {
			enterpriseCode = headquartersCode + "-00" + sequence;
		} else if (sequence < 1000) {
			enterpriseCode = headquartersCode + "-0" + sequence;
		} else {
			enterpriseCode = headquartersCode + "-" + sequence;
		}
		return enterpriseCode;
	}
}
