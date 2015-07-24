package cn.creditloans.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.enterprise.EnterpriseRole;
import cn.creditloans.core.entity.enterprise.EnterpriseRoleMenu;
import cn.creditloans.tools.page.PageModel;

public interface EnterpriseRoleDao {
	
	/**
	 * 根据角色id级联获取role及其menu
	 * @param roleId
	 * @return
	 */
	EnterpriseRole selectCascadeEnterpriseRoleMenuByRoleId(int roleId);
	

	/**
	 * 根据企业获取相应角色list
	 * @param enterpriseId
	 * @return
	 */
	List<EnterpriseRole> selectEnterpriseRoleListByEnterpriseId(@Param("enterpriseId") int enterpriseId, @Param("type") int type);
	
	/**
	 * 根据菜单id查询EnterpriseRoleMenu返回个数
	 * @param menuId
	 * @return
	 */
	int selectEnterpriseRoleMenuCountByMenuId(int menuId);
	
	/**
	 * 根据角色查询对象获取相应角色信息个数
	 * @param enterpriseRole
	 * @return
	 */
	int selectEnterpriseRolePageCount(EnterpriseRole enterpriseRole);
	
	/**
	 * 根据角色查询对象获取分页角色list
	 * @param pm
	 * @param enterpriseRole
	 * @return
	 */
	List<EnterpriseRole> selectEnterpriseRolePageList(@Param("pm") PageModel<?> pm, @Param("role") EnterpriseRole enterpriseRole);
	
	/**
	 * 添加角色
	 * @param enterpriseRole
	 */
	void insertEnterpriseRole(EnterpriseRole enterpriseRole);
	
	/**
	 * 批量添加EnterpriseRoleMenu信息
	 * @param enterpriseRoleMenuList
	 */
	void batchInsertEnterpriseRoleMenu(List<EnterpriseRoleMenu> enterpriseRoleMenuList);
	
	/**
	 * 更新角色
	 * @param enterpriseRole
	 */
	void updateEnterpriseRole(EnterpriseRole enterpriseRole);
	
	/**
	 * 根据角色id删除角色
	 * @param roleId
	 */
	void deleteEnterpriseRoleById(int roleId);
	
	/**
	 * 根据角色id删除EnterpriseRoleMenu
	 * @param roleId
	 */
	void deleteEnterpriseRoleMenuByRoleId(int roleId);
	
	/**
	 * 根据需要删除的菜单idList和企业id，删除EnterpriseRoleMenu
	 * @param delMenuIdList
	 * @param enterpriseId
	 */
	void deleteEnterpriseRoleMenuByMenuIdListAndEnterpriseId(@Param("delMenuIdList")List<Integer> delMenuIdList, @Param("enterpriseId")int enterpriseId);
}
