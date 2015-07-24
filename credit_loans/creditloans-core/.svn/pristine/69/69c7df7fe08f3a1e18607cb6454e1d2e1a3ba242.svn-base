package cn.creditloans.core.service;

import java.util.List;

import cn.creditloans.core.dto.enterprise.EnterpriseRoleDTO;
import cn.creditloans.tools.page.PageModel;

public interface EnterpriseRoleService {
	
	/**
	 * 根据企业id返回角色list
	 * @param enterpriseId
	 * @return
	 */
	List<EnterpriseRoleDTO> getEnterpriseRoleDtoListByEnterpriseId(int enterpriseId, int type);
	
	/**
	 * 分页展示角色信息
	 * @param enterpriseRoleDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<EnterpriseRoleDTO> getEnterpriseRolePageList(EnterpriseRoleDTO enterpriseRoleDto, int currentPage, int pageSize);
	
	/**
	 * 添加角色
	 * @param enterpriseRoleDto
	 * @return
	 */
	int saveCascadeEnterpriseRoleAndMenu(EnterpriseRoleDTO enterpriseRoleDto);
	
	/**
	 * 根据角色id查询相应的角色信息以及该角色对应拥有的菜单信息
	 * @param roleId
	 * @return
	 */
	EnterpriseRoleDTO getCascadeEnterpriseRoleAndMenuByRoleId(int roleId);
	
	/**
	 * 更新角色信息
	 * @param enterpriseRoleDto
	 */
	void updateEnterpriseRole(EnterpriseRoleDTO enterpriseRoleDto);
	
	/**
	 * 根据角色id删除角色
	 * @param roleId
	 */
	void deleteEnterpriseRole(int roleId);
	
	
}
