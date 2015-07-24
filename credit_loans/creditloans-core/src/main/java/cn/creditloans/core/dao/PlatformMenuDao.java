package cn.creditloans.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.platform.PlatformMenu;
import cn.creditloans.tools.page.PageModel;

public interface PlatformMenuDao {

	/**
	 * 查询所有菜单list
	 * @return
	 */
	List<PlatformMenu> selectPlatformMenuList();
	
	/**
	 * 根据fid查询子菜单list
	 * @param fid
	 * @return
	 */
	List<PlatformMenu> selectPlatformMenuListByFid(int fid);
	
	/**
	 * 根据menuId查询菜单对象
	 * @param id
	 * @return
	 */
	PlatformMenu selectPlatformMenuById(int menuId);
	
	/**
	 * 根据menuIdList获取菜单list
	 * @param menuIdList
	 * @return
	 */
	List<PlatformMenu> selectPlatformMenuListByIds(List<Integer> menuIdList);
	
	/**
	 * 根据菜单查询对象，获取菜单信息个数
	 * @param platformMenu
	 * @return
	 */
	int selectPlatformMenuPageCount(PlatformMenu platformMenu);
	
	/**
	 * 根据菜单查询对象和分页对象，获取分页菜单list
	 * @param pm
	 * @param platformMenu
	 * @return
	 */
	List<PlatformMenu> selectPlatformMenuPageList(@Param("pm") PageModel<?> pm, @Param("menu") PlatformMenu platformMenu);
	
	/**
	 * 添加菜单
	 * @param platformMenu
	 */
	void insertPlatformMenu(PlatformMenu platformMenu);
	
	/**
	 * 更新菜单
	 * @param platformMenu
	 */
	void updatePlatformMenu(PlatformMenu platformMenu);
	
	/**
	 * 删除菜单
	 * @param menuId
	 */
	void deletePlatformMenuById(int menuId);
}
