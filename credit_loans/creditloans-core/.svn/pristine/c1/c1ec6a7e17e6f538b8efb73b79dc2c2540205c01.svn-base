package cn.creditloans.core.service;

import java.util.List;
import cn.creditloans.core.dto.platform.PlatformDepartmentDTO;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.tree.TreeNode;

public interface PlatformDepartmentService {
	
	/**
	 * 根据ID获取部门信息
	 * @param id
	 * @return
	 */
	PlatformDepartmentDTO selectPlatformDepart(int id);
	
	/**
	 * 获取平台部门信息
	 * @param currentPage  当前页
	 * @param pageSize     
	 * @param departName   部门名称
	 * @param departId     部门id
	 * @return
	 */
	PageModel<PlatformDepartmentDTO> selectDepartmentList(int currentPage, int pageSize , String departName);
	
	/**
	 * 获取平台部门组织结构tree
	 * @return
	 */
	List<TreeNode> selectDepartmentTree();
	
	/**
	 * 重名验证
	 * @param name         部门名称
	 * @param parentId     父节点ID 
	 * @param id           部门ID 
	 * @return
	 */
	boolean checkDepartmentName(String name, int parentId, int id);
	
	/**
	 * 保存或者更新部门信息
	 * @param department
	 * @return
	 */
	
	boolean saveOrUpdateDepartment(PlatformDepartmentDTO department);
	
	/**
	 * 检查部门
	 * @param code编号是否唯一
	 * @return
	 */
	boolean checkDepartmentCode(int code, int id);
	
	/**
	 * 删除部门信息
	 * @param id
	 * @return
	 */
	int deleteDepartmentInfo(int id);
	
	/**
	 * 获取所有部门信息
	 * @return
	 */
	List<PlatformDepartmentDTO> getPlatformDepartmentDtoList();
}