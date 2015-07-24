package cn.creditloans.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import cn.creditloans.core.entity.enterprise.EnterpriseType;
import cn.creditloans.tools.page.PageModel;

public interface EnterpriseTypeDao {
	
	/**
	 * 查询所有企业类型
	 * @return
	 */
	List<EnterpriseType> selectEnterpriseTypeList(@Param("pm") PageModel<?> pm, @Param("user") EnterpriseType enterpriseType);
	
	/**
	 * 根据id获取企业类型对象
	 * @param id
	 * @return
	 */
	EnterpriseType selectEnterpriseTypeById(int id);
	
	/**
	 * 添加企业类型
	 * @param enterpriseType
	 */
	void insertEnterpriseType(EnterpriseType enterpriseType);
	
	/**
	 * 更新企业类型
	 * @param enterpriseType
	 */
	void updateEnterpriseType(EnterpriseType enterpriseType);
	
	/**
	 * 删除企业类型
	 * @param id
	 */
	void deleteEnterpriseTypeById(int id);
	
	/**
	 * 获取企业类型总条数
	 * 
	 */
	int selectEnterpriseTypePageCount();
	
	/**
	 * 重名信息数量
	 * @param name
	 * @return
	 */
	int selectEnterpriseName(String name);
	
	/**
	 * 查询企业类型是否已做关联
	 * @param id
	 * @return
	 */
	int selectEnterpriseType(int id);
	
	List<EnterpriseType> selectTypeList();
}
