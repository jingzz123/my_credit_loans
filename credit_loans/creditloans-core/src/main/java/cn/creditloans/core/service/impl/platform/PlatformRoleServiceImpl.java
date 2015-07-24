package cn.creditloans.core.service.impl.platform;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.PlatformMenuDao;
import cn.creditloans.core.dao.PlatformRoleDao;
import cn.creditloans.core.dao.PlatformUserDao;
import cn.creditloans.core.dto.platform.PlatformMenuDTO;
import cn.creditloans.core.dto.platform.PlatformRoleDTO;
import cn.creditloans.core.entity.platform.PlatformMenu;
import cn.creditloans.core.entity.platform.PlatformRole;
import cn.creditloans.core.entity.platform.PlatformRoleMenu;
import cn.creditloans.core.service.PlatformRoleService;
import cn.creditloans.tools.page.PageModel;

@Service("platformRoleService")
public class PlatformRoleServiceImpl implements PlatformRoleService {
	private final static Log logger = LogFactory.getLog(PlatformRoleServiceImpl.class);
	
	@Autowired
	PlatformRoleDao platformRoleDao;
	
	@Autowired
	PlatformMenuDao platformMenuDao;
	
	@Autowired
	PlatformUserDao platformUserDao;
	
	@Override
	public List<PlatformRoleDTO> getPlatformRoleDtoList() {
		List<PlatformRole> platformRoleList = platformRoleDao.selectPlatformRoleList();
		List<PlatformRoleDTO> platformRoleDtoList = new ArrayList<PlatformRoleDTO>();
		for(PlatformRole platformRole : platformRoleList){
			PlatformRoleDTO platformRoleDto = new  PlatformRoleDTO();
			BeanUtils.copyProperties(platformRole, platformRoleDto);
			platformRoleDtoList.add(platformRoleDto);
		}
		return platformRoleDtoList;
	}

	@Override
	public PageModel<PlatformRoleDTO> getPlatformeRolePageList(
			PlatformRoleDTO platformRoleDto, int currentPage, int pageSize) {
		PlatformRole platformRole = new PlatformRole();
		BeanUtils.copyProperties(platformRoleDto, platformRole);
		
		PageModel<PlatformRoleDTO> pm = new PageModel<PlatformRoleDTO>();
		pm.init(currentPage, pageSize);
		
		int count = platformRoleDao.selectPlatformRolePageCount(platformRole);
		List<PlatformRole> platformRoleList = platformRoleDao.selectPlatformRolePageList(pm, platformRole);
		
		List<PlatformRoleDTO> platformRoleDtoList = new ArrayList<PlatformRoleDTO>();
		for(PlatformRole _platformRole : platformRoleList){
			PlatformRoleDTO _platformRoleDto = new PlatformRoleDTO();
			BeanUtils.copyProperties(_platformRole, _platformRoleDto);
			platformRoleDtoList.add(_platformRoleDto);
		}
		pm.setDatas(platformRoleDtoList);
		pm.setTotal(count);
		return pm;
	}

	@Override
	public int saveCascadePlatformRoleAndMenu(PlatformRoleDTO platformRoleDto) {
		PlatformRole platformRole = new PlatformRole();
		BeanUtils.copyProperties(platformRoleDto, platformRole);
		platformRoleDao.insertPlatformRole(platformRole);
		
		int roleId = platformRole.getId();
		List<PlatformRoleMenu> platformRoleMenuList = new ArrayList<PlatformRoleMenu>();
		List<Integer> menuIdList = platformRoleDto.getMenuIdList();
		for(Integer menuId : menuIdList){
			PlatformRoleMenu platformRoleMenu = new PlatformRoleMenu();
			platformRoleMenu.setRoleId(roleId);
			platformRoleMenu.setMenuId(menuId);
			platformRoleMenuList.add(platformRoleMenu);
		}
		platformRoleDao.batchInsertPlatformRoleMenu(platformRoleMenuList);
		return roleId;
	}

	@Override
	public PlatformRoleDTO getCascadePlatformRoleAndMenuByRoleId(int roleId) {
		PlatformRole platformRole = platformRoleDao.selectCascadePlatformRoleMenuByRoleId(roleId);
		PlatformRoleDTO platformRoleDto = new PlatformRoleDTO();
		BeanUtils.copyProperties(platformRole, platformRoleDto);
		
		List<PlatformRoleMenu> platformRoleMenuList = platformRole.getPlatformRoleMenuList();
		if(platformRoleMenuList.size()>0){
			List<Integer> menuIdList = new ArrayList<Integer>();
			for(PlatformRoleMenu platformRoleMenu : platformRoleMenuList){
				menuIdList.add(platformRoleMenu.getMenuId());
			}
			
			List<PlatformMenu> platformMenuList = platformMenuDao.selectPlatformMenuListByIds(menuIdList);
			List<PlatformMenuDTO> platformMenuDtoList = new ArrayList<PlatformMenuDTO>();
			for(PlatformMenu platformMenu : platformMenuList){
				PlatformMenuDTO platformMenuDto = new PlatformMenuDTO();
				BeanUtils.copyProperties(platformMenu, platformMenuDto);
				platformMenuDtoList.add(platformMenuDto);
			}
			platformRoleDto.setMenuIdList(menuIdList);
			platformRoleDto.setPlatformMenuDtoList(platformMenuDtoList);
		}
		return platformRoleDto;
	}

	@Override
	public void updatePlatformRole(PlatformRoleDTO platformRoleDto) {
		PlatformRole platformRole = new PlatformRole();
		BeanUtils.copyProperties(platformRoleDto, platformRole);
		platformRoleDao.updatePlatformRole(platformRole);
		
		int roleId = platformRole.getId();
		platformRoleDao.deletePlatformRoleMenuByRoleId(roleId);
		
		List<PlatformRoleMenu> platformRoleMenuList = new ArrayList<PlatformRoleMenu>();
		List<Integer> menuIdList = platformRoleDto.getMenuIdList();
		if(menuIdList.size()>0){
			for(Integer menuId : menuIdList){
				PlatformRoleMenu platformRoleMenu = new PlatformRoleMenu();
				platformRoleMenu.setRoleId(roleId);
				platformRoleMenu.setMenuId(menuId);
				platformRoleMenuList.add(platformRoleMenu);
			}
			platformRoleDao.batchInsertPlatformRoleMenu(platformRoleMenuList);
		}		
		
	}

	@Override
	public void deletePlatformRole(int roleId) {
		// 检查是否有与该role id关联的用户
		int count = platformUserDao.selectPlatformUserRoleCountByRoleId(roleId);
		if(count > 0){
			throw new RuntimeException("the role with id : " + roleId
					+ " is related with some user");
		}
		platformRoleDao.deletePlatformRoleById(roleId);
		platformRoleDao.deletePlatformRoleMenuByRoleId(roleId);
		
	}

}
