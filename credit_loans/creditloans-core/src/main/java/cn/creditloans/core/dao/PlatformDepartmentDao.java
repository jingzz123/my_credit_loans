package cn.creditloans.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.platform.PlatformDepartment;
import cn.creditloans.tools.page.PageModel;

public interface PlatformDepartmentDao {

	/**
	 * 根据id获取平台部门对象
	 * @param id
	 * @return
	 */
	PlatformDepartment selectPlatformDepartmentById(int id);
	
	/**
	 * 获取所有平台部门list
	 * @return
	 */
	List<PlatformDepartment> selectPlatformDepartmentList();
	
	/**
	 * 根据idList获取相应的平台部门list
	 * @param idList
	 * @return
	 */
	List<PlatformDepartment> selectPlatformDepartmentsByIds(List<Integer> idList);
	
	/**
	 * 根据fid获取相应子平台部门list
	 * @param fid
	 * @return
	 */
	List<PlatformDepartment> selectPlatformDepartmentListByFid(int fid);
	
	/**
	 * 根据code获取平台部门对象
	 * @param code
	 * @return
	 */
	PlatformDepartment selectPlatformDepartmentByCode(int code);
	
	/**
	 * 根据id获取子平台部门个数
	 * @param departmentId
	 * @return
	 */
	int selectPlatformDepartmentCountByFid(int id);
	
	/**
	 * 根据查询对象，获取相应平台部门信息个数
	 * @param department
	 * @return
	 */
	int selectPlatformDepartmentPageCount(PlatformDepartment department);
	
	/**
	 * 根据查询对象和分页条件，分页获取相应平台部门list
	 * @param pm
	 * @param department
	 * @return
	 */
	List<PlatformDepartment> selectPlatformDepartmentPageList(@Param("pm") PageModel<?> pm, @Param("department") PlatformDepartment department);
	
	/**
	 * 添加平台部门对象
	 * @param department
	 */
	void insertPlatformDepartment(PlatformDepartment department);
	
	/**
	 * 更新平台部门对象
	 * @param department
	 */
	void updatePlatformDepartment(PlatformDepartment department);
	
	/**
	 * 删除平台部门对象
	 * @param departmentId
	 */
	void deletePlatformDepartmentById(int id);
	
	/**
	 * 重名验证
	 * @param params
	 * @return
	 */
	int selectDepartmentNameCount(Map<String, Object> params);
	
	/**
	 * 验证部门编号
	 * @param code
	 * @return
	 */
	int selectDepartmentCode(Map<String, Object> params);
	
	/**
	 * 部门用户数
	 * @param id
	 * @return
	 */
	int selectDepartmentUserCount(int id);
}
