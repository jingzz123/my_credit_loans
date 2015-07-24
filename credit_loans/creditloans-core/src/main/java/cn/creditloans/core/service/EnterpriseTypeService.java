package cn.creditloans.core.service;

import java.util.List;

import cn.creditloans.core.dto.enterprise.EnterpriseTypeDTO;
import cn.creditloans.core.entity.enterprise.EnterpriseType;
import cn.creditloans.tools.page.PageModel;

public interface EnterpriseTypeService {
	/**
	 * 获取企业类型
	 * @param enterpriseType   企业类型实体
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<EnterpriseTypeDTO> selectEnterpriseTypePageList(
			EnterpriseType enterpriseType, int currentPage, int pageSize);
	
	/**
	 * 删除企业类型
	 * @param id
	 * @return
	 */
	int deleteEnterpriseType(int id);
	
	/**
	 * 添加企业类型
	 * @param enterpriseType
	 * @return
	 */
	boolean insertEnterpriseType(EnterpriseTypeDTO enterpriseType);
	
	/**
	 * 更新企业类型
	 * @param enterpriseType
	 * @return
	 */
	boolean updateEnterpriseType(EnterpriseTypeDTO enterpriseType);
	
	/**
	 * 根据id获取企业类型
	 * @param id
	 * @return
	 */
	EnterpriseTypeDTO selectEnterpriseTypeById(int id); 
	
	/**
	 * 重名验证
	 * @param name
	 * @return
	 */
	boolean selectEnterpriseName(String name);
	
	/**
	 * 获取企业类型
	 * @return
	 */
	List<EnterpriseTypeDTO> selectEnterpriseTypeList();
}
