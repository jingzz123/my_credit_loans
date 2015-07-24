package cn.creditloans.core.dao.af;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.dao.share.AbstractEntityDao;
import cn.creditloans.core.entity.af.BaseActionName;
import cn.creditloans.core.entity.af.BaseActionRelation;
import cn.creditloans.core.entity.af.RulesetCondition;

/**
 * 对af_base_variable_transaction的DAO，通用的方法进行了继承
 * @author Ash
 *
 */
public interface BaseActionNameDao extends AbstractEntityDao<BaseActionName>{
	/**
	 * 
	* 批量插入关系
	* @param 
	* @return
	 */
	void batchInsertBaseActionRelation(List<BaseActionRelation> baseActionRelationList);
	/**
	 * 
	* 根据type和nameId删除所有关系
	* @param 
	* @return
	 */
	void deleteBaseActionRelationByActionName(Map<String, Object> param);
	/**
	 * 查询不包含rule中的action
	* 
	* @param 
	* @return
	 */
	List<BaseActionName> selectActionNotRule(@Param("actionNameList") List<String> actionNameList);
	/**
	 * 
	* 查询包是否被关联
	* @param 
	* @return
	 */
	public int selectCountByBaseId(int baseId);
}
