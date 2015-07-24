package cn.creditloans.core.service.impl.enterprise;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.EnterpriseDao;
import cn.creditloans.core.dao.EnterpriseMenuDao;
import cn.creditloans.core.dao.EnterpriseRoleDao;
import cn.creditloans.core.dao.EnterpriseUserDao;
import cn.creditloans.core.dto.enterprise.EnterpriseMenuDTO;
import cn.creditloans.core.dto.enterprise.EnterpriseRoleDTO;
import cn.creditloans.core.entity.enterprise.Enterprise;
import cn.creditloans.core.entity.enterprise.EnterpriseMenu;
import cn.creditloans.core.entity.enterprise.EnterpriseRole;
import cn.creditloans.core.entity.enterprise.EnterpriseRoleMenu;
import cn.creditloans.core.service.EnterpriseRoleService;
import cn.creditloans.tools.page.PageModel;

@Service("enterpriseRoleService")
public class EnterpriseRoleServiceImpl implements EnterpriseRoleService{
	private final static Log logger = LogFactory.getLog(EnterpriseRoleServiceImpl.class);
	
	@Autowired
	EnterpriseRoleDao enterpriseRoleDao;
	
	@Autowired
	EnterpriseMenuDao enterpriseMenuDao;
	
	@Autowired
	EnterpriseUserDao enterpriseUserDao;

	@Autowired
	EnterpriseDao enterpriseDao;
	
	@Override
	public List<EnterpriseRoleDTO> getEnterpriseRoleDtoListByEnterpriseId(
			int enterpriseId, int type) {
		List<EnterpriseRole> enterpriseRoleList = enterpriseRoleDao.selectEnterpriseRoleListByEnterpriseId(enterpriseId, type);
		List<EnterpriseRoleDTO> enterpriseRoleDtoList = new ArrayList<EnterpriseRoleDTO>();
		
		for(EnterpriseRole enterpriseRole : enterpriseRoleList){
			EnterpriseRoleDTO enterpriseRoleDto = new EnterpriseRoleDTO();
			BeanUtils.copyProperties(enterpriseRole, enterpriseRoleDto);
			enterpriseRoleDtoList.add(enterpriseRoleDto);
		}
		return enterpriseRoleDtoList;
	}

	@Override
	public PageModel<EnterpriseRoleDTO> getEnterpriseRolePageList(
			EnterpriseRoleDTO enterpriseRoleDto, int currentPage, int pageSize) {
		EnterpriseRole enterpriseRole = new EnterpriseRole();
		BeanUtils.copyProperties(enterpriseRoleDto, enterpriseRole);
		
		PageModel<EnterpriseRoleDTO> pm = new PageModel<EnterpriseRoleDTO>();
		pm.init(currentPage,pageSize);
		
		int count = enterpriseRoleDao.selectEnterpriseRolePageCount(enterpriseRole);
		List<EnterpriseRole> enterpriseRoleList = enterpriseRoleDao.selectEnterpriseRolePageList(pm, enterpriseRole);
		
		List<EnterpriseRoleDTO> enterpriseRoleDtoList = new ArrayList<EnterpriseRoleDTO>();
		List<Enterprise> enterpriseList = enterpriseDao.selectEnterpriseList();
		for(EnterpriseRole _enterpriseRole : enterpriseRoleList){
			EnterpriseRoleDTO _enterpriseRoleDto = new EnterpriseRoleDTO();
			BeanUtils.copyProperties(_enterpriseRole, _enterpriseRoleDto);
			for(Enterprise enterprise:enterpriseList){
				if(enterprise.getId()==_enterpriseRoleDto.getEnterpriseId()){
					_enterpriseRoleDto.setEnterpriseName(enterprise.getName());
				}
			}
			enterpriseRoleDtoList.add(_enterpriseRoleDto);
		}
		pm.setDatas(enterpriseRoleDtoList);
		pm.setTotal(count);
		return pm;
	}

