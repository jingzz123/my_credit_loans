package cn.creditloans.core.dao.af;

import java.util.List;
import java.util.Map;

import cn.creditloans.core.dao.share.AbstractEntityDao;
import cn.creditloans.core.entity.af.BaseVariableRelation;
import cn.creditloans.core.entity.af.BaseVariableSimple;

/**
 * 对af_base_variable_simple的DAO，通用的方法进行了继承
 * @author Ash
 *
 */
public interface BaseVariableSimpleDao extends AbstractEntityDao<BaseVariableSimple> {
	/**
	 * 
	* 批量插入关系
	* @param 
	* @return
	 */
	void batchInsertBaseVariableRelation(List<BaseVariableRelation> baseVariableRelationList);
	/**
	 * 
	* 根据type和nameId删除所有关系
	* @param 
	* @return
	 */
	void deleteBaseVariableRelationByVariable(Map<String, Object> param);
	/**
	 * 根据名称获得实体
	* 
	* @param 
	* @return
	 */
	List<BaseVariableSimple> selectAllByName(String name);
	/**
	 * 
	* 查询包是否被关联
	* @param 
	* @return
	 */
	public int selectCountByBaseId(int baseId);
}
