package cn.creditloans.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.enterprise.EnterpriseMenu;
import cn.creditloans.tools.page.PageModel;

public interface EnterpriseMenuDao {
	
	/**
	 * 查询所有菜单list
	 * @return
	 */
	List<EnterpriseMenu> selectEnterpriseMenuList();
	
	/**
	 * 根据fid查询子菜单list
	 * @param fid
	 * @return
	 */
	List<EnterpriseMenu> selectEnterpriseMenuListByFid(int fid);
	
	/**
	 * 根据menuId查询菜单对象
	 * @param id
	 * @return
	 */
	EnterpriseMenu selectEnterpriseMenuById(int menuId);
	
	/**
	 * 根据menuIdList获取菜单list
	 * @param menuIdList
	 * @return
	 */
	List<EnterpriseMenu> selectEnterpriseMenuListByIds(List<Integer> menuIdList);
	
	/**
	 * 根据菜单查询对象，获取菜单信息个数
	 * @param enterpriseMenu
	 * @return
	 */
	int selectEnterpriseMenuPageCount(EnterpriseMenu enterpriseMenu);
	
	/**
	 * 根据菜单查询对象和分页对象，获取分页菜单list
	 * @param pm
	 * @param enterpriseMenu
	 * @return
	 */
	List<EnterpriseMenu> selectEnterpriseMenuPageList(@Param("pm") PageModel<?> pm, @Param("menu") EnterpriseMenu enterpriseMenu);
	
	/**
	 * 根据isOpen查询菜单id集合
	 * @param isOpen
	 * @return
	 */
	List<Integer> selectEnterpriseMenuIdsByIsOpen(int isOpen);
	
	/**
	 * 根据企业id查询EnterpriseOwnMenu菜单id集合
	 * @param enterpriseId
	 * @return
	 */
	List<Integer> selectEnterpriseOwnMenuIdsByEnterpriseId(int enterpriseId);
		
	/**
	 * 添加菜单
	 * @param enterpriseMenu
	 */
	void insertEnterpriseMenu(EnterpriseMenu enterpriseMenu);
	
	/**
	 * 更新菜单
	 * @param enterpriseMenu
	 */
	void updateEnterpriseMenu(EnterpriseMenu enterpriseMenu);
	
	/**
	 * 删除菜单
	 * @param menuId
	 */
	void deleteEnterpriseMenuById(int menuId);
}
