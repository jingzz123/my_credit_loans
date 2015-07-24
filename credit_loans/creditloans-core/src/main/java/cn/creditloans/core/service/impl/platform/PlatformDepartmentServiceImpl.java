package cn.creditloans.core.service.impl.platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.PlatformDepartmentDao;
import cn.creditloans.core.dto.platform.PlatformDepartmentDTO;
import cn.creditloans.core.entity.platform.PlatformDepartment;
import cn.creditloans.core.service.PlatformDepartmentService;
import cn.creditloans.tools.page.PageModel;
import cn.creditloans.tools.tree.TreeNode;

@Service("platformDepartmentService")
public class PlatformDepartmentServiceImpl implements PlatformDepartmentService{
	
	private static final Log loger = LogFactory.getLog(PlatformDepartmentServiceImpl.class);

	//注入平台部门信息dao
	@Autowired
	PlatformDepartmentDao platformDepartmentDao;
	
	//删除成功
	private static final int DELETE_SUCCESS = 0;
	//删除失败
	private static final int DELETE_FAIL = 1;
	//删除提示
	private static final int DELETE_TIP = 2;
	
	@Override
	public PlatformDepartmentDTO selectPlatformDepart(int id) {
		PlatformDepartment department = platformDepartmentDao.selectPlatformDepartmentById(id);
		PlatformDepartmentDTO departmentDTO = null;
		if(department != null){
			departmentDTO = new PlatformDepartmentDTO();
			BeanUtils.copyProperties(department, departmentDTO);
		}
		return departmentDTO;
	}

	@Override
	public PageModel<PlatformDepartmentDTO> selectDepartmentList(int currentPage,
			int pageSize, String departName) {
		PlatformDepartment department = new PlatformDepartment();
		department.setName(departName);
		//获取总页数
		int totalCount  = platformDepartmentDao.selectPlatformDepartmentPageCount(department);
		//page 信息
		PageModel<PlatformDepartmentDTO> pageModel = new PageModel<PlatformDepartmentDTO>();
		pageModel.init(currentPage, pageSize);
		//查询部门信息结果集
		List<PlatformDepartment>  departments = platformDepartmentDao.selectPlatformDepartmentPageList(pageModel, department);
		List<PlatformDepartmentDTO> departmentDTOs = new ArrayList<PlatformDepartmentDTO>();
		if(departments != null && departments.size() > 0){
			PlatformDepartmentDTO departmentDTO = null;
			for(PlatformDepartment platformDepartment : departments){
				departmentDTO = new PlatformDepartmentDTO();
				BeanUtils.copyProperties(platformDepartment, departmentDTO);
			}
		}
		pageModel.setTotal(totalCount);
		pageModel.setDatas(departmentDTOs);
		return pageModel ;
	}

	@Override
	public List<TreeNode> selectDepartmentTree() {
		List<TreeNode> results = new ArrayList<TreeNode>();
		List<PlatformDepartment> departments = platformDepartmentDao.selectPlatformDepartmentList();
		if(departments != null && departments.size() > 0){
			TreeNode treeNode = null ;
			for(PlatformDepartment department : departments){
				treeNode = new  TreeNode();
				int parentId = department.getFid();
				treeNode.setId(department.getId());
				treeNode.setName(department.getName());
				treeNode.setPId(department.getFid());
				if(parentId == 0){
					treeNode.setOpen(true);
				}
				results.add(treeNode);
			}
		}
		return results;
	}

	@Override
	public boolean checkDepartmentName(String name, int parentId, int id) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("fid", parentId);
		params.put("id", id);
		//获取同级目录中部门名称个数
		int nameCount  = platformDepartmentDao.selectDepartmentNameCount(params);
		if(nameCount == 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean saveOrUpdateDepartment(PlatformDepartmentDTO department) {
		PlatformDepartment platformDepartment = new PlatformDepartment();
		BeanUtils.copyProperties(department, platformDepartment);
		boolean isSucc = false;
		int id = department.getId();
		try {
			if(id == 0){
				platformDepartmentDao.insertPlatformDepartment(platformDepartment);
			}else{
				platformDepartmentDao.updatePlatformDepartment(platformDepartment);
			}
			isSucc = true;
		} catch (Exception e) {
			isSucc = false;
			loger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<保存部门信息失败");
		}
		return isSucc;
	}

	@Override
	public boolean checkDepartmentCode(int code, int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("code", code);
		int codeCount = platformDepartmentDao.selectDepartmentCode(params);
		if(codeCount == 0){
			return true;
		}
		return false;
	}

	@Override
	public int deleteDepartmentInfo(int id) {
		int departmentUserCount = platformDepartmentDao.selectDepartmentUserCount(id);
		if(departmentUserCount > 0){
			return DELETE_TIP;
		}
		try {
			platformDepartmentDao.deletePlatformDepartmentById(id);
		} catch (Exception e) {
			loger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<删除部门信息失败");
			return DELETE_FAIL;
		}
		return DELETE_SUCCESS;
	}

	@Override
	public List<PlatformDepartmentDTO> getPlatformDepartmentDtoList() {
		List<PlatformDepartment> platformDepartmentList = platformDepartmentDao.selectPlatformDepartmentList();
		List<PlatformDepartmentDTO> platformDepartmentDtoList = new ArrayList<PlatformDepartmentDTO>();
		for(PlatformDepartment platformDepartment : platformDepartmentList){
			PlatformDepartmentDTO platformDepartmentDto = new PlatformDepartmentDTO();
			BeanUtils.copyProperties(platformDepartment, platformDepartmentDto);
			platformDepartmentDtoList.add(platformDepartmentDto);
		}
		return platformDepartmentDtoList;
	}
}
