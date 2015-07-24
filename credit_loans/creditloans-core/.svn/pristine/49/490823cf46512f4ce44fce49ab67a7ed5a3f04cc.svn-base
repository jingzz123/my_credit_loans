package cn.creditloans.core.dao.af;

import java.util.List;
import java.util.Map;

import cn.creditloans.core.dao.share.AbstractEntityDao;
import cn.creditloans.core.entity.af.BaseActionRelation;
import cn.creditloans.core.entity.af.BaseActionVariable;

/**
 * 对af_base_action_variable的DAO，通用的方法进行了继承
 * @author Ash
 *
 */
public interface BaseActionVariableDao extends AbstractEntityDao<BaseActionVariable> {
	/**
	 * 
	* 批量插入关系
	* @param 
	* @return
	 */
	void batchInsertBaseActionRelation(List<BaseActionRelation> baseActionRelationList);
	/**
	 * 
	* 根据type和baseId得到最大sequence
	* @param 
	* @return
	 */
	Integer getMaxSequence(Map<String, Object> param);
	/**
	 * 
	* 根据type和nameId删除所有关系
	* @param 
	* @return
	 */
	void deleteBaseActionRelationByActionVariable(Map<String, Object> param);
}
