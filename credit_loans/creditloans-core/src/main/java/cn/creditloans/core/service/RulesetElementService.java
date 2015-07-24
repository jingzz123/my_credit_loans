package cn.creditloans.core.service;

import java.util.List;
import java.util.Map;

import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.RulesetElementDTO;
import cn.creditloans.tools.page.PageModel;

public interface RulesetElementService {
	
	/**
	 * 分页展示rulesetElement信息
	 * @param rulesetElementDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<RulesetElementDTO> getRulesetElementPageList(RulesetElementDTO rulesetElementDTO, int currentPage, int pageSize);
	
	/**
	 * 保存rulesetElement
	 * @param rulesetElementDTO
	 * @return
	 */
	int saveRulesetElement(RulesetElementDTO rulesetElementDTO);
	
	/**
	 * 根据rulesetElementId返回rulesetElement信息
	 * @param rulesetElementId
	 * @return
	 */
	Map<String, Object> getRulesetElementById(int rulesetElementId);
	
	/**
	 * 修改rulesetElement信息
	 * @param rulesetElementDTO
	 */
	void updateRulesetElement(RulesetElementDTO rulesetElementDTO);
	
	/**
	 * 根据rulesetElementId删除relesetElemejt
	 * @param elementId
	 */
	int deleteRulesetElement(int rulesetElementId);
	/**
	 * 
	* 检查名字是否唯一
	* @param 
	* @return
	 */
	boolean checkNameIsExist(String name);
	/**
	 * 获得所有相关实体
	* 
	* @param 
	* @return
	 */
	Map<String, Object> getAllRelationEntity();
	
}
