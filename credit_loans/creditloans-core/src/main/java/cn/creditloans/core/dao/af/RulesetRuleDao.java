package cn.creditloans.core.dao.af;

import cn.creditloans.core.dao.share.AbstractEntityDao;
import cn.creditloans.core.entity.af.RulesetRule;

/**
 * 对af_ruleset_rule的DAO，通用的方法进行了继承
 * @author Ash
 *
 */
public interface RulesetRuleDao extends AbstractEntityDao<RulesetRule> {
	/**
	 * 检查序列是否唯一
	* 
	* @param 
	* @return
	 */
	int selectSequenceIsExit(int sequence);
	/**
	 * 
	* 根据rulesetElementId返回rule总数
	* @param 
	* @return
	 */
	int selectCountByRulesetElementId(int rulesetElementId);
}
