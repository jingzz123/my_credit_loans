package cn.creditloans.core.service;

import java.util.List;

import cn.creditloans.core.dto.platform.PlatformRoleDTO;
import cn.creditloans.tools.page.PageModel;

public interface PlatformRoleService {
	
	/**
	 * 查询所有角色信息
	 * @return
	 */
	List<PlatformRoleDTO> getPlatformRoleDtoList();
	
	/**
	 * 分页展示角色信息
	 * @param platformRoleDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<PlatformRoleDTO> getPlatformeRolePageList(PlatformRoleDTO platformRoleDto, int currentPage, int pageSize);
	
	/**
	 * 添加角色
	 * @param platformRoleDto
	 * @return
	 */
	int saveCascadePlatformRoleAndMenu(PlatformRoleDTO platformRoleDto);
	
	/**
	 * 根据角色id查询相应的角色信息以及该角色对应拥有的菜单信息
	 * @param roleId
	 * @return
	 */
	PlatformRoleDTO getCascadePlatformRoleAndMenuByRoleId(int roleId);
	
	/**
	 * 更新角色信息
	 * @param enterpriseRoleDto
	 */
	void updatePlatformRole(PlatformRoleDTO platformRoleDto);
	
	/**
	 * 根据角色id删除角色
	 * @param roleId
	 */
	void deletePlatformRole(int roleId);
}
