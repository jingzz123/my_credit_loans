package cn.creditloans.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.platform.PlatformRole;
import cn.creditloans.core.entity.platform.PlatformRoleMenu;
import cn.creditloans.tools.page.PageModel;

public interface PlatformRoleDao {
		
	/**
	 * 根据角色id级联获取role及其menu
	 * @param roleId
	 * @return
	 */
	PlatformRole selectCascadePlatformRoleMenuByRoleId(int roleId);
	
	
	/**
	 * 根据菜单id查询PlatformRoleMenu返回个数
	 * @param menuId
	 * @return
	 */
	int selectPlatformRoleMenuCountByMenuId(int menuId);
	
	/**
	 * 根据角色查询对象获取相应角色信息个数
	 * @param platformRole
	 * @return
	 */
	int selectPlatformRolePageCount(PlatformRole platformRole);
	
	/**
	 * 根据角色查询对象获取分页角色list
	 * @param pm
	 * @param platformRole
	 * @return
	 */
	List<PlatformRole> selectPlatformRolePageList(@Param("pm") PageModel<?> pm, @Param("role") PlatformRole platformRole);
	

	/**
	 * 查询所有角色信息
	 * @return
	 */
	List<PlatformRole> selectPlatformRoleList();
	
	/**
	 * 添加角色
	 * @param platformRole
	 */
	void insertPlatformRole(PlatformRole platformRole);
	
	/**
	 * 批量添加PlatformRoleMenu信息
	 * @param platformRoleMenuList
	 */
	void batchInsertPlatformRoleMenu(List<PlatformRoleMenu> platformRoleMenuList);
	
	/**
	 * 更新角色
	 * @param platformRole
	 */
	void updatePlatformRole(PlatformRole platformRole);
	
	/**
	 * 根据角色id删除角色
	 * @param roleId
	 */
	void deletePlatformRoleById(int roleId);
	
	/**
	 * 根据角色id删除PlatformRoleMenu
	 * @param roleId
	 */
	void deletePlatformRoleMenuByRoleId(int roleId);
	
}