	@Override
	public int saveCascadeEnterpriseRoleAndMenu(
			EnterpriseRoleDTO enterpriseRoleDto) {
		EnterpriseRole enterpriseRole = new EnterpriseRole();
		BeanUtils.copyProperties(enterpriseRoleDto, enterpriseRole);
		enterpriseRoleDao.insertEnterpriseRole(enterpriseRole);
		
		int roleId = enterpriseRole.getId();
		
		List<EnterpriseRoleMenu> enterpriseRoleMenuList = new ArrayList<EnterpriseRoleMenu>();
		List<Integer> menuIdList = enterpriseRoleDto.getMenuIdList();
		for(Integer menuId : menuIdList){
			EnterpriseRoleMenu enterpriseRoleMenu = new EnterpriseRoleMenu();
			enterpriseRoleMenu.setRoleId(roleId);
			enterpriseRoleMenu.setMenuId(menuId);
			enterpriseRoleMenuList.add(enterpriseRoleMenu);
		}
		enterpriseRoleDao.batchInsertEnterpriseRoleMenu(enterpriseRoleMenuList);
		return roleId;
	}

	@Override
	public EnterpriseRoleDTO getCascadeEnterpriseRoleAndMenuByRoleId(int roleId) {
		EnterpriseRole enterpriseRole = enterpriseRoleDao.selectCascadeEnterpriseRoleMenuByRoleId(roleId);
		EnterpriseRoleDTO enterpriseRoleDto = new EnterpriseRoleDTO();
		BeanUtils.copyProperties(enterpriseRole, enterpriseRoleDto);
		
		List<EnterpriseRoleMenu> enterpriseRoleMenuList = enterpriseRole.getEnterpriseRoleMenuList();
		if(enterpriseRoleMenuList.size()>0){
			List<Integer> menuIdList = new ArrayList<Integer>();
			for(EnterpriseRoleMenu enterpriseRoleMenu : enterpriseRoleMenuList){
				menuIdList.add(enterpriseRoleMenu.getMenuId());
			}
			List<EnterpriseMenu> enterpriseMenuList = enterpriseMenuDao.selectEnterpriseMenuListByIds(menuIdList);
			
			List<EnterpriseMenuDTO> enterpriseMenuDtoList = new ArrayList<EnterpriseMenuDTO>();
			for(EnterpriseMenu enterpriseMenu : enterpriseMenuList){
				EnterpriseMenuDTO enterpriseMenuDto = new EnterpriseMenuDTO();
				BeanUtils.copyProperties(enterpriseMenu, enterpriseMenuDto);
				enterpriseMenuDtoList.add(enterpriseMenuDto);
			}
			
			enterpriseRoleDto.setMenuIdList(menuIdList);
			enterpriseRoleDto.setEnterpriseMenuDtoList(enterpriseMenuDtoList);
		}
		return enterpriseRoleDto;
	}

	@Override
	public void updateEnterpriseRole(EnterpriseRoleDTO enterpriseRoleDto) {
		EnterpriseRole enterpriseRole = new EnterpriseRole();
		BeanUtils.copyProperties(enterpriseRoleDto, enterpriseRole);
		
		enterpriseRoleDao.updateEnterpriseRole(enterpriseRole);
		
		int roleId = enterpriseRole.getId();
		enterpriseRoleDao.deleteEnterpriseRoleMenuByRoleId(roleId);
		
		List<EnterpriseRoleMenu> enterpriseRoleMenuList = new ArrayList<EnterpriseRoleMenu>();
		List<Integer> menuIdList = enterpriseRoleDto.getMenuIdList();
		if(menuIdList.size()>0){
			for(Integer menuId : menuIdList){
				EnterpriseRoleMenu enterpriseRoleMenu = new EnterpriseRoleMenu();
				enterpriseRoleMenu.setRoleId(roleId);
				enterpriseRoleMenu.setMenuId(menuId);
				enterpriseRoleMenuList.add(enterpriseRoleMenu);
			}
			enterpriseRoleDao.batchInsertEnterpriseRoleMenu(enterpriseRoleMenuList);
		}
	}

	@Override
	public void deleteEnterpriseRole(int roleId) {
		// 检查是否有与该role id关联的用户
		int count = enterpriseUserDao.selectEnterpriseUserRoleCountByRoleId(roleId);
		if(count > 0){
			throw new RuntimeException("the role with id : " + roleId
					+ " is related with some user");
		}
		enterpriseRoleDao.deleteEnterpriseRoleById(roleId);
		enterpriseRoleDao.deleteEnterpriseRoleMenuByRoleId(roleId);		
	}

}
