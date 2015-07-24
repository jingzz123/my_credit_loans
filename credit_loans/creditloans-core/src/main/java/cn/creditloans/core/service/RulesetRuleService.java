package cn.creditloans.core.service;

import java.util.List;
import java.util.Map;

import cn.creditloans.core.dto.af.RulesetConditionDTO;
import cn.creditloans.core.dto.af.RulesetRuleDTO;
import cn.creditloans.tools.page.PageModel;

public interface RulesetRuleService {

	/**
	 * 分页展示rule信息
	 * @param rulesetRuleDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<RulesetRuleDTO> getRulesetRulePageList(
			RulesetRuleDTO rulesetRuleDTO, int currentPage, int pageSize);

	/**
	 * 保存Rule
	 * @param rulesetRuleDTO
	 * @return
	 */
	int saveRulesetRule(RulesetRuleDTO rulesetRuleDTO);
	
	/**
	 * 根据ruleId返回Rule信息
	 * @param ruleId
	 * @return
	 */
	Map<String, Object> getRulesetRuleById(int ruleId);
	
	/**
	 * 修改Rule信息
	 * @param rulesetRuleDTO
	 */
	void updateRulesetRule(RulesetRuleDTO rulesetRuleDTO);
	
	/**
	 * 根据ruleId删除Rule
	 * @param ruleId
	 */
	void deleteRulesetRule(int ruleId);
	/**
	 * 
	* @description 获得全部rulesetRule
	* @param 
	* @return
	 */
	List<RulesetRuleDTO> getAllInfos();
	
	/**
	 * 
	* 检查名字是否唯一
	* @param 
	* @return
	 */
	boolean checkNameIsExist(String name);
	/**
	 * 
	 * 检查次序是否唯一
	 * @param 
	 * @return
	 */
	boolean checkSequenceIsExist(int sequence);
	/**
	 * 获得所有相关实体
	* 
	* @param 
	* @return
	 */
	Map<String, Object> getAllRelationEntity();
}
