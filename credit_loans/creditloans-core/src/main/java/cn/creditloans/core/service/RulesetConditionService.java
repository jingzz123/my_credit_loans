package cn.creditloans.core.service;

import java.util.List;
import java.util.Map;

import cn.creditloans.core.dto.af.MetadataFieldDTO;
import cn.creditloans.core.dto.af.RulesetConditionDTO;
import cn.creditloans.core.entity.af.MetadataField;
import cn.creditloans.tools.page.PageModel;

public interface RulesetConditionService {

	/**
	 * 分页展示condition信息
	 * @param rulesetConditionDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<RulesetConditionDTO> getRulesetConditionPageList(
			RulesetConditionDTO rulesetConditionDTO, int currentPage, int pageSize);

	/**
	 * 保存condition
	 * @param rulesetConditionDTO
	 * @return
	 */
	int saveRulesetCondition(RulesetConditionDTO rulesetConditionDTO);
	
	/**
	 * 根据conditionId返回condition信息
	 * @param conditionId
	 * @return
	 */
	RulesetConditionDTO getRulesetConditionById(int conditionId);
	
	/**
	 * 修改condition信息
	 * @param rulesetConditionDTO
	 */
	void updateRulesetCondition(RulesetConditionDTO rulesetConditionDTO);
	
	/**
	 * 根据conditionId删除condition
	 * @param conditionId
	 */
	void deleteRulesetCondition(int conditionId);
	/**
	 * 
	* @description 获得全部rulesetCondition
	* @param 
	* @return
	 */
	List<RulesetConditionDTO> getAllInfos();
	
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
	 * 获得所有的变量
	* 
	* @param 
	* @return
	 */
	Map<Integer, String> getVariable(String name);
}
