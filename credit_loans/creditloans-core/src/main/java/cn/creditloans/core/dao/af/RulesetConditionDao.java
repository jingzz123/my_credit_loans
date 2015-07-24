package cn.creditloans.core.dao.af;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.dao.share.AbstractEntityDao;
import cn.creditloans.core.entity.af.RulesetCondition;

/**
 * 对af_ruleset_condition的DAO，通用的方法进行了继承
 * @author Ash
 *
 */
public interface RulesetConditionDao extends AbstractEntityDao<RulesetCondition> {
	/**
	 * 检查序列是否唯一
	* 
	* @param 
	* @return
	 */
	int selectSequenceIsExit(int sequence);
	/**
	 * 查询不包含rule中的condition
	* 
	* @param 
	* @return
	 */
	List<RulesetCondition> selectConditionNotRule(@Param("conditionList") List<String> conditionList);
	/**
	 * 
	* 根据rulesetElementId返回condition总数
	* @param 
	* @return
	 */
	int selectCountByRulesetElementId(int rulesetElementId);
}
